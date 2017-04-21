package rs.uns.acs.ftn.TurbineService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@SpringBootApplication
@EnableTurbineStream
public class TurbineServiceApplication 
{
	// turbine stream: http://localhost:8999/turbine/turbine.stream pasete in hystrics dashboard
	// to start straming, push first request.
	public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(TurbineServiceApplication.class);    	
    	app.run(args);	
    }
}
