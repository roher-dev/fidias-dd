package com.fidias.database.modeler.xml.processor;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public abstract class XmlProcessor<T> {

	protected abstract Class<T> getClazz();
	
	@SuppressWarnings("unchecked")
	public T process(InputStream inputStream){
		try {
			return (T) JAXBContext.newInstance(this.getClazz()).createUnmarshaller().unmarshal(inputStream);
		} catch (JAXBException e) {
			throw new RuntimeException("Unhandled error when it tried to parse xml file", e);
		}
	}
}
