package com.fidias.database.modeler.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.fidias.database.modeler.dao.ProjectDao;
import com.fidias.database.modeler.model.Project;
import com.fidias.database.modeler.xml.element.ProjectElement;
import com.fidias.database.modeler.xml.transformer.ProjectXmlTransformer;

@Named
public class ProjectServiceImpl extends BasicServiceImpl implements
		ProjectService {

	private final ProjectXmlTransformer projectXmlTransformer;
	private final ProjectDao projectDao;

	@Inject
	public ProjectServiceImpl(ProjectXmlTransformer projectXmlTransformer,
			ProjectDao projectDao) {
		super();
		this.projectXmlTransformer = projectXmlTransformer;
		this.projectDao = projectDao;
	}

	@Override
	public boolean insert(ProjectElement element) {
		Project project = this.projectXmlTransformer.transform(element);
		
		this.projectDao.saveOrUpdate(project);
		
		return false;
	}
	
}
