package uos.capstone.backend.user.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import uos.capstone.backend.user.domain.User;
import uos.capstone.backend.user.dto.response.UserInfoResponse;

@Mapper(componentModel = "spring")
public interface UserInfoMapper {
	UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);

	UserInfoResponse toDto(User user);
}
