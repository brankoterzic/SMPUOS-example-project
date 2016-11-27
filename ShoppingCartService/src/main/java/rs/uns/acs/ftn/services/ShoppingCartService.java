package rs.uns.acs.ftn.services;

import java.util.Date;
import java.util.List;

import org.jglue.fluentjson.JsonArrayBuilder;
import org.jglue.fluentjson.JsonBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import rs.uns.acs.ftn.models.CartItem;
import rs.uns.acs.ftn.models.ShoppingCart;
import rs.uns.acs.ftn.repositories.ShoppingCartRepository;

@Service
public class ShoppingCartService extends AbstractCRUDService<ShoppingCart, String>{

	private ShoppingCartRepository shoppingCartRepository;
	private RestTemplate restTemplate;
	
	@Autowired
	public ShoppingCartService(ShoppingCartRepository repo, RestTemplate restTemplate) {
		super(repo);
		this.shoppingCartRepository = repo;
		this.restTemplate = restTemplate;

	}
	
	public ShoppingCart createShoppingCart(List<CartItem> items, String userId){
		ShoppingCart cart = new ShoppingCart();
		
		cart.setUserId(userId);
		cart.setDate(new Date());
		cart.setSumPrice(calculateSumPrice(items));
		cart.setItems(items);
		shoppingCartRepository.save(cart);
		
		return cart;
	}
	
	private Double calculateSumPrice(List<CartItem> items){
		Double sum = 0.;
		
		for(CartItem item : items){
			if(item.getPrice() != null){
				sum+=item.getPrice();
			}
		}
		
		return sum;
	}
	
	public Boolean checkProductsFromCart(List<CartItem> items){
		
		JsonArrayBuilder ids = JsonBuilderFactory.buildArray();
		for(CartItem item : items){
			ids.add(item.getId());
		}
		
		
		Boolean isProductsOK = postS("http://localhost:8081/users/checkProductsFromCart", ids.toString());
		
		return isProductsOK;

	}
	
	private Boolean postS(String url, String requestBody){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> req = new HttpEntity<String>(requestBody, headers);
		try{
			return restTemplate.postForObject(url, req, Boolean.class);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
