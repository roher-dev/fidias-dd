package com.fidias.database.modeler.dao;

import java.util.List;

import com.fidias.database.modeler.model.Project;

public interface ProjectDao extends BasicIdentificableDao<Project> {

	List<Project> getProjects(String type, Long id);
}
