package uos.capstone.backend.note.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import uos.capstone.backend.note.domain.Note;
import uos.capstone.backend.note.dto.NoteCreateRequest;
import uos.capstone.backend.user.domain.User;

@Mapper(componentModel = "spring")
public interface NoteCreateRequestMapper {

	NoteCreateRequestMapper INSTANCE = Mappers.getMapper(NoteCreateRequestMapper.class);

	@Mapping(expression="java(dto.getRegion())", target="region")
	Note toEntity(User user, NoteCreateRequest dto);

}
