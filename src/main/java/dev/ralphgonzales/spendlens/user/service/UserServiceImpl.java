package dev.ralphgonzales.spendlens.user.service;

import dev.ralphgonzales.spendlens.shared.dto.PaginatedResponse;
import dev.ralphgonzales.spendlens.user.dto.UserRequestDto;
import dev.ralphgonzales.spendlens.user.dto.UserResponseDto;
import org.springframework.data.domain.Pageable;

public class UserServiceImpl implements UserService {
    @Override
    public UserResponseDto create(UserRequestDto dto) {
        return null;
    }

    @Override
    public UserResponseDto update(UserRequestDto dto, Long id) {
        return null;
    }

    @Override
    public UserResponseDto getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PaginatedResponse<UserResponseDto> findAll(Pageable pageable) {
        return null;
    }
}
