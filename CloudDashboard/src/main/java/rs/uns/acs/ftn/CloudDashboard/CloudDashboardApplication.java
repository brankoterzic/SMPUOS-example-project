package rs.uns.acs.ftn.CloudDashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.github.vanroy.cloud.dashboard.config.EnableCloudDashboard;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCloudDashboard
public class CloudDashboardApplication 
{
	public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(CloudDashboardApplication.class);    	
    	app.run(args);	
    }
}
