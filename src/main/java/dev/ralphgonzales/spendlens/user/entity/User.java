package dev.ralphgonzales.spendlens.user.entity;

import dev.ralphgonzales.spendlens.common.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String roleTypeCode;
}
