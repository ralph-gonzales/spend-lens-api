package dev.ralphgonzales.spendlens.user.repository;

import dev.ralphgonzales.spendlens.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
