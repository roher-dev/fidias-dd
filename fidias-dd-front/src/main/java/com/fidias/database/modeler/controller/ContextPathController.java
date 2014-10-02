package com.fidias.database.modeler.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Preconditions;

public abstract class ContextPathController extends ExceptionHandlingController {

	private final String contextPath;

	public ContextPathController(@Value("${app.context.path}") String contextPath) {
		super();
		this.contextPath = contextPath;
	}
	
	protected final String getContextPath() {
		return contextPath;
	}
	
	protected final ModelAndView getContextPathMav(final ModelAndView mav){
		Preconditions.checkArgument(mav != null, "mav is required");
		mav.addObject("contextPath", this.contextPath);
		
		return mav;
	}
	
	protected final ModelAndView getContextPathMav(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("contextPath", this.contextPath);
		
		return mav;
	}
	
}
