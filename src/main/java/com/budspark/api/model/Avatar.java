package com.budspark.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "`avatar`")
@Entity
public class Avatar {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`type`")
    private String type;

    @Lob
    @Column(name = "image_data", length = 1000)
    private byte[] imageData;

    @OneToOne(mappedBy = "avatar")
    @JsonBackReference
    private User user;

    public Avatar(String name, String type, byte[] imageData, User user) {
        this.name = name;
        this.type = type;
        this.imageData = imageData;
        this.user = user;
    }
}
