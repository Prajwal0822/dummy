package com.gvnc.sample.web.model.session;

import lombok.Data;

@Data
public class SessionItem {
    private String token;
    private String userId;
    private String firstName;
    private String lastName;
    private String[] roles;
}
