package uos.capstone.backend.common.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListEvalResponse {
	private List<EvalResponse> listEvalResponse;
}
