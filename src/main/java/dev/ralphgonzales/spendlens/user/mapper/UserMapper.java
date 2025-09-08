package dev.ralphgonzales.spendlens.user.mapper;

import dev.ralphgonzales.spendlens.user.dto.UserRequestDto;
import dev.ralphgonzales.spendlens.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toDto(UserRequestDto userDto);
    UserRequestDto toEntity(User user);
}
