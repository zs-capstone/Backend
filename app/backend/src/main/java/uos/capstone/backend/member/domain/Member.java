package uos.capstone.backend.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.capstone.backend.common.domain.BaseTimeEntity;
import uos.capstone.backend.member.dto.request.JoinDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="member")
@Builder
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="username",unique = true, nullable = false, length = 15)
    private String username;

    @Column(name="nickname",unique = true, nullable = false, length = 15)
    private String nickname;

    @Column(name="email", unique = true, nullable = false, length = 320)
    private String email;

    @Column(name="password", nullable = false, length = 100)
    private String password;

    @Column(name="gender", length = 1)
    private String gender;
    @Column(name="year", length = 4)
    private String year;
    @Column(name="month", length = 2)
    private String month;
    @Column(name="day", length = 2)
    private String day;

    @Column(name="region")
    private String region;

    @OneToMany(mappedBy = "member", cascade = ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Authority> authorities = new HashSet<>();

    // 생성자에 들어있는 field들을 builder 클래스로 생성
    // Member.builder().nickname("하이").email("1@com").passowrd("123").build()
    // 로 객체를 생성
    public static Member ofUser(JoinDto joinDto) {
        Member member = Member.builder()
                .username(joinDto.getUsername())
                .email(joinDto.getEmail())
                .password(joinDto.getPassword())
                .nickname(joinDto.getNickname())
                .build();
        member.addAuthority(Authority.ofUser(member));
        return member;
    }

    public static Member ofAdmin(JoinDto joinDto) {
        Member member = Member.builder()
                .username(joinDto.getUsername())
                .email(joinDto.getEmail())
                .password(joinDto.getPassword())
                .nickname(joinDto.getNickname())
                .build();
        member.addAuthority(Authority.ofAdmin(member));
        return member;
    }

    private void addAuthority(Authority authority) {
        authorities.add(authority);
    }

    public List<String> getRoles() {
        return authorities.stream()
                .map(Authority::getRole)
                .collect(toList());
    }

}
