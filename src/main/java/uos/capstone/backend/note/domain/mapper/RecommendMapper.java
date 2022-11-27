package uos.capstone.backend.note.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import uos.capstone.backend.note.domain.Recommend;
import uos.capstone.backend.place.domain.Place;

@Mapper(componentModel = "spring")
public interface RecommendMapper {

	RecommendMapper INSTANCE = Mappers.getMapper(RecommendMapper.class);

	Recommend toEntity(Integer day, Place place);

}

// @Mapper(componentModel = "spring")
// public interface NoteResponseMapper {
//
// 	NoteResponseMapper INSTANCE = Mappers.getMapper(NoteResponseMapper.class);
//
// 	NoteResponse toDto(Note note);
// }
