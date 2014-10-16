package com.fidias.database.modeler.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class ProjectDto extends BasicOutputDto {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	private List<ProjectTableDto> tables = Lists.newArrayList();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProjectTableDto> getTables() {
		return tables;
	}

	public void setTables(List<ProjectTableDto> tables) {
		this.tables = tables;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
