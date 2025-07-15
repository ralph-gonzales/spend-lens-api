package dev.ralphgonzales.spendlens.user.service;

import dev.ralphgonzales.spendlens.user.dto.UserDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
    public List<UserDto> findAll(Pageable pageable) {
        return List.of();
    }
}
