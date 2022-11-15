package uos.capstone.backend.note.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import uos.capstone.backend.note.domain.mapper.NoteCreateRequestMapper;
import uos.capstone.backend.note.domain.mapper.NoteResponseMapper;
import uos.capstone.backend.note.domain.repository.NoteRepository;
import uos.capstone.backend.note.dto.NoteCreateRequest;
import uos.capstone.backend.note.dto.NoteResponse;
import uos.capstone.backend.note.exception.NoteNotFoundException;
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

	@Transactional
	public Long save(Long id, NoteCreateRequest noteCreateRequest) {
		return noteRepository.save(
			NoteCreateRequestMapper.INSTANCE
				.toEntity(
				userRepository.findById(id).orElseThrow(() -> new UserNotFoundException()),
				noteCreateRequest))
			.getId();
	}

	public NoteResponse findById(Long userId, Long noteId) {
		validateNote(noteRepository.findById(noteId)
				.orElseThrow(() -> new NoteNotFoundException())
				.getUser(),
			userRepository.findById(userId)
				.orElseThrow(()->new UserNotFoundException()));

		NoteResponse response =  NoteResponseMapper.INSTANCE
			.toDto(
				noteRepository.findById(noteId).orElseThrow(() -> new NoteNotFoundException()));

		return response;
	}

	private void validateNote(User user1, User user2) {
		if (user1.getId() != user2.getId()) {
			throw new UserNotAuthorException();
		}
	}

}
