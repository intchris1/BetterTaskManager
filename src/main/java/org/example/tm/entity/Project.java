package org.example.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Project extends UsersAbstractEntity {

    @Override
    public String toString() {
        return "==============================\n" +
                "project name = " + getName() + '\n' +
                "project id = " + getId() + '\n' +
                "description = " + getDescription() + '\n' +
                "creation date = " + getCreationDate() + '\n' +
                "start date = " + getStartDate() + "\n" +
                "end date = " + getEndDate() + '\n' +
                "==============================";
    }

}
