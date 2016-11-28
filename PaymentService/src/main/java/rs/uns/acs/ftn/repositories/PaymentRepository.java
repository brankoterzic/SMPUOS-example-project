package rs.uns.acs.ftn.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import rs.uns.acs.ftn.models.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String>{

}
