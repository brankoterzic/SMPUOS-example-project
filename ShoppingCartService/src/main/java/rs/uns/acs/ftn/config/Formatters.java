package rs.uns.acs.ftn.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class Formatters extends WebMvcConfigurerAdapter {

	@Value("${general.dateFormat}")
	private String dateFormat;
	
	/**
	 * Register formatters
	 * */
	@Override
	public void addFormatters( final FormatterRegistry registry ) {
		registry.addFormatterForFieldType(Date.class, new DateFormatter(dateFormat) );
	}

}
