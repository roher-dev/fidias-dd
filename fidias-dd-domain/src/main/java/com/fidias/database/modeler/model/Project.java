package com.fidias.database.modeler.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;

@Entity
@Table(name = "PROJECT")
public class Project extends Identificable {

	private static final long serialVersionUID = 1L;
	@Column(name = "NAME", length = 100)
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	private List<ProjectTable> tables = Lists.newArrayList();
	
	public Project() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProjectTable> getTables() {
		return tables;
	}

	public void setTables(List<ProjectTable> tables) {
		this.tables = tables;
	}
	
	public void addTable(ProjectTable projectTable){
		if(projectTable != null){
			this.tables.add(projectTable);
		}
	}
	
}
