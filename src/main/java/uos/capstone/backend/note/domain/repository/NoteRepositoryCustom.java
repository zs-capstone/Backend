package uos.capstone.backend.note.domain.repository;

import uos.capstone.backend.note.dto.response.NoteInfoResponse;

public interface NoteRepositoryCustom {
	NoteInfoResponse findOneNoteById(Long noteId);
}
