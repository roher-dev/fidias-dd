package com.fidias.database.modeler.dao;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fidias.database.modeler.model.Project;

@ContextConfiguration(locations = {"classpath:hibernate-db-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ProjectDaoTest {

	@Inject
	private ProjectDao target;
	
	@Test
	public void saveOrUpdate_ok(){
		Project project = new Project();
		project.setName("proj1");
		this.target.saveOrUpdate(project);
		
		List<Project> result = this.target.findByCriteria(Restrictions.eq("name", "proj1"));
		assertThat(result.size()).isEqualTo(1);
	}
	
}
