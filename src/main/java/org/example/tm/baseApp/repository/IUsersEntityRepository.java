package org.example.tm.baseApp.repository;

import org.example.tm.entity.UsersAbstractEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface IUsersEntityRepository<T extends UsersAbstractEntity> extends JpaRepository<T, String> {

    boolean existsByUserIdAndName(@NotNull String userId, @NotNull String name);

    @Nullable T findByUserIdAndName(@NotNull String userId, @NotNull String name);

    //можно сделать одим запросом?
    @NotNull List<T> findAllByUserIdAndNameContaining(@NotNull String userId, @NotNull String searchString);

    @NotNull List<T> findAllByUserIdAndDescriptionContaining(@NotNull String userId, @NotNull String searchString);

    @NotNull List<T> findAllByUserIdOrderByStatusAsc(@NotNull String userId);

    @NotNull List<T> findAllByUserIdOrderByNameAsc(@NotNull String userId);

    @NotNull List<T> findAllByUserIdOrderByStartDateAsc(@NotNull String userId);

    @NotNull List<T> findAllByUserIdOrderByEndDateAsc(@NotNull String userId);

    @NotNull List<T> findAllByUserIdOrderByCreationDateAsc(@NotNull String userId);

    void deleteByUserIdAndName(@NotNull String userId, @Nullable String name);

    void deleteAllByUserId(@NotNull String userId);


}
