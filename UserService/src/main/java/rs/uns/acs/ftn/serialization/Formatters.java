package rs.uns.acs.ftn.serialization;

import java.io.IOException;
import java.text.ParseException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		registry.addFormatterForFieldType(ZonedDateTime.class, new ZonedDateTimeFormatter(dateFormat) );
		registry.addFormatterForFieldType(GeoJsonPolygon.class, new GeoJsonPolygonFormatter());
		registry.addFormatterForFieldType(ZoneId.class, new ZoneIdFormatter());
	}

	/**
	 * ZonedDateTime formatter
	 * */
	public static class ZonedDateTimeFormatter implements Formatter<ZonedDateTime> {

		private static String dateFormat;
		
		public ZonedDateTimeFormatter(String dateFormat) {
			super();
			ZonedDateTimeFormatter.dateFormat = dateFormat;
		}
		
		@Override
		public String print(ZonedDateTime arg0, Locale arg1) {
			return null;
		}

		@Override
		public ZonedDateTime parse(String text, Locale locale) throws ParseException {
			DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern(dateFormat);
			ZonedDateTime zonedDateTime = ZonedDateTime.parse(
					text,
					dateTimeFormatter 
			);
			return zonedDateTime;
		}		
	}
	
	/**
	 * ZoneId formatter
	 * */
	public static class ZoneIdFormatter implements Formatter<ZoneId> {
		
		public ZoneIdFormatter() {
			super();
		}
		
		@Override
		public String print(ZoneId arg0, Locale arg1) {
			return null;
		}

		@Override
		public ZoneId parse(String text, Locale locale) throws ParseException {
			return ZoneId.of(text);
		}		
	}
	
	/**
	 * GeoJsonPolygon formatter
	 * */
	public static class GeoJsonPolygonFormatter implements Formatter<GeoJsonPolygon> {

		@Override
		public String print(GeoJsonPolygon object, Locale locale) {
			return null;
		}

		@Override
		public GeoJsonPolygon parse(String text, Locale locale) throws ParseException {
			JsonNode node;
			try {
				node = (new ObjectMapper()).readTree(text);
				Iterator<JsonNode> itGlobal = node.elements();
				
				List<Point> points = new ArrayList<Point>();
				List<Double> coords = new ArrayList<Double>();
				
				while(itGlobal.hasNext()){
					JsonNode elementGlobal = itGlobal.next();
					Iterator<JsonNode> itLocal = elementGlobal.elements();
					coords.clear();
					while(itLocal.hasNext()){
						JsonNode elementLocal = itLocal.next();
						coords.add(Double.parseDouble(elementLocal.asText()));
					}
					try{
						
						points.add(new Point(coords.get(0), coords.get(1)));
						
					}catch(IndexOutOfBoundsException e){
						continue;
					}
				}
				return new GeoJsonPolygon(points);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
