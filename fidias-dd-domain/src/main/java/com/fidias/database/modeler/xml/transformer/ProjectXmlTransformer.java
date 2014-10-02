package com.fidias.database.modeler.xml.transformer;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.fidias.database.modeler.model.ColumnTable;
import com.fidias.database.modeler.model.Project;
import com.fidias.database.modeler.model.ProjectTable;
import com.fidias.database.modeler.xml.element.ColumnElement;
import com.fidias.database.modeler.xml.element.ProjectElement;
import com.fidias.database.modeler.xml.element.TableElement;

@Named
public class ProjectXmlTransformer {

	private boolean siNoToBoolean(String value){
		if(StringUtils.equalsIgnoreCase(value, "si")){
			return true;
		}
		
		return false;
	}
	
	public Project transform(ProjectElement p){
		Project r = new Project();
		r.setName(p.getDbName());
		for(TableElement t : p.getTables().getTableElements()){
			ProjectTable pt = new ProjectTable();
			pt.setName(t.getName());
			for(ColumnElement c : t.getColumns().getColumns()){
				ColumnTable ct = new ColumnTable();
				ct.setDescription(c.getDescription());
				ct.setFk(this.siNoToBoolean(c.getFk()));
				ct.setName(c.getName());
				ct.setNullable(this.siNoToBoolean(c.getNullable()));
				ct.setPk(this.siNoToBoolean(c.getPk()));
				ct.setSize(c.getSize());
				ct.setType(c.getType());
				ct.setUnique(this.siNoToBoolean(c.getUnique()));
				ct.setProjectTable(pt);
				pt.addColumn(ct);
			}
			pt.setProject(r);
			r.addTable(pt);
		}
		
		return r;
	}
	
}
