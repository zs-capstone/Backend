package uos.capstone.backend.note.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uos.capstone.backend.note.domain.Note;

public interface NoteRepository extends JpaRepository<Note,Long> {
}
