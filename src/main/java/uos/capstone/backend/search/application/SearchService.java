package uos.capstone.backend.search.application;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.place.domain.repository.PlaceRepository;
import uos.capstone.backend.search.domain.mapper.PlaceSearchResponseMapper;
import uos.capstone.backend.search.domain.mapper.UserSearchResponseMapper;
import uos.capstone.backend.search.dto.PlaceSearchResponse;
import uos.capstone.backend.search.dto.UserSearchResponse;
import uos.capstone.backend.user.domain.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchService {

	private final PlaceRepository placeRepository;

	private final UserRepository userRepository;

	public Slice<PlaceSearchResponse> findAllPlaceBySearch(String content, Pageable pageable) {
		Slice<PlaceSearchResponse> responses = placeRepository.findByTitleContaining(content,pageable)
			.map(PlaceSearchResponseMapper.INSTANCE::toDto);

		return responses;
	}

	public Slice<UserSearchResponse> findAllUserBySearch(String content, Pageable pageable) {
		Slice<UserSearchResponse> responses = userRepository.findByNicknameContaining(content,pageable)
			.map(UserSearchResponseMapper.INSTANCE::toDto);

		return responses;
	}

	// public Slice<NoteSearchResponse> findAllNoteBySearch(String content, Pageable pageable) {
	// 	Slice<UserSearchResponse> responses = userRepository.findByNicknameContaining(content,pageable)
	// 		.map(UserSearchResponseMapper.INSTANCE::toDto);
	//
	// 	return responses;
	// }

}
