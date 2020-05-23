package org.example.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tm.enumeration.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Task extends AbstractEntity {

    @NotNull
    private Status status = Status.PLANNED;

    @NotNull
    private String description = "";

    @NotNull
    private Date startDate = new Date();

    @Nullable
    private Date endDate = null;

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
                "start date = " + startDate + '\n' +
                "end date = " + endDate + '\n' +
                "==============================";
    }

}
