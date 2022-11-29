package uos.capstone.backend.note.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import uos.capstone.backend.note.dto.response.NoteInfoResponse;
import uos.capstone.backend.search.dto.NoteSearchResponse;

public interface NoteRepositoryCustom {
	NoteInfoResponse findOneNoteById(Long noteId);

	Slice<NoteSearchResponse> findAllNoteBySearch(String query, Pageable pageable);
}
