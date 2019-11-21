package xtec.eott.controller.tec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import xtec.eott.DAO.Orders;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.Random;

@Path("/order")
public class OrderController {

	@Path("/create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create_order(String order) throws JsonProcessingException { // {"idClient": XXXXXXX, "observation": "XXXXXXXXX"}
		ObjectMapper mapper = new ObjectMapper();
		Orders new_order = mapper.readValue(order, Orders.class);
		new_order.setDate(new Timestamp(System.currentTimeMillis()));
		new_order.setIdCook(154845451);
		new_order.create();
		return Response.ok("Order creada con exito").build();
	}

	private int assign_cook() {
		// TODO: traer todos los maes que son  cook y elegir uno.
		return -1;
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
