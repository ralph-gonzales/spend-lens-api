package dev.ralphgonzales.spendlens.user.mapper;

import dev.ralphgonzales.spendlens.user.dto.UserDto;
import dev.ralphgonzales.spendlens.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toDto(UserDto userDto);
    UserDto toEntity(User user);
}
