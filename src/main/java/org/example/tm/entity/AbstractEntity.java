package org.example.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractEntity {

    @NotNull
    private String id = UUID.randomUUID().toString();

    @NotNull
    private String name;

    @NotNull
    private Date creationDate = new Date();

}
