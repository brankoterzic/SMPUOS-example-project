package rs.uns.acs.ftn.serialization;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class SerializationDeserialization {

	public static class GeoJsonPointSerializer extends JsonSerializer<GeoJsonPoint> {
		@Override
		public void serialize(GeoJsonPoint value, JsonGenerator jgen, SerializerProvider provider)
				throws IOException, JsonProcessingException {
			jgen.writeStartObject();
			jgen.writeStringField("type", "Point");
			jgen.writeArrayFieldStart("coordinates");
			jgen.writeNumber(value.getX());
			jgen.writeNumber(value.getY());
			jgen.writeEndArray();
			jgen.writeEndObject();
		}
	}

	public static class GeoJsonPolygonSerializer extends JsonSerializer<GeoJsonPolygon> {
		@Override
		public void serialize(GeoJsonPolygon value, JsonGenerator jgen, SerializerProvider provider)
				throws IOException, JsonProcessingException {
			jgen.writeStartObject();
			jgen.writeStringField("type", "Polygon");
			jgen.writeArrayFieldStart("coordinates");
			try{
				for(Point point: value.getCoordinates().get(0).getCoordinates()){
					jgen.writeStartArray();
					jgen.writeNumber(point.getX());
					jgen.writeNumber(point.getY());
					jgen.writeEndArray();
				}
				jgen.writeEndArray();
				jgen.writeEndObject();
			}catch(IndexOutOfBoundsException e){
				jgen.writeNull();
				jgen.writeEndArray();
				jgen.writeEndObject();
			}

		}
	}
	
	public static class GeoJsonPointDeserialization extends JsonDeserializer<GeoJsonPoint> {

		@Override
		public GeoJsonPoint deserialize(JsonParser jsonParser, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			ObjectCodec oc = jsonParser.getCodec();
			JsonNode node = oc.readTree(jsonParser);
			JsonNode coordinates = node.get("coordinates");
			Iterator<JsonNode> it = coordinates.elements();
			List<Double> coords = new ArrayList<Double>();
			while(it.hasNext()){
				
				JsonNode element = it.next();
				coords.add(Double.parseDouble(element.asText()));
			}
			return new GeoJsonPoint(coords.get(0), coords.get(1));
		}
		
	}
	
	public static class GeoJsonPolygonDeserialization extends JsonDeserializer<GeoJsonPolygon> {

		@Override
		public GeoJsonPolygon deserialize(JsonParser jsonParser, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			
			ObjectCodec oc = jsonParser.getCodec();
			JsonNode node = oc.readTree(jsonParser);
		
			JsonNode coordinates = node.get("coordinates");
			Iterator<JsonNode> itGlobal = coordinates.elements();
			
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
		}
		
	}
	
	public static class DateSerializer extends JsonSerializer<Date> {
		
		private String dateFormat;
		
		public DateSerializer(String dateFormat) {
			this.dateFormat = dateFormat;
		}
		
		@Override
		public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
				throws IOException, JsonProcessingException {
			jgen.writeString(new SimpleDateFormat(dateFormat).format(value));
		}
	}
	
	public static class DateDeserializer extends JsonDeserializer<Date> {
		
		private String dateFormat;
		
		public DateDeserializer(String dateFormat) {
			this.dateFormat = dateFormat;
		}

		@Override
		public Date deserialize(JsonParser jsonParser, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			ObjectCodec oc = jsonParser.getCodec();
			JsonNode node = oc.readTree(jsonParser);
			Date date = null;
			try {
				date = new SimpleDateFormat(dateFormat).parse(node.asText());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}
		
	}
	
	public static class ZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {
		
		private String dateFormat;
		
		public ZonedDateTimeSerializer(String dateFormat) {
			this.dateFormat = dateFormat;
		}
		
		@Override
		public void serialize(ZonedDateTime value, JsonGenerator jgen, SerializerProvider provider)
				throws IOException, JsonProcessingException {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
			jgen.writeString(value.format(dateTimeFormatter));
		}
	}
	
	public static class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {
		
		private String dateFormat;
		
		public ZonedDateTimeDeserializer(String dateFormat) {
			this.dateFormat = dateFormat;
		}

		@Override
		public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			ObjectCodec oc = jsonParser.getCodec();
			JsonNode node = oc.readTree(jsonParser);
			ZonedDateTime date = null;
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
			
			date = ZonedDateTime.parse(node.asText(), dateTimeFormatter);
			
			return date;
		}
		
	}

	public static class ZoneIdSerializer extends JsonSerializer<ZoneId> {
					
			public ZoneIdSerializer() {
			}
			
			@Override
			public void serialize(ZoneId value, JsonGenerator jgen, SerializerProvider provider)
					throws IOException, JsonProcessingException {
				jgen.writeString(value.toString());
			}
		}
	
	public static class ZoneIdDeserializer extends JsonDeserializer<ZoneId> {
				
		public ZoneIdDeserializer() {
		}

		@Override
		public ZoneId deserialize(JsonParser jsonParser, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			ObjectCodec oc = jsonParser.getCodec();
			JsonNode node = oc.readTree(jsonParser);

			ZoneId zoneId = ZoneId.of(node.asText());
			
			return zoneId;
		}
		
	}

}

