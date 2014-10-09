package com.fidias.database.modeler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "COLUMN_TABLE")
public class ColumnTable extends Identifiable {

	private static final long serialVersionUID = 1L;

	@Column(name = "NAME", length = 100)
	private String name;
	@Column(name = "TYPE")
	private String type;
	@Column(name = "SIZE")
	private int size;
	@Column(name = "IS_UNIQUE", nullable = false)
	private boolean unique;
	@Column(name = "IS_NULLABLE", nullable = false)
	private boolean nullable;
	@Column(name = "IS_PK", nullable = false)
	private boolean pk;
	@Column(name = "IS_FK", nullable = false)
	private boolean fk;
	@Column(name = "DESCRIPTION", length = 200)
	private String description;
	/*@ManyToOne(cascade = CascadeType.ALL, optional = false)*/
	@Transient
	private ProjectTable projectTable;
	
	public ColumnTable() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public boolean isPk() {
		return pk;
	}

	public void setPk(boolean pk) {
		this.pk = pk;
	}

	public boolean isFk() {
		return fk;
	}

	public void setFk(boolean fk) {
		this.fk = fk;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProjectTable getProjectTable() {
		return projectTable;
	}

	public void setProjectTable(ProjectTable projectTable) {
		this.projectTable = projectTable;
	}
	
}
