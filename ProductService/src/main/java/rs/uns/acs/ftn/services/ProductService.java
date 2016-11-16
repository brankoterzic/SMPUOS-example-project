package rs.uns.acs.ftn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.uns.acs.ftn.models.Product;
import rs.uns.acs.ftn.repositories.ProductRepository;

@Service
public class ProductService extends AbstractCRUDService<Product, String>{

	private ProductRepository productRepository;
	
	@Autowired
	public ProductService(ProductRepository repo) {
		super(repo);
		this.productRepository = repo;
	}
	
	public List<Product> findByProductName(String name){
		return productRepository.findByProductName(name);
	}
}
