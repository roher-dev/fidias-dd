package com.fidias.database.modeler.service;

import java.util.List;

import com.fidias.database.modeler.api.dto.ProjectDto;
import com.fidias.database.modeler.xml.element.ProjectElement;

public interface ProjectService extends BasicService {

	boolean insert(ProjectElement element);

	List<ProjectDto> searchAll();
	
	List<ProjectDto> searchByidType(String type, Long id);
	
	boolean remove(Long id);
	
	List<ProjectDto> searchByNameLike(String name);
	
	List<ProjectDto> searchByName(String name);
	
}
