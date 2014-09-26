package com.fidias.database.modeler.dao;

import javax.inject.Named;

import com.fidias.database.modeler.model.Project;

@Named
public class ProjectDaoImpl extends BasicIdentificableDaoImpl<Project>
		implements ProjectDao {

	@Override
	protected Class<Project> getEntityClass() {
		return Project.class;
	}

}
