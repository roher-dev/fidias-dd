package com.fidias.database.modeler.service;

import static org.fest.assertions.Assertions.assertThat;

import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fidias.database.modeler.dao.ProjectDao;
import com.fidias.database.modeler.model.Project;
import com.fidias.database.modeler.xml.element.ProjectElement;
import com.fidias.database.modeler.xml.processor.ProjectXmlProcessor;

@ContextConfiguration(locations = {"classpath:hibernate-db-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ProjectServiceTest {

	@Inject
	private ProjectService target;
	@Inject
	private ProjectDao projectDao;
	@Inject
	private ProjectXmlProcessor projectXmlProcessor;
	
	@Test
	public void insert_ok(){
		ProjectElement e = new ProjectElement();
		e.setDbName("proj1");
		this.target.insert(e);
		List<Project> r = this.projectDao.findByCriteria(Restrictions.eq("name", "proj1"));
		assertThat(r.size()).isEqualTo(1);
	}
	
	@Test
	public void insert_file_ok(){
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("xml/fidias_dd_to_export.xml");
		ProjectElement e = this.projectXmlProcessor.process(is);
		this.target.insert(e);
		List<Project> r = this.projectDao.findByCriteria(Restrictions.eq("name", "Proyecto1"));
		assertThat(r.size()).isEqualTo(1);
	}
	
}
