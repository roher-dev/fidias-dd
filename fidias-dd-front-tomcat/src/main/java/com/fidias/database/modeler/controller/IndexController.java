package com.fidias.database.modeler.controller;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class IndexController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(IndexController.class);
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
}
