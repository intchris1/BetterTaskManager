package org.example.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Task extends AbstractEntity {

    @NotNull
    private String description = "";

    @Nullable
    private Date dateStart = null;

    @Nullable
    private Date dateFinish = null;

    @Nullable
    private String projectId = null;

    @Nullable
    private String userId = null;

    @NotNull
//    private Status status = Status.PLANNED;

    @Override
    public String toString() {
        return "==============================\n" +
                "task name = " + getName() + '\n' +
                "task id = " + getId() + '\n' +
                "project id = " + projectId + '\n' +
                "description = " + getDescription() + '\n' +
                "start date = " + getDateStart() + '\n' +
                "end date = " + getDateFinish() + '\n' +
                "==============================";
    }

}
