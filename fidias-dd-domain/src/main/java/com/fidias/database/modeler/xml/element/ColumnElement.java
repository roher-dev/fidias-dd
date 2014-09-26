package com.fidias.database.modeler.xml.element;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "column")
public class ColumnElement {

	
	private String name;
	
	private String type;
	
	private Integer size;
	
	private String unique;
	
	private String nullable;
	
	private String pk;
	
	private String fk;
	
	private String description;

	public ColumnElement() {
		super();
	}

	@XmlElement(name = "column_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "column_type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@XmlElement(name = "column_size")
	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	@XmlElement(name = "is_unique")
	public String getUnique() {
		return unique;
	}

	public void setUnique(String unique) {
		this.unique = unique;
	}
	@XmlElement(name = "is_null")
	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}
	@XmlElement(name = "id_pk")
	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}
	@XmlElement(name = "is_fk")
	public String getFk() {
		return fk;
	}

	public void setFk(String fk) {
		this.fk = fk;
	}
	@XmlElement(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
