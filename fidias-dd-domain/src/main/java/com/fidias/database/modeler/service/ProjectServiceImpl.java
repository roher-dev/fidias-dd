package com.fidias.database.modeler.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.NotImplementedException;

import com.fidias.database.modeler.api.dto.ProjectDto;
import com.fidias.database.modeler.api.dto.ProjectTableDto;
import com.fidias.database.modeler.api.dto.TableColumnDto;
import com.fidias.database.modeler.dao.ProjectDao;
import com.fidias.database.modeler.model.Project;
import com.fidias.database.modeler.transformer.ProjectDtoModelTransformer;
import com.fidias.database.modeler.xml.element.ProjectElement;
import com.fidias.database.modeler.xml.transformer.ProjectXmlTransformer;
import com.google.common.collect.Lists;

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

	@Override
	public List<ProjectDto> searchByidType(String type, Long id){
		
		List<Project> projects = this.projectDao.getProjects(type, id);
		if(projects == null){
			return null;
		}
		
		List<ProjectDto> list = this.projectDtoModelTransformer.transformAllTo(projects);
		
		List<ProjectTableDto> r = Lists.newArrayList();
		switch(type){
			case "proj":
				break;
			case "table":
				for(ProjectTableDto t : list.get(0).getTables()){
					if(t.getId().equals(id)){
						r.add(t);
					}
				}
				list.get(0).setTables(r);
				
				break;
			case "column":
				for(ProjectTableDto t : list.get(0).getTables()){
					List<TableColumnDto> rt = Lists.newArrayList();
					
					for(TableColumnDto c : t.getColumns()){
						if(c.getId().equals(id)){
							rt.add(c);
							t.setColumns(rt);
							r.add(t);
							list.get(0).setTables(r);
							break;
						}
					}
					
				}
				
				break;
			default:
				throw new NotImplementedException("this option was not implemented.");
		}
		
		return list;
	}
}
