package org.example.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import lombok.*;
import org.jetbrains.annotations.NotNull;


@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractEntity {

    @NotNull
    private String id = UUID.randomUUID().toString();

    @NotNull
    private String name;

}
