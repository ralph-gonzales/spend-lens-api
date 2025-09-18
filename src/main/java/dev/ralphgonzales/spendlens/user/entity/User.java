package dev.ralphgonzales.spendlens.user.entity;

import dev.ralphgonzales.spendlens.shared.domain.BaseEntity;
import dev.ralphgonzales.spendlens.user.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@FieldNameConstants
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "app_user",
        schema = "spend_lens",
        indexes = @Index(name = "uq_app_user__email_normalized__active_only", columnList = "email_normalized",
                unique = true)
)
public class User extends BaseEntity {

    @Id
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(length = 100)
    private String lastName;

    @Column(nullable = false, length = 254)
    private String email;

    @Column(insertable = false, updatable = false)
    private String emailNormalized;

    @Column(length = 25)
    private String contactNumber;

    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;
}
