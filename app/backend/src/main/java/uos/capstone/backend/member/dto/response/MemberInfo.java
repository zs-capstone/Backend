package uos.capstone.backend.member.dto.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class MemberInfo {

    private String email;
    private String username;
}
