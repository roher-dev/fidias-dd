package com.fidias.database.modeler.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.collect.Lists;

@Entity
@Table(name = "PROJECT_TABLE")
public class ProjectTable extends Identifiable {

	private static final long serialVersionUID = 1L;
	@Column(name = "NAME", length = 100)
	private String name;
	@OneToMany(cascade = CascadeType.ALL/*, mappedBy = "projectTable"*/)
	private List<ColumnTable> columns = Lists.newArrayList();
	/*@ManyToOne(cascade = CascadeType.ALL, optional = false)*/
	@Transient
	private Project project;

	public ProjectTable() {
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ColumnTable> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnTable> columns) {
		this.columns = columns;
	}
	
	public void addColumn(ColumnTable columnTable){
		if(columnTable != null){
			this.columns.add(columnTable);
		}
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
}
