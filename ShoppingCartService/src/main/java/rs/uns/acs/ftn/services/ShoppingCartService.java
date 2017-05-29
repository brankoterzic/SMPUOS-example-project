package rs.uns.acs.ftn.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import rs.uns.acs.ftn.controllers.ShoppingCartController.ProductServiceClient;
import rs.uns.acs.ftn.controllers.ShoppingCartController.UserServiceClient;
import rs.uns.acs.ftn.models.CartItem;
import rs.uns.acs.ftn.models.ShoppingCart;
import rs.uns.acs.ftn.repositories.ShoppingCartRepository;

@Service
public class ShoppingCartService extends AbstractCRUDService<ShoppingCart, String>{

	private ShoppingCartRepository shoppingCartRepository;
	private RestTemplate restTemplate;
	
	@Autowired
	private UserServiceClient userServiceClient;// feign client
	
	@Autowired
	private ProductServiceClient productServiceClient;
	
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
			if(item.getProductPrice() != null){
				sum+= item.getProductPrice() * (item.getQTY() != null ? item.getQTY() : 1);
			}
		}
		
		return sum;
	}
	
	/**
	 * Method checks if products from user shopping cart exist on Product service. 
	 * @param items
	 * @return if product exists
	 */
	@HystrixCommand(fallbackMethod="fallbackCheckProductsFromCart")
	public Boolean checkProductsFromCart(List<CartItem> items){
		/*WITHOUT USING LOAD-BALANCING*/
		
//		JsonArrayBuilder ids = JsonBuilderFactory.buildArray();
//		for(CartItem item : items){
//			ids.add(item.getProductId());
//		}
		
//		CommunicationService<Boolean> c = new CommunicationService<>(Boolean.class, restTemplate);
//		
//		//Boolean isProductsOK = c.postS("http://localhost:8082/products/checkProductsFromCart", ids.toString());
//		Boolean isProductsOK = c.postS("http://localhost:8765/product-service/products/checkProductsFromCart", ids.toString());
//		return isProductsOK;
		
		List<String> ids = new ArrayList<String>();
		for(CartItem item : items){
			ids.add(item.getProductId());
		}
		
		Boolean isProductOK = productServiceClient.checkProductsFromCart(ids);
		return isProductOK;
	}
	
	public Boolean fallbackCheckProductsFromCart(List<CartItem> items){
		System.out.println("???????????????????????????????????");
		return true;
	}

	/**
	 * Method checks if the given user is registered and active
	 * We use Ribbon and Feign to get data from user-service, load-balancing 
	 * @param userId
	 * @return if user exists
	 */
	@HystrixCommand(fallbackMethod="fallbackCheckUser")
	public Boolean checkUser(String userId) {
		
//		CommunicationService<Boolean> c = new CommunicationService<>(Boolean.class, restTemplate);
				
		//Boolean userExists = c.getS("http://localhost:8081/users/checkUser?userId=" + userId);
//		Boolean userExists = c.getS("http://localhost:8765/user-service/users/checkUser?userId=" + userId);
//		return userExists;
		
		/*USING LOAD-BALANCING*/
		 Boolean resp = userServiceClient.checkUser(userId);
		 return resp;
	}
	
	public Boolean fallbackCheckUser(String userId){
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return true;
	}
}
