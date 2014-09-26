package com.fidias.database.modeler.service;

import java.util.List;

import com.fidias.database.modeler.api.dto.BasicDto;
import com.fidias.database.modeler.model.Model;

public interface ModelDtoService<E extends Model, O extends BasicDto> 
	extends BasicService {

	O searchById(Long id);
	
	List<O> searchAll();
	
	boolean insert(O dto);
	
	boolean remove(Long id);
}
