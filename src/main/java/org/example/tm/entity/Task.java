package org.example.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Task extends UsersAbstractEntity {

    @NotNull
    private String projectId;

    @Override
    public String toString() {
        return "==============================\n" +
                "task name = " + getName() + '\n' +
                "task id = " + getId() + '\n' +
                "project id = " + projectId + '\n' +
                "description = " + getDescription() + '\n' +
                "creation date = " + getCreationDate() + '\n' +
                "start date = " + getStartDate() + '\n' +
                "end date = " + getEndDate() + '\n' +
                "==============================";
    }

}
