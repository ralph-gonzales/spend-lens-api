package dev.ralphgonzales.spendlens.user.service;

import dev.ralphgonzales.spendlens.shared.service.CrudService;
import dev.ralphgonzales.spendlens.user.dto.UserRequestDto;
import dev.ralphgonzales.spendlens.user.dto.UserResponseDto;

public interface UserService extends CrudService<UserRequestDto, UserResponseDto> {
}
