package uos.capstone.backend.member.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uos.capstone.backend.member.config.cache.CacheKey;
import uos.capstone.backend.member.domain.Member;
import uos.capstone.backend.member.domain.MemberRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Cacheable(value = CacheKey.USER, key = "#username", unless = "#result == null")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByNicknameWithAuthority(username).orElseThrow(() -> new NoSuchElementException("없는 회원입니다."));
        return CustomUserDetails.of(member);
    }
}
