package rs.uns.acs.ftn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value = "/createShoppingCart", method = RequestMethod.POST)
	ShoppingCart createShoppingCart(
			@RequestParam(name = "items") List<CartItem> items,
			@RequestParam(name = "userId") String userId
			){
		
		return shoppingCartSrevice.createShoppingCart(items, userId);
	}

}
