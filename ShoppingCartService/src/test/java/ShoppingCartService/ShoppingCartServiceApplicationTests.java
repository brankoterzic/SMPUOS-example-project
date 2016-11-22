package ShoppingCartService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.jglue.fluentjson.JsonArrayBuilder;
import org.jglue.fluentjson.JsonBuilderFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.JsonObject;

import rs.uns.acs.ftn.ShoppingCartServiceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingCartServiceApplication.class)
@WebAppConfiguration
@ActiveProfiles("testing")
public class ShoppingCartServiceApplicationTests {

	
	protected MockMvc mockMvc;
	
	@Resource
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void init(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testCreateShoppingCart() throws Exception{
		
		JsonArrayBuilder items  = JsonBuilderFactory.buildArray()
						.add(
							 JsonBuilderFactory.buildObject()
								.add("id", "1")
								.add("name", "product1")
								.add("price", 120.)
						).add(
							 JsonBuilderFactory.buildObject()
								.add("id", "1")
								.add("name", "product1")
								.add("price", 120.)	
						);
		
		JsonObject obj = JsonBuilderFactory.buildObject()
						.add("userId", "123")
						.add("date", "2016-03-25T12:26:44Z")
						.add("sumPrice", 1233.)
						.add("items", items)
						.getJson();

		mockMvc.perform(post("/shoppingCarts")
				   .contentType(MediaType.APPLICATION_JSON_UTF8)
				   .content(obj.toString())
				   )
				   .andDo(MockMvcResultHandlers.print())
				   .andExpect(status().isOk());
		
		mockMvc.perform(post("/shoppingCarts/123/createShoppingCart/")
				   .contentType(MediaType.APPLICATION_JSON_UTF8)
				   .content(items.toString())
				   )
				   .andDo(MockMvcResultHandlers.print())
				   .andExpect(status().isOk());
	}
	
	@Test
	public void contextLoads() {
	}

}
