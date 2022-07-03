package io.getarrays.UserService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String name;

    private String username;

    @JsonIgnore
    private String password;

    @ManyToOne(fetch = EAGER)
    private AppRole role;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "image_id")
    private Image image;
}
