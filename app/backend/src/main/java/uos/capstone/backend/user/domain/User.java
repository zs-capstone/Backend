package uos.capstone.backend.user.domain;

import lombok.*;
import uos.capstone.backend.common.domain.BaseEntity;
import uos.capstone.backend.user.dto.request.UserUpdateRequest;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Table(name="user", uniqueConstraints = {@UniqueConstraint(name = "uk_user_email", columnNames = {"email"})})
public class User extends BaseEntity {

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String nickname;

	@Column(nullable = false, length = 20)
	private String phone;
	private String img;
	private String region;




	public void updateUser(UserUpdateRequest userUpdateRequest) {

		if (userUpdateRequest.getName() != "") {
			this.name = userUpdateRequest.getName();
		}
		if (userUpdateRequest.getRegion() != "") {
			this.region = userUpdateRequest.getRegion();
		}
		if (userUpdateRequest.getPhone() != "") {
			this.phone = userUpdateRequest.getPhone();
		}
		if (userUpdateRequest.getNickname() != "") {
			this.nickname = userUpdateRequest.getNickname();
		}

	}

	public void updateImg(String img) {
		this.img = img;
	}

	public void deleteImg() {this.img = "";}
}
