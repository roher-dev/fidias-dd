package com.fidias.database.modeler.transformer;

import javax.inject.Inject;
import javax.inject.Named;

import com.fidias.database.modeler.api.dto.ProjectDto;
import com.fidias.database.modeler.model.Project;

@Named
public class ProjectDtoModelTransformer extends AbstractDtoModelTransformer<ProjectDto, Project> {

	private final TableDtoModelTransformer tableDtoModelTransformer;
	
	@Inject
	public ProjectDtoModelTransformer(
			TableDtoModelTransformer tableDtoModelTransformer) {
		super();
		this.tableDtoModelTransformer = tableDtoModelTransformer;
	}

	@Override
	protected Project doTransform(ProjectDto dto) {
		
		return null;
	}

	@Override
	protected ProjectDto doTransform(Project e, int depth) {
		ProjectDto dto = new ProjectDto();
		dto.setName(e.getName());
		dto.setTables(this.tableDtoModelTransformer.transformAllTo(e.getTables()));
		
		return dto;
	}


}
