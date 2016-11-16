package rs.uns.acs.ftn.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import rs.uns.acs.ftn.models.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

	List<Product> findByProductName(String name);
}
