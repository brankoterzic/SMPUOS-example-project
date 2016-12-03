package rs.uns.acs.ftn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.uns.acs.ftn.models.Product;
import rs.uns.acs.ftn.services.ProductService;


@RestController
@RequestMapping("products")
public class ProductController extends AbstractRESTController<Product, String>{

	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService service) {
		super(service);
		this.productService = service;
	}
	
	@RequestMapping(value = "/checkProductsFromCart")
	public Boolean checkProductsFromCart(
			@RequestBody List<String> ids
			){
		return productService.checkProductsFromCart(ids);
	}

}
