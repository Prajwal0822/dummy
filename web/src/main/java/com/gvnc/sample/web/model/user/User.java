package com.gvnc.sample.web.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.*;
import lombok.*;

@Entity
public class User {
    @Id
    @Getter @Setter
    private String userId;

    @Getter @Setter
    private String password = "";

    @Getter @Setter
    private String firstName = "";

    @Getter @Setter
    private String lastName ="";

    @OneToOne
    @JoinColumn(name = "groupId", referencedColumnName = "groupId")
    @JsonIgnore
    @Getter @Setter
    private UserGroup userGroup;

    public User() {
    }

    public User(String userId, String password, String firstName, String lastName, UserGroup userGroup){
        this.setUserId(userId);
        this.setPassword(new BCryptPasswordEncoder().encode(password));
        this.firstName=firstName;
        this.lastName=lastName;
        this.userGroup = userGroup;
    }

    public String getFullName(){
        return this.firstName + this.lastName;
    }
}
