package org.example.tm.baseApp.repository;

import org.example.tm.entity.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {

    @NotNull User findByName(@NotNull String name);

    boolean existsByName(@NotNull String name);
}
