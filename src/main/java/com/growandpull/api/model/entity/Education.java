package com.growandpull.api.model.entity;

import com.growandpull.api.model.enums.Degree;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "`education`")
public class Education {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "school", nullable = false)
    private String school;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "degree", nullable = false)
    @Enumerated(EnumType.STRING)
    private Degree degree;

    @Column(name = "startedAt", nullable = false)
    private LocalDate startedAt;

    @Column(name = "graduatedAt", nullable = false)
    private LocalDate graduatedAt;
}
