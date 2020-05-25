package org.example.tm.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tm.entity.AbstractEntity;
import org.example.tm.enumeration.RoleType;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
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
