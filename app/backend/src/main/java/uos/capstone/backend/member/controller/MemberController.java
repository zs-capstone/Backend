package uos.capstone.backend.member.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uos.capstone.backend.member.dto.request.JoinDto;
import uos.capstone.backend.member.dto.request.LoginDto;
import uos.capstone.backend.member.dto.response.MemberInfo;
import uos.capstone.backend.member.dto.response.TokenDto;
import uos.capstone.backend.member.exception.NoEmailException;
import uos.capstone.backend.member.service.MemberService;
import uos.capstone.backend.member.util.JwtTokenUtil;

@Tag(name="멤버", description = "회원가입, 로그인 관련")
@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenUtil jwtTokenUtil;


    @PostMapping("/new")
    public ResponseEntity<String> join(@RequestBody JoinDto joinDto) {
        memberService.join(joinDto);
        return ResponseEntity.ok("회원가입 완료");
    }

//    @PostMapping("/new/admin")
//    public String joinAdmin(@RequestBody JoinDto joinDto) {
//        memberService.joinAdmin(joinDto);
//        return "어드민 회원 가입 완료";
//    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(memberService.login(loginDto));
    }

    @PostMapping("/email")
    public ResponseEntity<String> validateEmail(@RequestBody String email) {
        memberService.validate_email(email);
        return ResponseEntity.ok("중복 없음");
    }

    @PostMapping("/nickname")
    public ResponseEntity<String> validateNickname(@RequestBody String nickname) {
        memberService.validate_nickname(nickname);
        return ResponseEntity.ok("중복 없음");
    }

//    @PatchMapping("/members/nickname")
//    public ResponseEntity<String> patchNickname(@RequestHeader("Authorization") String accessToken,
//                                                @RequestBody String nickname) {
//        memberService.change_nickname(accessToken,nickname);
//        return ResponseEntity.ok("성공");
//    }

//    @GetMapping("/members/{email}")
//    public MemberInfo getMemberInfo(@PathVariable String email) {
//        return memberService.getMemberInfo(email);
//    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestHeader("RefreshToken") String refreshToken) {
        return ResponseEntity.ok(memberService.reissue(refreshToken));
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String accessToken,
                       @RequestHeader("RefreshToken") String refreshToken) {
        String username = jwtTokenUtil.getUsername(resolveToken(accessToken));
        System.out.println("logout 유저 이름");
        System.out.println(username);
        memberService.logout(TokenDto.of(accessToken, refreshToken), username);
    }

    private String resolveToken(String accessToken) {
        return accessToken.substring(7);
    }
}
