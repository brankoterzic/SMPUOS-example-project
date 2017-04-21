package rs.uns.acs.ftn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.uns.acs.ftn.models.CartItem;
import rs.uns.acs.ftn.models.ShoppingCart;
import rs.uns.acs.ftn.services.ShoppingCartService;

@RestController
@RequestMapping("shoppingCarts")
public class ShoppingCartController extends AbstractRESTController<ShoppingCart, String>{

	private ShoppingCartService shoppingCartSrevice;
	
	@Autowired
	public ShoppingCartController(ShoppingCartService service) {
		super(service);
		this.shoppingCartSrevice = service;
	}
	
	@RequestMapping(value = "{userId}/createShoppingCart", method = RequestMethod.POST)
	ShoppingCart createShoppingCart(
			@RequestBody List<CartItem> items,
			@PathVariable(name = "userId") String userId
			){
		Boolean productsOK = shoppingCartSrevice.checkProductsFromCart(items);
		Boolean userOK = shoppingCartSrevice.checkUser(userId);
		
		if(userOK != null && productsOK != null)
			if(userOK && productsOK)
				return shoppingCartSrevice.createShoppingCart(items, userId);
		
		return null;
	}
	
	@FeignClient("user-service")//the server.port property name, for the "server" service
	public interface UserServiceClient {
		@RequestMapping(value = "users/checkUser", method = RequestMethod.GET)// the endpoint which will be balanced over
		Boolean checkUser(
				@RequestParam(name = "userId") String userId);// the method specification must be the same as for users/hello
	}
	
//	@FeignClient("user-service")//the server.port property name, for the service
//	public interface Hello {
//		@RequestMapping(value = "users/hello", method = RequestMethod.GET)// the endpoint which will be balanced over
//		String hello();// the method specification must be the same as for users/hello
//	}
}
