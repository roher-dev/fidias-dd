package com.fidias.database.modeler.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.fidias.database.modeler.api.dto.ProjectDto;
import com.fidias.database.modeler.dao.ProjectDao;
import com.fidias.database.modeler.model.Project;
import com.fidias.database.modeler.transformer.ProjectDtoModelTransformer;
import com.fidias.database.modeler.xml.element.ProjectElement;
import com.fidias.database.modeler.xml.transformer.ProjectXmlTransformer;

@Named
public class ProjectServiceImpl extends BasicServiceImpl implements
		ProjectService {

	private final ProjectXmlTransformer projectXmlTransformer;
	private final ProjectDao projectDao;
	private final ProjectDtoModelTransformer projectDtoModelTransformer;

	@Inject
	public ProjectServiceImpl(ProjectXmlTransformer projectXmlTransformer,
			ProjectDao projectDao,
			ProjectDtoModelTransformer projectDtoModelTransformer) {
		super();
		this.projectXmlTransformer = projectXmlTransformer;
		this.projectDao = projectDao;
		this.projectDtoModelTransformer = projectDtoModelTransformer;
	}	

	@Override
	public boolean insert(ProjectElement element) {
		Project project = this.projectXmlTransformer.transform(element);
		
		this.projectDao.saveOrUpdate(project);
		
		return false;
	}
	
	@Override
	public List<ProjectDto> searchAll(){
		return this.projectDtoModelTransformer.transformAllTo(this.projectDao.findAll());
	}
	
}
