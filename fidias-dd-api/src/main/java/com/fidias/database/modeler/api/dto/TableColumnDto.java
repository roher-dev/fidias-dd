package com.fidias.database.modeler.api.dto;

public class TableColumnDto extends BasicDto {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String type;
	private int size;
	private boolean unique;
	private boolean nullable;
	private boolean pk;
	private boolean fk;
	private String description;

	public TableColumnDto() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
