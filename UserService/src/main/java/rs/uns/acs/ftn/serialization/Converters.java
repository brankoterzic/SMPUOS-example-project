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
	
	public static class DateToStringConverter implements Converter<Date, String> {
		
		private String dateFormat;
		
		public DateToStringConverter(String dateFormat) {
			super();
			this.dateFormat = dateFormat;
		}
		
		@Override
	    public String convert(Date source) {
	    	SimpleDateFormat format = new SimpleDateFormat(dateFormat);
	    	return format.format(source);
		}
	}

	public static class StringTOStringConverter implements Converter<String, String> {
		
		public StringTOStringConverter() {
			super();
		}
		
		@Override
	    public String convert(String source) {
	    	return source.toString();
		}
	}
}
