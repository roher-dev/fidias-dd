package com.fidias.database.modeler.dao;

import com.fidias.database.modeler.model.Identifiable;

public interface BasicIdentificableDao<E extends Identifiable> extends BasicDao<E>{
	
	E findById(Long id);
	
	boolean deleteById(Long id);
	
}
