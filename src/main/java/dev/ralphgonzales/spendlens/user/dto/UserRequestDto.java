package dev.ralphgonzales.spendlens.user.dto;

public record UserRequestDto(
       String userName,
       String firstName,
       String lastName,
       String email,
       String contactNumber,
       String roleTypeCode
) {
}
