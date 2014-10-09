package com.fidias.database.modeler.service;

import javax.transaction.Transactional;

@Transactional
public abstract class BasicServiceImpl implements BasicService {

	public BasicServiceImpl() {
		super();
	}
}
