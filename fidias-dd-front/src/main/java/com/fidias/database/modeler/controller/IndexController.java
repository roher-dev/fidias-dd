package com.fidias.database.modeler.controller;

import static org.slf4j.LoggerFactory.getLogger;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/html")
public class IndexController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(IndexController.class);
	
	private final String contextPath;

	@Inject
	public IndexController(@Value("${app.context.path}") String contextPath) {
		super();
		this.contextPath = contextPath;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(){
	    ModelAndView mav = new ModelAndView("index");
        mav.addObject("contextPath", this.contextPath);
        
        return mav;
    }

	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
}
