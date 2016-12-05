package rs.uns.acs.ftn.config;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import rs.uns.acs.ftn.serialization.SerializationDeserialization;

@Configuration
public class GlobalConfig {

	@Value("${general.dateFormat}")
	private String dateFormat;
	
	@Bean
	public RestTemplate restTemplate(){
		RestTemplate template = new RestTemplate();
		return template;
	}
	
//	@Bean
//	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
//		return new Jackson2ObjectMapperBuilder() {
//			@Override
//			public void configure(ObjectMapper objectMapper) {
//				super.configure(objectMapper);
//				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
//				
//				objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
//				objectMapper.setVisibility(PropertyAccessor.GETTER, Visibility.NONE);
//				
//				SimpleModule module = new SimpleModule();
//				
//				module.addSerializer(
//						GeoJsonPoint.class,
//						new SerializationDeserialization.GeoJsonPointSerializer()
//				);
//				module.addSerializer(
//						GeoJsonPolygon.class,
//						new SerializationDeserialization.GeoJsonPolygonSerializer()
//				);
//		
//				module.addDeserializer(
//						GeoJsonPoint.class,
//						new SerializationDeserialization.GeoJsonPointDeserialization()
//				);
//				module.addDeserializer(
//						GeoJsonPolygon.class,
//						new SerializationDeserialization.GeoJsonPolygonDeserialization()
//				);
//		
//				module.addSerializer(
//						Date.class,
//						new SerializationDeserialization.DateSerializer(dateFormat)
//				);
//				module.addDeserializer(
//						Date.class,
//						new SerializationDeserialization.DateDeserializer(dateFormat)
//				);
//					
//				module.addSerializer(
//						ZonedDateTime.class,
//						new SerializationDeserialization.ZonedDateTimeSerializer(dateFormat)
//				);
//				module.addDeserializer(
//						ZonedDateTime.class,
//						new SerializationDeserialization.ZonedDateTimeDeserializer(dateFormat)
//				);
//				
//				module.addSerializer(
//						ZoneId.class,
//						new SerializationDeserialization.ZoneIdSerializer()
//				);
//				module.addDeserializer(
//						ZoneId.class,
//						new SerializationDeserialization.ZoneIdDeserializer()
//				);
//				objectMapper.registerModule(module);
//				
//				simpleDateFormat(dateFormat);
//			}
//		};
//	}
}
