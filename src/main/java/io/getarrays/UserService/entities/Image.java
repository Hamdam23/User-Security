package io.getarrays.UserService.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    private Long id;

    private String location;

    private String imageName;

    public Image(String imageName, String location){
        this.imageName = imageName;
        this.location = location;
    }
}
