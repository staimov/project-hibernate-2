package org.staimov.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
public class UpdateDetails {
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;
}
