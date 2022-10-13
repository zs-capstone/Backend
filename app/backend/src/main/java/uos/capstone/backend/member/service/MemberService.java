package uos.capstone.backend.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uos.capstone.backend.member.config.cache.CacheKey;
import uos.capstone.backend.member.config.jwt.JwtExpirationEnums;
import uos.capstone.backend.member.domain.*;
import uos.capstone.backend.member.dto.request.JoinDto;
import uos.capstone.backend.member.dto.request.LoginDto;
import uos.capstone.backend.member.dto.response.MemberInfo;
import uos.capstone.backend.member.dto.response.TokenDto;
import uos.capstone.backend.member.util.JwtTokenUtil;

import java.util.NoSuchElementException;

import static uos.capstone.backend.member.config.jwt.JwtExpirationEnums.REFRESH_TOKEN_EXPIRATION_TIME;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    private final LogoutAccessTokenRedisRepository logoutAccessTokenRedisRepository;
    private final JwtTokenUtil jwtTokenUtil;

    //user 회원 가입
    public void join(JoinDto joinDto) {
        joinDto.setPassword(passwordEncoder.encode(joinDto.getPassword()));
        memberRepository.save(Member.ofUser(joinDto));
    }

    //admin 회원가입
    public void joinAdmin(JoinDto joinDto) {
        joinDto.setPassword(passwordEncoder.encode(joinDto.getPassword()));
        memberRepository.save(Member.ofAdmin(joinDto));
    }

    //login에 사용할 메소드
    private void checkPassword(String rawPassword, String findMemberPassword) {
        if (!passwordEncoder.matches(rawPassword, findMemberPassword)) {
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
        }
    }

    private RefreshToken saveRefreshToken(String username) {
        return refreshTokenRedisRepository.save(RefreshToken.createRefreshToken(username,
                jwtTokenUtil.generateRefreshToken(username), REFRESH_TOKEN_EXPIRATION_TIME.getValue()));
    }

    //login
    public TokenDto login(LoginDto loginDto) {
        Member member = memberRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new NoSuchElementException("회원이 없습니다."));
        checkPassword(loginDto.getPassword(), member.getPassword());

        String username = member.getUsername();
        String accessToken = jwtTokenUtil.generateAccessToken(username);
        RefreshToken refreshToken = saveRefreshToken(username);
        return TokenDto.of(accessToken, refreshToken.getRefreshToken());
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return principal.getUsername();
    }

    public MemberInfo getMemberInfo(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("회원이 없습니다."));
        if (!member.getUsername().equals(getCurrentUsername())) {
            throw new IllegalArgumentException("회원 정보가 일치하지 않습니다.");
        }
        return MemberInfo.builder()
                .username(member.getUsername())
                .email(member.getEmail())
                .build();
    }

    // 토큰 재발급 보조 메소드
    private String resolveToken(String token) {
        return token.substring(7);
    }
    private boolean lessThanReissueExpirationTimesLeft(String refreshToken) {
        return jwtTokenUtil.getRemainMilliSeconds(refreshToken) < JwtExpirationEnums.REISSUE_EXPIRATION_TIME.getValue();
    }
    private TokenDto reissueRefreshToken(String refreshToken, String username) {
        if (lessThanReissueExpirationTimesLeft(refreshToken)) {
            String accessToken = jwtTokenUtil.generateAccessToken(username);
            return TokenDto.of(accessToken, saveRefreshToken(username).getRefreshToken());
        }
        return TokenDto.of(jwtTokenUtil.generateAccessToken(username), refreshToken);
    }

    // 토큰 재발급
    public TokenDto reissue(String refreshToken) {
        refreshToken = resolveToken(refreshToken);
        String username = getCurrentUsername();
        RefreshToken redisRefreshToken = refreshTokenRedisRepository.findById(username).orElseThrow(NoSuchElementException::new);

        if (refreshToken.equals(redisRefreshToken.getRefreshToken())) {
            return reissueRefreshToken(refreshToken, username);
        }
        throw new IllegalArgumentException("토큰이 일치하지 않습니다.");
    }

    @CacheEvict(value = CacheKey.USER, key = "#username")
    public void logout(TokenDto tokenDto, String username) {
        String accessToken = resolveToken(tokenDto.getAccessToken());
        long remainMilliSeconds = jwtTokenUtil.getRemainMilliSeconds(accessToken);
        refreshTokenRedisRepository.deleteById(username);
        logoutAccessTokenRedisRepository.save(LogoutAccessToken.of(accessToken, username, remainMilliSeconds));
    }
}
