package com.fidias.database.modeler.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class ProjectResponseDto extends BasicOutputDto {

	private static final long serialVersionUID = 1L;
	
	private List<ProjectDto> projectList = Lists.newArrayList();

	public List<ProjectDto> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<ProjectDto> projectList) {
		this.projectList = projectList;
	}

	public void addProject(ProjectDto projectDto){
		if(projectDto != null){
			this.projectList.add(projectDto);
		}
	}
}
