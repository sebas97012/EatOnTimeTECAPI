package xtec.eott.controller.tec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import xtec.eott.DAO.Orders;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/order")
public class OrderController {
	
	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean add_order(String new_order) { 
		Orders order = null;
		try {
			order = new ObjectMapper().readValue(new_order, Orders.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return true; 
		}
	
	@Path("/get/{order_id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Orders get_order(int order_id) { return new Orders(); }
	
	@Path("/remove")
	@DELETE
	public boolean remove_order(int order_id) { return true; }
	
	@Path("/update/{order_id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean update_dish(@PathParam("order_id") int order_id, String new_vals) { 
		Orders order = null;
		try {
			order = new ObjectMapper().readValue(new_vals, Orders.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return true; 
		}
}
