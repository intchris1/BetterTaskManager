package org.example.tm.session;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.tm.entity.user.User;

@NoArgsConstructor
@Getter
@Setter
public class Session {
    private User user;
}
