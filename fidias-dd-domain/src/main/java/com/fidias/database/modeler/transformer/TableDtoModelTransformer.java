package com.fidias.database.modeler.transformer;

import javax.inject.Inject;
import javax.inject.Named;

import com.fidias.database.modeler.api.dto.ProjectTableDto;
import com.fidias.database.modeler.model.ProjectTable;

@Named
public class TableDtoModelTransformer extends
		AbstractDtoModelTransformer<ProjectTableDto, ProjectTable> {

	private final ColumnDtoModelTransformer columnDtoModelTransformer;
	
	@Inject
	public TableDtoModelTransformer(
			ColumnDtoModelTransformer columnDtoModelTransformer) {
		super();
		this.columnDtoModelTransformer = columnDtoModelTransformer;
	}

	@Override
	protected ProjectTable doTransform(ProjectTableDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ProjectTableDto doTransform(ProjectTable e, int depth) {
		ProjectTableDto dto = new ProjectTableDto();
		dto.setId(e.getId());
		dto.setName(e.getName());
		dto.setColumns(this.columnDtoModelTransformer.transformAllTo(e.getColumns()));
		
		return dto;
	}

}
