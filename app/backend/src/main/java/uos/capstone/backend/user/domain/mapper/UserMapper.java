package uos.capstone.backend.user.domain.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import uos.capstone.backend.user.domain.User;
import uos.capstone.backend.user.dto.response.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	User toEntity(UserResponse dto);

	UserResponse toDto(User user);
}
