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
public class Project extends AbstractEntity {

    @NotNull
    private String description = "";

    @Nullable
    private Date startDate = null;

    @Nullable
    private Date endDate = null;

    @Nullable
    private String userId = null;

    @NotNull
//    private Status status = Status.PLANNED;

    @Override
    public String toString() {
        return "==============================\n" +
                "project name = " + getName() + '\n' +
                "project id = " + getId() + '\n' +
                "description = " + getDescription() + '\n' +
                "start date = " + startDate + "\n" +
                "end date = " + endDate + '\n' +
                "==============================";
    }

}
