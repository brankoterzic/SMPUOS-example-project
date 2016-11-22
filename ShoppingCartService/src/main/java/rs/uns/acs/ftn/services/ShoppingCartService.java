package rs.uns.acs.ftn.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.uns.acs.ftn.models.CartItem;
import rs.uns.acs.ftn.models.ShoppingCart;
import rs.uns.acs.ftn.repositories.ShoppingCartRepository;

@Service
public class ShoppingCartService extends AbstractCRUDService<ShoppingCart, String>{

	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	public ShoppingCartService(ShoppingCartRepository repo) {
		super(repo);
		this.shoppingCartRepository = repo;
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

}
