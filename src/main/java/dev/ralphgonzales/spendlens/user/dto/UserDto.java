package dev.ralphgonzales.spendlens.user.dto;

public record UserDto(
       String userName,
       String firstName,
       String lastName,
       String email,
       String contactNumber,
       String roleTypeCode
) {
}
