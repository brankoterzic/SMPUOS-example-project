package rs.uns.acs.ftn.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class Converters {

	public static class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {
		
		private String dateFormat;
		
		public ZonedDateTimeToDateConverter(String dateFormat) {
			super();
			this.dateFormat = dateFormat;
		}
		
		@Override
	    public Date convert(ZonedDateTime zonedDateTime) {
	    	SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			try {
				return format.parse(zonedDateTime.format(DateTimeFormatter.ofPattern(dateFormat)));
			} catch (ParseException e) {
				return null;
			}
	    }
	}

	public static class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {

		private String dateFormat;
		
		public DateToZonedDateTimeConverter(String dateFormat) {
			super();
			this.dateFormat = dateFormat;
		}
		
		@Override
	    public ZonedDateTime convert(Date source) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
	        return ZonedDateTime.parse(format.format(source), dateTimeFormatter).withZoneSameInstant(ZoneId.of("Z"));
	    }
	}
	
	public static class ZoneIdToStringConverter implements Converter<ZoneId, String> {
			
			
			public ZoneIdToStringConverter() {
				super();
			}
			
			@Override
		    public String convert(ZoneId zoneId) {
				return zoneId.toString();
		    }
		}

	public static class StringToZoneIdConverter implements Converter<String, ZoneId> {
		
		public StringToZoneIdConverter() {
			super();
		}
		
		@Override
	    public ZoneId convert(String source) {
			return ZoneId.of(source);
		}
	}
	

}
