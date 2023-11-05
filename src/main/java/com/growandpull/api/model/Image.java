package com.growandpull.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "`image`")
@Entity
public class Image {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "`name`", nullable = false)
    private String name;

    @Column(name = "`type`", nullable = false)
    private String type;

    @Lob
    @Column(name = "image_data", length = 1000, nullable = false)
    private byte[] imageData;

    @OneToOne(mappedBy = "image")
    @JsonBackReference
    private Startup startup;

    public Image(String name, String type, byte[] imageData, Startup startup) {
        this.name = name;
        this.type = type;
        this.imageData = imageData;
        this.startup = startup;
    }

    public Image(String name, String type, byte[] imageData) {
        this.name = name;
        this.type = type;
        this.imageData = imageData;
    }
}
