package uos.capstone.backend.user.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import uos.capstone.backend.user.domain.Token;
import uos.capstone.backend.user.dto.response.UserResponse;
import uos.capstone.backend.user.dto.response.UserTokenResponse;

@Mapper(componentModel = "spring")
public interface UserTokenMapper {
	UserTokenMapper INSTANCE = Mappers.getMapper(UserTokenMapper.class);

	@Mapping(source = "userResponse", target="userResponse")
	UserTokenResponse toDto(Token token, UserResponse userResponse);
}
