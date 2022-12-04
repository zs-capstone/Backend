package uos.capstone.backend.note.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.common.dto.response.EvalResponse;
import uos.capstone.backend.common.utils.FlaskUtils;
import uos.capstone.backend.note.domain.Note;
import uos.capstone.backend.note.domain.Recommend;
import uos.capstone.backend.note.domain.repository.NoteRepository;
import uos.capstone.backend.note.domain.repository.RecommendRepository;
import uos.capstone.backend.note.dto.request.NoteCreateRequest;
import uos.capstone.backend.note.dto.request.NoteUpdateRequest;
import uos.capstone.backend.note.dto.response.NoteInfoResponse;
import uos.capstone.backend.note.exception.NoteNotFoundException;
import uos.capstone.backend.place.domain.Place;
import uos.capstone.backend.place.domain.repository.PlaceRepository;
import uos.capstone.backend.place.exception.PlaceNotExistException;
import uos.capstone.backend.survey.domain.repository.SurveyRepository;
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
	public Long save(Long userId, NoteCreateRequest noteCreateRequest) {
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
					.day(listEvalResponse.get(i).getDay())
					.place(findPlace(listEvalResponse.get(i).getPlaceId()))
					.note(note)
					.isUserPick(listEvalResponse.get(i).getIsUserPick())
					.build());
		}
		recommendRepository.saveAll(recommendList);

		return note.getId();
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
		NoteInfoResponse response =  noteRepository.findOneNoteById(noteId);

		return response;
	}

	@Transactional
	public void updateById(Long userId, Long noteId, NoteUpdateRequest noteUpdateRequest) {
		Note note = findNote(noteId);
		validateNoteAuthor2(note, findUser(userId));

		note.updateNote(noteUpdateRequest);

		if (noteUpdateRequest.getDayStart()!=null ||
			noteUpdateRequest.getDayEnd()!=null ||
			noteUpdateRequest.getMaxPlacePerDay()!=null ||
			noteUpdateRequest.getPlaceList()!=null) {

			recommendRepository.deleteAllByNote(note);
			List<Recommend> recommendList = new ArrayList<>();
			List<Long> orgList = new ArrayList<>();

			if (noteUpdateRequest.getPlaceList() != null) {
				orgList = noteUpdateRequest.getPlaceList();
			}

			List<EvalResponse> listEvalResponse = flaskUtils.reevaluate(userId, note, orgList)
				.getListEvalResponse();
			final Integer len = listEvalResponse.size();

			for (int i = 0; i < len; i++) {
				recommendList.add(
					Recommend.builder()
						.day(listEvalResponse.get(i).getDay())
						.place(findPlace(listEvalResponse.get(i).getPlaceId()))
						.note(note)
						.isUserPick(listEvalResponse.get(i).getIsUserPick())
						.build());
			}

			recommendRepository.saveAll(recommendList);
		}
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
