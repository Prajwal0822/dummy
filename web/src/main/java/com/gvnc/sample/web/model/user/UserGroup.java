package com.gvnc.sample.web.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Entity
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private String groupId;

    @Getter @Setter
    private String groupName = "";

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "groupRole",
            joinColumns = { @JoinColumn(name = "groupId", referencedColumnName = "groupId") },
            inverseJoinColumns = { @JoinColumn(name = "roleId", table = "role", referencedColumnName = "roleId") })
    @Getter @Setter
    private List<Role> roles= new ArrayList<Role>();

    public UserGroup() {
    }

    public UserGroup(String groupId, String groupName, List<Role> roles) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.roles = roles;
    }

    public String[] getRolesArray(){
        if(roles!= null && roles.size()>0) {
            final String [] rolesArray = new String[roles.size()];
            IntStream.range(0, roles.size())
                    .forEach(idx -> rolesArray[idx]=roles.get(idx).getRoleName());
            return rolesArray;
        }
        return null;
    }
}
