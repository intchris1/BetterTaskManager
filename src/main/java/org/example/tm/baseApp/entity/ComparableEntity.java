package org.example.tm.baseApp.entity;

import org.example.tm.enumeration.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

public interface ComparableEntity {
    @Nullable
    String getStartDate();

    @Nullable
    String getEndDate();

    @NotNull Status getStatus();

    @Nullable
    Date getCreationDate();
}
