package rs.uns.acs.ftn.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import rs.uns.acs.ftn.services.AbstractCRUDService;

/**
*
* @param <T> Entity class
* @param <ID> Type used as identifier
*/
public abstract class AbstractRESTController<T, ID extends Serializable> {

	private Logger logger = LoggerFactory.getLogger(AbstractRESTController.class);

	@Value("${general.pageSize}")
	protected Integer pageSize;
	
	private AbstractCRUDService<T, ID> service;

	/**
	 * @param service
	 */
	public AbstractRESTController(AbstractCRUDService<T, ID> service) {
		this.service = service;
	}

	/**
	 * Get all entities from database. Pageable.
	 * @param page Number of page
	 * @return All selected Entities from page
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Map<String, Object> findAll(
		@RequestParam(name = "page", defaultValue = "0") Integer page,
		@RequestParam(name = "sort", defaultValue = "") String sort
	) {
		List<Order> orders = getOrders(sort);
		Page<T> all = null;
		if(orders.size()!=0){
			all = service.findAll(new PageRequest(page, pageSize, new Sort(orders)));
		}else{
			all = service.findAll(new PageRequest(page, pageSize));
		}
		return prepareListPage(all);
	}
	
	@RequestMapping(value = "findByIds", method = RequestMethod.GET)
	public List<T> findByIds(
		@RequestParam(name = "ids") List<ID> ids) {
		return service.findByIds(ids);
	}


	/**
	 * Get all entities matching given id.
	 * @param id Identifier for desired entity
	 * @return Selected entity of given type
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public T findOne(@PathVariable ID id) {
		return service.findOne(id);
	}
	
	/**
	 * Save entity to a database.
	 * @param newEntity Entity to be saved.
	 * @return Saved entity object.
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> save(@RequestBody T newEntity) {
		logger.debug("create() - {} - {}", newEntity, newEntity.getClass());
		
		T created = service.save(newEntity);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("success", true);
		m.put("created", created);
		return m;
	}

	/**
	 * Update desired entity.
	 * @param id Entity identifier.
	 * @param newEntity Updated entity.
	 * @return Updated entity object.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> update(@PathVariable ID id, @RequestBody T newEntity) {
		logger.debug("update() - {} - {} - {}", id, newEntity, newEntity.getClass());
		
		T updated = service.update(id, newEntity);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("success", true);
		m.put("updated", updated);
		return m;
	}

	/**
	 * Delete desired entity.
	 * @param id Entity identifier.
	 * @return Operation success flag.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable ID id) {
		logger.debug("delete() - {}", id);
		
		service.delete(id);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("success", true);
		return m;
	}
	
	/**
	 * Delete all entities.
	 * @return Operation success flag.
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public Map<String, Object> deleteAll() {		
		service.deleteAll();
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("success", true);
		return m;
	}
	
	protected Map<String, Object> prepareListPage(Page<T> page){
		List<T> results = new ArrayList<T>();
		page.iterator().forEachRemaining(results::add);
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("elements", results);
		m.put("page", page.getNumber());
		m.put("totalPages", page.getTotalPages());
		m.put("numberOfElements", page.getTotalElements());
		return m;
	}
	
	protected List<Order> getOrders(String sort) {
		List<Order> orders = new ArrayList<Order>();
		if(sort!=null && !sort.equals("")){
			String[] properties = sort.split(",");
			for(String property: properties){
				if(property.startsWith("!")){
					orders.add(new Order(Direction.DESC, property.substring(1)));
				}else{
					orders.add(new Order(Direction.ASC, property));
				}
			}
		}
		return orders;
	}
	
}
