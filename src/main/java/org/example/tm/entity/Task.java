package org.example.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tm.baseApp.entity.ComparableEntity;
import org.example.tm.enumeration.Status;
import org.example.tm.util.DateFormatter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class Task extends AbstractEntity implements ComparableEntity {

    @NotNull
    private Status status = Status.PLANNED;

    @NotNull
    private String description = "";

    @NotNull
    private String startDate = DateFormatter.convertDateToString(getCreationDate());

    @Nullable
    private String endDate = null;

    @NotNull
    private String projectId;

    @NotNull
    private String userId;

    @Override
    public String toString() {
        return "==============================\n" +
                "task name = " + getName() + '\n' +
                "task id = " + getId() + '\n' +
                "project id = " + projectId + '\n' +
                "description = " + getDescription() + '\n' +
                "creation date = " + getCreationDate() + '\n' +
                "start date = " + startDate + '\n' +
                "end date = " + endDate + '\n' +
                "==============================";
    }

}
