package com.fidias.database.modeler.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class ProjectTableDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	
	private List<TableColumnDto> columns = Lists.newArrayList();

	public ProjectTableDto() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TableColumnDto> getColumns() {
		return columns;
	}

	public void setColumns(List<TableColumnDto> columns) {
		this.columns = columns;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
