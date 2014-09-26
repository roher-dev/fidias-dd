package com.fidias.database.modeler.xml.element;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;

@XmlRootElement(name = "database")
public class ProjectElement {
	
	private String dbName;
	private Tables tables;

	public ProjectElement() {
		super();
	}

	@XmlElement(name = "db_name")
	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
	@XmlRootElement(name = "tables")
	public static class Tables {

		private List<TableElement> tableElements = Lists.newArrayList();

		@XmlElementRef(name = "table")
		public List<TableElement> getTableElements() {
			return tableElements;
		}

		public void setTableElements(List<TableElement> tableElements) {
			this.tableElements = tableElements;
		}
		
	}

	public Tables getTables() {
		return tables;
	}

	public void setTables(Tables tables) {
		this.tables = tables;
	}
	
}
