package com.gvnc.sample.web.service;

import com.gvnc.sample.web.model.Workspace;
import com.gvnc.sample.web.repository.WorkspaceRepository;
import com.gvnc.sample.web.util.DatabaseUtil;
import com.gvnc.sample.web.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WorkspaceService {

    @Autowired
    private WorkspaceRepository workspaceRepository;

    public Workspace createWorkspace(Workspace workspace) {
        workspace.setCreateDate(DatabaseUtil.getNow());
        return workspaceRepository.save(workspace);
    }

    public Workspace updateWorkspace(Workspace workspaceToUpdate) throws Exception{
        Workspace workspace = workspaceRepository.findById(workspaceToUpdate.getWorkspaceId()).orElse(null);
        if(workspace == null) {
            log.error("Workspace not found. " + StringUtil.logFormat(workspaceToUpdate, "workGroup", "name"));
            throw new Exception("Workspace not found.");
        }

        workspace.setName(workspaceToUpdate.getName());
        workspace.setWorkGroup(workspaceToUpdate.getWorkGroup());
        workspace.setUrl(workspaceToUpdate.getUrl());

        return workspaceRepository.save(workspace);
    }

    public void deleteWorkspace(Workspace workspaceToDelete){
        Workspace workspace = workspaceRepository.findById(workspaceToDelete.getWorkspaceId()).orElse(null);
        if(workspace == null) {
            log.error("Workspace not found. " + StringUtil.logFormat(workspaceToDelete, "workGroup", "name"));
            return;
        }
        workspaceRepository.deleteById(workspace.getWorkspaceId());
    }

    public List<Workspace> findAll() {
        return workspaceRepository.findAll();
    }

    public List<String> getWorkgroups(){
        return workspaceRepository.findDistinctWorkgroups();
    }
}
