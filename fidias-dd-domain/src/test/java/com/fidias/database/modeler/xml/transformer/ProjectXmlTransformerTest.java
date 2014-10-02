package com.fidias.database.modeler.xml.transformer;

import static org.fest.assertions.Assertions.assertThat;

import java.io.InputStream;

import org.junit.Test;

import com.fidias.database.modeler.model.ColumnTable;
import com.fidias.database.modeler.model.Project;
import com.fidias.database.modeler.model.ProjectTable;
import com.fidias.database.modeler.xml.element.ProjectElement;
import com.fidias.database.modeler.xml.processor.ProjectXmlProcessor;

public class ProjectXmlTransformerTest {

	private ProjectXmlProcessor projectXmlProcessor = new ProjectXmlProcessor();
	private ProjectXmlTransformer target = new ProjectXmlTransformer();
	
	@Test
	public void transform_ok(){
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("xml/fidias_dd_to_export.xml");
		ProjectElement r = this.projectXmlProcessor.process(is);
		Project project = this.target.transform(r);
		assertThat(project).isNotNull();
		assertThat(project.getName()).isEqualTo("Proyecto1");
		assertThat(project.getTables().size()).isEqualTo(6);
		ProjectTable t0 = project.getTables().get(0);
		assertThat(t0.getName()).isEqualTo("Departamento");
		assertThat(t0.getColumns().size()).isEqualTo(3);
		ColumnTable c0 = t0.getColumns().get(0);
		assertThat(c0.getName()).isEqualTo("Departamento.id_departamento");
		assertThat(c0.getType()).isEqualTo("INTEGER");
		assertThat(c0.getSize()).isEqualTo(15);
		assertThat(c0.isUnique()).isFalse();
		assertThat(c0.isNullable()).isFalse();
		assertThat(c0.isPk()).isTrue();
		assertThat(c0.isFk()).isFalse();
		assertThat(c0.getDescription()).isEqualTo("identificador de departamento");
	}
	
}
