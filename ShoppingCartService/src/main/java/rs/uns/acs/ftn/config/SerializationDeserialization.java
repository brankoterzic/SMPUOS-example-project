package rs.uns.acs.ftn.config;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	

}

