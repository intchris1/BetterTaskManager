package org.example.tm.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tm.entity.AbstractEntity;
import org.example.tm.enumeration.RoleType;
import org.example.tm.util.Md5Custom;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractEntity {
    @NotNull
    private String password;
    @NotNull
    private org.example.tm.enumeration.RoleType displayName = RoleType.USER;

    public void setPassword(String password) {
        this.password = Md5Custom.md5Custom(password);
    }

    @Override
    public String toString() {
        return "==============================\n" +
                "user name = " + getName() + '\n' +
                "user id = " + getId() + '\n' +
                "creation date = " + getCreationDate() + '\n' +
                "==============================";
    }
}
