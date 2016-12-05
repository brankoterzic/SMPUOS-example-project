package rs.uns.acs.ftn.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import rs.uns.acs.ftn.serialization.Converters;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
	
	@Value("${mongodb.host}")
	private String mongodb_host; 

	@Value("${mongodb.port}")
	private int mongodb_port; 

	@Value("${mongodb.databasename}")
	private String mongodb_databasename;
	
	@Value("${general.dateFormat}")
	private String dateFormat;
	
	@Override
	public String getDatabaseName() {
		return mongodb_databasename;
	}
	
//	@Override
//    public CustomConversions customConversions() {
//		 return new CustomConversions( Arrays.asList(
//        		new Converters.StringToDateConverter(dateFormat),
//        		new Converters.DateToStringConverter(dateFormat)
//		 ));
//    }

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient(mongodb_host, mongodb_port);
	}
}
