// package uos.capstone.backend.note.application;
//
// import java.util.stream.Collectors;
//
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
//
// import lombok.RequiredArgsConstructor;
// import uos.capstone.backend.note.domain.Note;
// import uos.capstone.backend.note.domain.mapper.NoteInfoMapper;
// import uos.capstone.backend.note.domain.mapper.NotePlaceUpdateMapper;
// import uos.capstone.backend.note.domain.repository.NoteRepository;
// import uos.capstone.backend.note.domain.repository.RecommendRepository;
// import uos.capstone.backend.note.dto.request.NoteCreateRequest;
// import uos.capstone.backend.note.dto.request.NotePlaceUpdateRequest;
// import uos.capstone.backend.note.dto.request.NoteUpdateRequest;
// import uos.capstone.backend.note.dto.response.NoteInfoResponse;
// import uos.capstone.backend.note.exception.NoteNotFoundException;
// import uos.capstone.backend.place.domain.repository.PlaceRepository;
// import uos.capstone.backend.place.exception.PlaceNotExistException;
// import uos.capstone.backend.user.domain.User;
// import uos.capstone.backend.user.domain.UserRepository;
// import uos.capstone.backend.user.exception.UserNotAuthorException;
// import uos.capstone.backend.user.exception.UserNotFoundException;
//
// @Service
// @Transactional(readOnly = true)
// @RequiredArgsConstructor
// public class NoteService {
//
// 	private final NoteRepository noteRepository;
//
// 	private final UserRepository userRepository;
//
// 	private final RecommendRepository recommendRepository;
//
// 	private final PlaceRepository placeRepository;
//
// 	@Transactional
// 	public Long save(Long userId, NoteCreateRequest noteCreateRequest) {
// 		Long response =  noteRepository.save(
// 			Note.builder()
// 				.noteCreateRequest(noteCreateRequest)
// 				.user(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException()))
// 				.placeList(noteCreateRequest.getPlaceList()
// 					.stream()
// 					.map( x -> {
// 						return placeRepository.findById(x)
// 							.orElseThrow(() -> new PlaceNotExistException());
// 					})
// 					.collect(Collectors.toList()))
// 				.recommendList(null)
// 				.build())
// 			.getId();
//
// 		return response;
// 	}
//
// 	public NoteInfoResponse findById(Long userId, Long noteId) {
// 		validateNoteAuthor(noteRepository.findById(noteId)
// 				.orElseThrow(() -> new NoteNotFoundException()),
// 			userRepository.findById(userId)
// 				.orElseThrow(()->new UserNotFoundException()));
//
// 		NoteInfoResponse response =  NoteInfoMapper.INSTANCE.toDto(
// 				noteRepository.findById(noteId).orElseThrow(() -> new NoteNotFoundException()));
//
// 		return response;
// 	}
//
// 	@Transactional
// 	public void updateById(Long userId, Long noteId, NoteUpdateRequest noteUpdateRequest) {
// 		Note note = noteRepository.findById(noteId)
// 			.orElseThrow(() -> new NoteNotFoundException());
// 		validateNoteAuthor2(note, userRepository.findById(userId)
// 				.orElseThrow(()->new UserNotFoundException()));
//
// 		note.updateNote(noteUpdateRequest);
//
// 		if (!(noteUpdateRequest.getDayStart()!=null) ||
// 			!(noteUpdateRequest.getDayEnd()!=null)) {
// 			// 학습 수행
// 		}
// 	}
//
// 	@Transactional
// 	public void updatePlaceById(Long userId, Long noteId, NotePlaceUpdateRequest notePlaceUpdateRequest) {
// 		Note note = noteRepository.findById(noteId)
// 			.orElseThrow(() -> new NoteNotFoundException());
// 		validateNoteAuthor2(note, userRepository.findById(userId)
// 			.orElseThrow(()->new UserNotFoundException()));
//
// 		note.updateNotePlace(notePlaceUpdateRequest.getPlaceList()
// 			.stream()
// 			.map(NotePlaceUpdateMapper.INSTANCE::toEntity)
// 			.collect(Collectors.toList()));
// 	}
//
// 	@Transactional
// 	public void deleteById(Long userId, Long noteId) {
// 		validateNoteAuthor2(noteRepository.findById(noteId)
// 				.orElseThrow(() -> new NoteNotFoundException()),
// 			userRepository.findById(userId)
// 				.orElseThrow(()->new UserNotFoundException()));
//
// 		noteRepository.deleteById(noteId);
// 	}
//
// 	private void validateNoteAuthor(Note note, User user) {
// 		if (note.getPublic_share()) {
// 			return;
// 		}
// 		if (note.getUser().getId() != user.getId()) {
// 			throw new UserNotAuthorException();
// 		}
// 	}
//
// 	private void validateNoteAuthor2(Note note, User user) {
// 		if (note.getUser().getId() != user.getId()) {
// 			throw new UserNotAuthorException();
// 		}
// 	}
//
// }
