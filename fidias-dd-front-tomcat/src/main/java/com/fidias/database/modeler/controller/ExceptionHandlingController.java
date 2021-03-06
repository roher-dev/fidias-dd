package com.fidias.database.modeler.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fidias.database.modeler.api.dto.BasicOutputDto;
import com.fidias.database.modeler.api.dto.BasicOutputDto.BasicOutputType;

public abstract class ExceptionHandlingController {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public @ResponseBody BasicOutputDto illegalArgumentError(
			HttpServletRequest req, Exception exception) {
		this.getOwnLogger().error(
				"Url: " + req.getRequestURL() + " raised: "
						+ exception.getMessage(), exception);

		BasicOutputDto dto = new BasicOutputDto(BasicOutputType.ILLEGAL_ARGUMENTS.getId());
		dto.setMessage("Bad Request, illegal arguments received.");
		dto.addCause(exception.getMessage());

		return dto;
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public @ResponseBody BasicOutputDto unhandledError(HttpServletRequest req,
			Exception exception) {
		this.getOwnLogger().error(
				"Url: " + req.getRequestURL() + " raised: "
						+ exception.getMessage(), exception);

		BasicOutputDto dto = new BasicOutputDto(BasicOutputType.UNHANDLED_ERROR.getId());
		dto.setMessage("An unhandled error has ocurred on FIDIAS DD.");
		dto.addCause(exception.getMessage());

		return dto;
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RuntimeException.class)
	public @ResponseBody BasicOutputDto unhandledRuntimeError(HttpServletRequest req,
			Exception exception) {
		this.getOwnLogger().error(
				"Url: " + req.getRequestURL() + " raised: "
						+ exception.getMessage(), exception);

		BasicOutputDto dto = new BasicOutputDto(BasicOutputType.UNHANDLED_ERROR.getId());
		dto.setMessage("An unhandled error has ocurred on FIDIAS DD.");
		dto.addCause(exception.getMessage());

		return dto;
	}

	protected abstract Logger getOwnLogger();

	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@ExceptionHandler(ConstraintViolationException.class)
	public @ResponseBody BasicOutputDto unhandledConstraintError(HttpServletRequest req,
			Exception exception) {
		ConstraintViolationException ce = (ConstraintViolationException)exception;
		this.getOwnLogger().error(
				"Url: " + req.getRequestURL() + " raised: "
						+ ce.getSQLException().getMessage(), exception);

		BasicOutputDto dto = new BasicOutputDto(BasicOutputType.SQL_CONSTRAINT_ERROR.getId());
		dto.setMessage(String.format("Se ha producido un error de clave duplicada al querer insertar el proyecto. FIDIAS DDM. SQLState: %s", 
				ce.getSQLState()));
		dto.addCause(ce.getSQLException().getMessage());

		return dto;
	}
	
}
