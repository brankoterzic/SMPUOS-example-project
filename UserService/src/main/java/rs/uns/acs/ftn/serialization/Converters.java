package rs.uns.acs.ftn.serialization;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class Converters {
	
	public static class StringToDateConverter implements Converter<String, Date> {
		
		private String dateFormat;
		
		public StringToDateConverter(String dateFormat) {
			super();
			this.dateFormat = dateFormat;
		}
		
		@Override
	    public Date convert(String source) {
	    	SimpleDateFormat format = new SimpleDateFormat(dateFormat);

			try {
				return format.parse(source);
			} catch (ParseException e) {
				return null;
			}
		}
	}
	

}
