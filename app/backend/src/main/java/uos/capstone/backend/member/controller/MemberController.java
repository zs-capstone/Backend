package uos.capstone.backend.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uos.capstone.backend.member.dto.request.JoinDto;
import uos.capstone.backend.member.dto.request.LoginDto;
import uos.capstone.backend.member.dto.response.MemberInfo;
import uos.capstone.backend.member.dto.response.TokenDto;
import uos.capstone.backend.member.service.MemberService;
import uos.capstone.backend.member.util.JwtTokenUtil;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @PostMapping("/join")
    public String join(@RequestBody JoinDto joinDto) {
        memberService.join(joinDto);
        return "회원가입 완료";
    }

    @PostMapping("/join/admin")
    public String joinAdmin(@RequestBody JoinDto joinDto) {
        memberService.joinAdmin(joinDto);
        return "어드민 회원 가입 완료";
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(memberService.login(loginDto));
    }

    @GetMapping("/members/{email}")
    public MemberInfo getMemberInfo(@PathVariable String email) {
        return memberService.getMemberInfo(email);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestHeader("RefreshToken") String refreshToken) {
        return ResponseEntity.ok(memberService.reissue(refreshToken));
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String accessToken,
                       @RequestHeader("RefreshToken") String refreshToken) {
        String username = jwtTokenUtil.getUsername(resolveToken(accessToken));
        memberService.logout(TokenDto.of(accessToken, refreshToken), username);
    }

    private String resolveToken(String accessToken) {
        return accessToken.substring(7);
    }
}
