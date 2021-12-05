package com.gvnc.sample.web.config;

import com.gvnc.sample.web.model.Workspace;
import com.gvnc.sample.web.repository.WorkspaceRepository;
import com.gvnc.sample.web.model.user.Role;
import com.gvnc.sample.web.model.user.User;
import com.gvnc.sample.web.model.user.UserGroup;
import com.gvnc.sample.web.repository.RoleRepository;
import com.gvnc.sample.web.repository.UserGroupRepository;
import com.gvnc.sample.web.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class InitDatabase implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Override
    public void run(String... strings) throws Exception {

        log.debug("Data insert started.");

        Role workspaceRole = new Role();
        workspaceRole.setRoleName("Workspace");
        roleRepository.save(workspaceRole);

        Role mockDataRole = new Role();
        mockDataRole.setRoleName("MockData");
        roleRepository.save(mockDataRole);

        Role testCasesRole = new Role();
        testCasesRole.setRoleName("TestCases");
        roleRepository.save(testCasesRole);

        Role settingsRole = new Role();
        settingsRole.setRoleName("Settings");
        roleRepository.save(settingsRole);

        UserGroup adminGroup = new UserGroup();
        adminGroup.setGroupName("Admin");
        List roleList = new ArrayList<Role>();
        roleList.add(workspaceRole);
        roleList.add(mockDataRole);
        roleList.add(testCasesRole);
        roleList.add(settingsRole);

        adminGroup.setRoles(roleList);
        userGroupRepository.save(adminGroup);

        User user = new User();
        user.setFirstName("harry");
        user.setLastName("kane");
        user.setUserId("admin");
        user.setUserGroup(adminGroup);
        user.setPassword(passwordEncoder.encode("admin"));
        userRepository.save(user);

        UserGroup developerGroup = new UserGroup();
        developerGroup.setGroupName("Developer");
        roleList = new ArrayList<Role>();
        roleList.add(workspaceRole);
        roleList.add(mockDataRole);
        roleList.add(testCasesRole);

        developerGroup.setRoles(roleList);
        userGroupRepository.save(developerGroup);

        user = new User();
        user.setFirstName("james");
        user.setLastName("harden");
        user.setUserId("demo");
        user.setUserGroup(developerGroup);
        user.setPassword(passwordEncoder.encode("demo"));
        userRepository.save(user);

        Workspace workspace = new Workspace();
        workspace.setWorkspaceId(1);
        workspace.setName("101396903980");
        workspace.setWorkGroup("Bireysel.GPON");
        workspace.setUrl("http://10.35.63.197:800/startup.do?subscriberid=10990");
        workspace.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        workspaceRepository.save(workspace);

        log.debug("Data insert completed.");
    }
}
