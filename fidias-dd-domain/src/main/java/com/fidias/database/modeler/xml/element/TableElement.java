package com.fidias.database.modeler.xml.element;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;

@XmlRootElement(name = "table")
public class TableElement {

	private String name;
	
	private Columns columns;
	
	public TableElement() {
		super();
	}

	@XmlAttribute(name = "table_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@XmlRootElement(name = "columns")
	public static class Columns {
	
		private List<ColumnElement> columns = Lists.newArrayList();
		
		@XmlElementRef(name = "column")
		public List<ColumnElement> getColumns() {
			return columns;
		}
		
		public void setColumns(List<ColumnElement> columns) {
			this.columns = columns;
		}
		
	}

	public Columns getColumns() {
		return columns;
	}

	public void setColumns(Columns columns) {
		this.columns = columns;
	}
	
}
