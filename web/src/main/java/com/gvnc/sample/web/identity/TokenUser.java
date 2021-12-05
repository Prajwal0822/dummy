package com.gvnc.sample.web.identity;

import com.gvnc.sample.web.model.user.Role;
import org.springframework.security.core.authority.AuthorityUtils;
import com.gvnc.sample.web.model.user.User;

import java.util.Set;

public class TokenUser extends org.springframework.security.core.userdetails.User {
    private User user;

    //For returning a normal user
    public TokenUser(User user) {
        super(user.getUserId(), user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getUserGroup().getRolesArray()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
