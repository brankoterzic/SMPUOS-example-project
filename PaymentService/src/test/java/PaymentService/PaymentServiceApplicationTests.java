package PaymentService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import rs.uns.acs.ftn.PaymentServiceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaymentServiceApplication.class)
@WebAppConfiguration
public class PaymentServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
