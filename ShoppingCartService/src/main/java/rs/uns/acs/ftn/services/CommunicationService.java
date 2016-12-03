package rs.uns.acs.ftn.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class CommunicationService <T>{

	final Class<T> responseType;
	private RestTemplate restTemplate;

    public CommunicationService(Class<T> typeParameterClass, RestTemplate restTemplate) {
        this.responseType = typeParameterClass;
        this.restTemplate = restTemplate;
    }
    
    public T postS(String url, String requestBody){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<String>(requestBody, headers);
		try{
			return restTemplate.postForObject(url, req, responseType);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		
		}
    }
    
    public T getS(String url){
    	return restTemplate.getForObject(url, responseType);
    }
}
