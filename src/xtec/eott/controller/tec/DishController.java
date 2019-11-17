package xtec.eott.controller.tec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import xtec.eott.DAO.Dish;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/dish")
public class DishController {
	
	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean add_new_dish(String new_dish) {
		Dish dish = null;
		try {
			dish = new ObjectMapper().readValue(new_dish, Dish.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return true; 
	}
	
	@Path("/remove/{dish_id}")
	@DELETE
	public boolean remove_dish(@PathParam("dish_id") int dish_id) { return true; }
	
	@Path("/update/{dish_id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean update_dish(@PathParam("dish_id") int dish_id, String new_vals) { 
		Dish dish = null;
		try {
			dish = new ObjectMapper().readValue(new_vals, Dish.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return true; 
		}
}
