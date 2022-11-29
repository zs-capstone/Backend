package uos.capstone.backend.search.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import uos.capstone.backend.note.domain.Note;
import uos.capstone.backend.search.dto.NoteSearchResponse;

@Mapper(componentModel = "spring")
public interface NoteSearchResponseMapper {

	NoteSearchResponseMapper INSTANCE = Mappers.getMapper(NoteSearchResponseMapper.class);

	@Mapping(source = "id", target = "noteId")
	NoteSearchResponse toDto(Note note);
}
