package uos.capstone.backend.user.domain.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import uos.capstone.backend.user.domain.User;
import uos.capstone.backend.user.dto.request.UserCreateRequest;

@Mapper(componentModel = "spring")
public interface UserCreateMapper {
	UserCreateMapper INSTANCE = Mappers.getMapper(UserCreateMapper.class);

	@Mapping(source="password",target="password")
	User toEntity(UserCreateRequest dto, String password);

}
