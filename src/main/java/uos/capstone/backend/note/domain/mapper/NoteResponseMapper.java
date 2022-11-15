package uos.capstone.backend.note.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import uos.capstone.backend.note.domain.Note;
import uos.capstone.backend.note.dto.NoteCreateRequest;
import uos.capstone.backend.note.dto.NoteResponse;
import uos.capstone.backend.user.domain.User;

@Mapper(componentModel = "spring")
public interface NoteResponseMapper {

	NoteResponseMapper INSTANCE = Mappers.getMapper(NoteResponseMapper.class);

	NoteResponse toDto(Note note);
}
