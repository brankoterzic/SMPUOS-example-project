package rs.uns.acs.ftn.services;

import java.util.Date;
import java.util.List;

import org.jglue.fluentjson.JsonArrayBuilder;
import org.jglue.fluentjson.JsonBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
			if(item.getProductPrice() != null){
				sum+=(item.getProductPrice() != null ? item.getProductPrice() : 0) * (item.getQTY() != null ? item.getQTY() : 1);
			}
		}
		
		return sum;
	}
	
	/**
	 * Method checks if products from user shopping cart exist on Product service. 
	 * @param items
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Boolean checkProductsFromCart(List<CartItem> items){
		
		JsonArrayBuilder ids = JsonBuilderFactory.buildArray();
		for(CartItem item : items){
			ids.add(item.getProductId());
		}
		
		CommunicationService<Boolean> c = new CommunicationService<>(Boolean.class, restTemplate);
		
		Boolean isProductsOK = c.postS("http://localhost:8082/products/checkProductsFromCart", ids.toString());
		return isProductsOK;
	}

	/**
	 * Method checks if the given user is registered and active
	 * @param userId
	 * @return
	 */
	public Boolean checkUser(String userId) {
		
		CommunicationService<Boolean> c = new CommunicationService<>(Boolean.class, restTemplate);
		
		Boolean userExists = c.getS("http://localhost:8081/users/checkUser?userId=" + userId);
		
		return userExists;
	}
}
