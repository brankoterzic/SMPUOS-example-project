package rs.uns.acs.ftn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.uns.acs.ftn.models.Payment;
import rs.uns.acs.ftn.services.PaymentService;

@RestController
@RequestMapping("payments")
public class PaymentController extends AbstractRESTController<Payment, String> {

	private PaymentService paymentService;
	
	@Autowired
	public PaymentController(PaymentService service) {
		super(service);
		this.paymentService = service;
	}

}
