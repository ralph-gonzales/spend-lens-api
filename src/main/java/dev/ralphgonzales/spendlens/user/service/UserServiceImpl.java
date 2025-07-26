package dev.ralphgonzales.spendlens.user.service;

import dev.ralphgonzales.spendlens.shared.dto.PaginatedResponse;
import dev.ralphgonzales.spendlens.user.dto.UserDto;
import org.springframework.data.domain.Pageable;

public class UserServiceImpl implements UserService {
    @Override
    public UserDto create(UserDto dto) {
        return null;
    }

    @Override
    public UserDto update(UserDto dto) {
        return null;
    }

    @Override
    public void delete(UserDto dto) {

    }

    @Override
    public PaginatedResponse<UserDto> findAll(Pageable pageable) {
        return null;
    }
}
