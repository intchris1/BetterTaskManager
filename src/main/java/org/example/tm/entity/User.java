package org.example.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tm.enumeration.RoleType;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User extends AbstractEntity {
    @NotNull
    private String password;
    @NotNull
    private RoleType displayName = RoleType.USER;

    @Override
    public String toString() {
        return "==============================\n" +
                "user name = " + getName() + '\n' +
                "user id = " + getId() + '\n' +
                "user type = " + getDisplayName() + '\n' +
                "creation date = " + getCreationDate() + '\n' +
                "==============================";
    }
}
