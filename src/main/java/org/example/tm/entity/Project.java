package org.example.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tm.enumeration.Status;
import org.example.tm.util.DateFormatter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Project extends AbstractEntity {

    @NotNull
    private Status status = Status.PLANNED;

    @NotNull
    private String description = "";

    @NotNull
    private String startDate = DateFormatter.convertDateToString(LocalDate.now());

    @Nullable
    private String endDate = null;

    @NotNull
    private String userId;

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
