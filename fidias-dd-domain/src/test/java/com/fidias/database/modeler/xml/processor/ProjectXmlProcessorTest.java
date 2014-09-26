package com.fidias.database.modeler.xml.processor;

import static org.fest.assertions.Assertions.assertThat;

import java.io.InputStream;

import org.junit.Test;

import com.fidias.database.modeler.xml.element.ColumnElement;
import com.fidias.database.modeler.xml.element.ProjectElement;
import com.fidias.database.modeler.xml.element.TableElement;

public class ProjectXmlProcessorTest {

	private ProjectXmlProcessor target = new ProjectXmlProcessor();
	
	@Test
	public void process_ok(){
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("xml/fidias_dd_to_export.xml");
			ProjectElement r = this.target.process(is);
			assertThat(r).isNotNull();
			assertThat(r.getDbName()).isEqualTo("Proyecto1");
			assertThat(r.getTables().getTableElements().size()).isEqualTo(6);
			TableElement t0 = r.getTables().getTableElements().get(0);
			assertThat(t0.getName()).isEqualTo("Departamento");
			assertThat(t0.getColumns().getColumns().size()).isEqualTo(3);
			ColumnElement c0 = t0.getColumns().getColumns().get(0);
			assertThat(c0.getName()).isEqualTo("Departamento.id_departamento");
			assertThat(c0.getType()).isEqualTo("INTEGER");
			assertThat(c0.getSize()).isEqualTo(15);
			assertThat(c0.getUnique()).isEqualTo("NO");
			assertThat(c0.getNullable()).isEqualTo("NO");
			assertThat(c0.getPk()).isEqualTo("SI");
			assertThat(c0.getFk()).isEqualTo("NO");
			assertThat(c0.getDescription()).isEqualTo("identificador de departamento");
			
	}
}
