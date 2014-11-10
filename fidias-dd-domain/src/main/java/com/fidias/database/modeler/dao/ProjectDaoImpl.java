package com.fidias.database.modeler.dao;

import java.util.List;

import javax.inject.Named;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.fidias.database.modeler.model.Project;

@Named
public class ProjectDaoImpl extends BasicIdentificableDaoImpl<Project>
		implements ProjectDao {

	@Override
	protected Class<Project> getEntityClass() {
		return Project.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getProjects(String type, Long id) {
		Criteria criteria = this.getCurrentSession().createCriteria(this.getEntityClass());

		if(StringUtils.equals(type, "proj")){
			criteria.add(Restrictions.eq("id", id));
		} else if(StringUtils.equals(type, "table")){
			criteria.createAlias("tables", "table");
			criteria.add(Restrictions.eq("table.id", id));
		} else if(StringUtils.equals(type, "column")){
			criteria.createAlias("tables", "table").createAlias("table.columns", "column");
			criteria.add(Restrictions.eq("column.id", id));
		} else {
			throw new NotImplementedException("this option was not implemented.");
		}
		
		return criteria.list();
	}

}
