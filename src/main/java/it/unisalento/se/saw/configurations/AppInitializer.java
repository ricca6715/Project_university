package it.unisalento.se.saw.configurations;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {WebAppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String [] {"/"}; //tutte le richieste con quella stringa sono soddisfatte dal servlet dispatcher
	}
	
	//Configuration used to upload a file
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		super.customizeRegistration(registration);
		final String LOCATION = "C:\\Users\\ricca\\Desktop\\Progetto\\temp"; // Temporary location where files will be stored
		final long MAX_FILE_SIZE = 5242880; // 5MB : Max file size.
		final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multipart.
		final int FILE_SIZE_THRESHOLD = 0;
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
				LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
		
		registration.setMultipartConfig(multipartConfigElement);
	}

}
