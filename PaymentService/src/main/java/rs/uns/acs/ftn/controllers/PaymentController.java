package rs.uns.acs.ftn.controllers;

import rs.uns.acs.ftn.models.Payment;
import rs.uns.acs.ftn.services.PaymentService;

public class PaymentController extends AbstractRESTController<Payment, String> {

	public PaymentController(PaymentService service) {
		super(service);
		// TODO Auto-generated constructor stub
	}

}
