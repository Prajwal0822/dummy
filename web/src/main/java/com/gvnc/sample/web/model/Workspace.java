package com.gvnc.sample.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "workspace")
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer workspaceId;

    @Column(name="workGroup")
    @Getter @Setter
    private String workGroup;

    @Column(name="name")
    @Getter @Setter
    private String name;

    @Column(name="url", nullable = true)
    @Getter @Setter
    private String url;

    @Column(name="createDate")
    @Getter @Setter
    private Date createDate;
}
