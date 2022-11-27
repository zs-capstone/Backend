package uos.capstone.backend.note.application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.common.dto.response.EvalResponse;
import uos.capstone.backend.common.dto.response.ListEvalResponse;
import uos.capstone.backend.common.utils.FlaskUtils;
import uos.capstone.backend.note.domain.Note;
import uos.capstone.backend.note.domain.Recommend;
import uos.capstone.backend.note.domain.mapper.NoteInfoMapper;
import uos.capstone.backend.note.domain.repository.NoteRepository;
import uos.capstone.backend.note.domain.repository.RecommendRepository;
import uos.capstone.backend.note.dto.request.NoteCreateRequest;
import uos.capstone.backend.note.dto.request.NotePlaceUpdateRequest;
import uos.capstone.backend.note.dto.request.NoteUpdateRequest;
import uos.capstone.backend.note.dto.response.NoteInfoResponse;
import uos.capstone.backend.note.exception.NoteNotFoundException;
import uos.capstone.backend.place.domain.Place;
import uos.capstone.backend.place.domain.repository.PlaceRepository;
import uos.capstone.backend.place.exception.PlaceNotExistException;
import uos.capstone.backend.survey.domain.repository.SurveyRepository;
import uos.capstone.backend.survey.exception.SurveyNotExistException;
import uos.capstone.backend.survey.exception.UserNotDidSurveyException;
import uos.capstone.backend.user.domain.User;
import uos.capstone.backend.user.domain.UserRepository;
import uos.capstone.backend.user.exception.UserNotAuthorException;
import uos.capstone.backend.user.exception.UserNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoteService {

	private final NoteRepository noteRepository;

	private final UserRepository userRepository;

	private final PlaceRepository placeRepository;

	private final RecommendRepository recommendRepository;

	private final SurveyRepository surveyRepository;

	private final FlaskUtils flaskUtils;

	@Transactional
	public void save(Long userId, NoteCreateRequest noteCreateRequest) {
		if (!(surveyRepository.existsByUser(findUser(userId)))) {
			throw new UserNotDidSurveyException();
		}

		List<EvalResponse> listEvalResponse = flaskUtils.evaluate(userId,noteCreateRequest)
			.getListEvalResponse();

		List<Recommend> recommendList = new ArrayList<>();
		final Integer n = noteCreateRequest.getMaxPlacePerDay();
		final Integer len = listEvalResponse.size();

		Note note = noteRepository.save(
			Note.builder()
				.noteCreateRequest(noteCreateRequest)
				.user(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException()))
				.build());

		for (int i = 0; i < len; i++) {
			recommendList.add(
				Recommend.builder()
					.day(i/n +1)
					.place(findPlace(listEvalResponse.get(i).getPlaceId()))
					.note(note)
					.build());
		}

		recommendRepository.saveAll(recommendList);
	}

	private Note findNote(Long noteId) {
		return noteRepository.findById(noteId)
			.orElseThrow(() -> new NoteNotFoundException());
	}

	private User findUser(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(()->new UserNotFoundException());
	}

	private Place findPlace(Long placeId) {
		return placeRepository.findById(placeId)
			.orElseThrow(() -> new PlaceNotExistException());
	}

	public NoteInfoResponse findById(Long userId, Long noteId) {
		validateNoteAuthor(findNote(noteId),findUser(userId));

		NoteInfoResponse response =  NoteInfoMapper.INSTANCE.toDto(
				findNote(noteId));

		return response;
	}

	@Transactional
	public void updateById(Long userId, Long noteId, NoteUpdateRequest noteUpdateRequest) {
		Note note = findNote(noteId);
		validateNoteAuthor2(note, findUser(userId));

		note.updateNote(noteUpdateRequest);

		if (!(noteUpdateRequest.getDayStart()!=null) ||
			!(noteUpdateRequest.getDayEnd()!=null) ||
			!(noteUpdateRequest.getMaxPlacePerDay()!=null)) {
			// recommend 다시 update 필요
		}
	}

	@Transactional
	public void updatePlaceById(Long userId, Long noteId, NotePlaceUpdateRequest notePlaceUpdateRequest) {
		Note note = findNote(noteId);
		validateNoteAuthor2(note, findUser(userId));

		// 다시 recommend update 필요
		// note.updateNotePlace(notePlaceUpdateRequest
		// 	.getPlaceList()
		// 	.stream()
		// 	.map(x -> {return placeRepository.findById(x)
		// 		.orElseThrow(() -> new PlaceNotExistException());})
		// 	.collect(Collectors.toList()));
	}

	@Transactional
	public void deleteById(Long userId, Long noteId) {
		Note note = findNote(noteId);
		validateNoteAuthor2(note,findUser(userId));

		recommendRepository.deleteAllByNote(note);
		noteRepository.deleteById(noteId);
	}

	private void validateNoteAuthor(Note note, User user) {
		if (note.getPublicShare()) {
			return;
		}
		if (note.getUser().getId() != user.getId()) {
			throw new UserNotAuthorException();
		}
	}

	private void validateNoteAuthor2(Note note, User user) {
		if (note.getUser().getId() != user.getId()) {
			throw new UserNotAuthorException();
		}
	}

}
