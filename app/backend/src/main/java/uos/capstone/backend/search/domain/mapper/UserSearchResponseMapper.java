package uos.capstone.backend.search.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import uos.capstone.backend.search.dto.UserSearchResponse;
import uos.capstone.backend.user.domain.User;

@Mapper(componentModel = "spring")
public interface UserSearchResponseMapper {

	UserSearchResponseMapper INSTANCE = Mappers.getMapper(UserSearchResponseMapper.class);

	@Mapping(source="id",target = "userId")
	@Mapping(source="img",target="profileImg")
	UserSearchResponse toDto(User user);

}
