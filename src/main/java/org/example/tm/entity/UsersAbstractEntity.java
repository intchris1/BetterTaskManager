package org.example.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tm.enumeration.Status;
import org.example.tm.util.DateFormatter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class UsersAbstractEntity extends AbstractEntity {

    @NotNull
    private String userId;

    @NotNull
    private Status status = Status.PLANNED;

    @NotNull
    private String description = "";

    @NotNull
    private String startDate = DateFormatter.convertDateToString(getCreationDate());

    @Nullable
    private String endDate = null;

}
