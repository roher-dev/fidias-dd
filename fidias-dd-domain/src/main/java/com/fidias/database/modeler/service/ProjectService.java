package com.fidias.database.modeler.service;

import com.fidias.database.modeler.xml.element.ProjectElement;

public interface ProjectService extends BasicService {

	boolean insert(ProjectElement element);

}
