package com.laan.tech.assessment.common;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@SuperBuilder(toBuilder = true)
public class BaseDto {
    @Serial
    private static final long serialVersionUID = -6802067245636533901L;
    private UUID id;
}
