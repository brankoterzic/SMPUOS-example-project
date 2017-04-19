package rs.uns.acs.ftn;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
public class ConfigurationServiceApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(ConfigurationServiceApplication.class,args);
    }
}
