package com.fidias.database.modeler.xml.processor;

import javax.inject.Named;

import com.fidias.database.modeler.xml.element.ProjectElement;

@Named
public class ProjectXmlProcessor extends XmlProcessor<ProjectElement>{

	@Override
	protected Class<ProjectElement> getClazz() {
		return ProjectElement.class;
	}

}
