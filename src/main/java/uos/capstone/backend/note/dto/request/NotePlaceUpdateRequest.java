package uos.capstone.backend.note.dto.request;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotePlaceUpdateRequest {

	private List<Long> placeList;

}
