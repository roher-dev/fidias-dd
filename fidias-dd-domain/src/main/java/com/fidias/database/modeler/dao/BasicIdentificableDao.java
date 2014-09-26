package com.fidias.database.modeler.dao;

import com.fidias.database.modeler.model.Identificable;

public interface BasicIdentificableDao<E extends Identificable> extends BasicDao<E>{
	
	E findById(Long id);
	
	boolean deleteById(Long id);
	
}
