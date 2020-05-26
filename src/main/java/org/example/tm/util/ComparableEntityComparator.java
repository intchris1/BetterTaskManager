package org.example.tm.util;

import org.example.tm.baseApp.entity.ComparableEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class ComparableEntityComparator {

    @NotNull
    public static final Comparator<ComparableEntity> comparatorCreationDate = (o1, o2) -> {
        if (o1.getCreationDate() == null || o2.getCreationDate() == null) return 0;
        return o1.getCreationDate().compareTo(o2.getCreationDate());
    };


    @NotNull
    public static final Comparator<ComparableEntity> comparatorStartDate = (o1, o2) -> {
        if (o1.getStartDate() == null || o2.getStartDate() == null) return 0;
        return o1.getStartDate().compareTo(o2.getStartDate());
    };

    @NotNull
    public static final Comparator<ComparableEntity> comparatorEndDate = (o1, o2) -> {
        if (o1.getEndDate() == null || o2.getEndDate() == null) return 0;
        return o1.getEndDate().compareTo(o2.getEndDate());
    };

    @NotNull
    public static final Comparator<ComparableEntity> comparatorStatus = Comparator.comparing(ComparableEntity::getStatus);

}
