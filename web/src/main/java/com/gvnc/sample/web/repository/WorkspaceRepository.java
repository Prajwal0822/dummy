package com.gvnc.sample.web.repository;

import com.gvnc.sample.web.model.Workspace;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkspaceRepository extends CrudRepository<Workspace, Integer>{

    List<Workspace> findAll();

    @Query("SELECT distinct w.workGroup FROM Workspace w")
    List<String> findDistinctWorkgroups();
}
