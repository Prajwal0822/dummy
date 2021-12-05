package com.gvnc.sample.web.controller;

import com.gvnc.sample.web.model.Workspace;
import com.gvnc.sample.web.service.WorkspaceService;
import com.gvnc.sample.web.util.StringUtil;
import com.gvnc.sample.web.model.response.OperationResponse;
import com.gvnc.sample.web.model.response.ResponseStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/workspaces", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkspaceController {

    @Autowired
    private WorkspaceService workspaceService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List getWorkspaces( ) {
        return workspaceService.findAll();
    }

    @RequestMapping(value = "/getWorkgroups", method = RequestMethod.GET)
    public List getWorkgroups() {
        return workspaceService.getWorkgroups();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {"application/json"})
    public OperationResponse createWorkspace(@RequestBody Workspace workspace) {
        log.debug("Workspace update operation. New values " + StringUtil.logFormat(workspace, "workGroup", "name"));
        OperationResponse response = new OperationResponse();
        try {
            Workspace w = workspaceService.createWorkspace(workspace);
            response.setOperationStatus(ResponseStatusEnum.SUCCESS);
            response.setOperationMessage("workspace.create.success");
            response.setResponseObject(w);
        } catch (Exception e) {
            log.error("Workspace update operation failed.", e);
            response.setOperationStatus(ResponseStatusEnum.ERROR);
            response.setOperationMessage("workspace.create.error");
        }
        return response;
    }

    //@PreAuthorize("hasRole('Workspace')")
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = {"application/json"})
    public OperationResponse updateWorkspace(@RequestBody Workspace workspace) {
        log.debug("Workspace update operation. New values " + StringUtil.logFormat(workspace, "workGroup", "name"));
        OperationResponse response = new OperationResponse();
        try {
            Workspace w = workspaceService.updateWorkspace(workspace);
            response.setOperationStatus(ResponseStatusEnum.SUCCESS);
            response.setOperationMessage("workspace.update.success");
            response.setResponseObject(w);
        } catch (Exception e) {
            log.error("Workspace update operation failed.", e);
            response.setOperationStatus(ResponseStatusEnum.ERROR);
            response.setOperationMessage("workspace.update.error");
        }
        return response;
    }

    //@PreAuthorize("hasRole('coco')")
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = {"application/json"})
    public OperationResponse deleteWorkspace(@RequestBody Workspace workspace) {
        log.debug("Workspace delete operation. " + StringUtil.logFormat(workspace, "workspaceId", "workGroup", "name"));
        OperationResponse response = new OperationResponse();
        try {
            workspaceService.deleteWorkspace(workspace);
            response.setOperationStatus(ResponseStatusEnum.SUCCESS);
            response.setOperationMessage("workspace.delete.success");
        } catch (Exception e) {
            log.error("Workspace delete operation failed.", e);
            response.setOperationStatus(ResponseStatusEnum.ERROR);
            if(e instanceof DataIntegrityViolationException) {
                response.setOperationMessage("delete.failed.for.violation");
            } else {
                response.setOperationMessage("workspace.delete.error");
            }
        }
        return response;
    }
}
