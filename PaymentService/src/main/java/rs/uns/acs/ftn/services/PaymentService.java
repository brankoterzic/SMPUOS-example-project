package rs.uns.acs.ftn.services;

import org.springframework.stereotype.Service;

import rs.uns.acs.ftn.models.Payment;
import rs.uns.acs.ftn.repositories.PaymentRepository;

@Service
public class PaymentService extends AbstractCRUDService<Payment, String> {

	public PaymentService(PaymentRepository repo) {
		super(repo);
	}
}
