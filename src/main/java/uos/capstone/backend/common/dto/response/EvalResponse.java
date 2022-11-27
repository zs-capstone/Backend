package uos.capstone.backend.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalResponse {

	private Long placeId;

	private Boolean isUserPick;

	private Integer day;

}
