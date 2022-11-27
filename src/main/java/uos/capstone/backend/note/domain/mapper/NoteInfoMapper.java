package uos.capstone.backend.note.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import uos.capstone.backend.note.domain.Note;
import uos.capstone.backend.note.dto.request.NoteCreateRequest;
import uos.capstone.backend.note.dto.response.NoteInfoResponse;
import uos.capstone.backend.user.domain.User;

@Mapper(componentModel = "spring")
public interface NoteInfoMapper {
	NoteInfoMapper INSTANCE = Mappers.getMapper(NoteInfoMapper.class);

	NoteInfoResponse toDto(Note dto);
}

