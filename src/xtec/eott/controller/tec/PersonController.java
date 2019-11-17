package xtec.eott.controller.tec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import xtec.eott.DAO.User;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/person")
public class PersonController {
	
	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean create_person(String new_person) { 
		User person = null;
		try {
			person = new ObjectMapper().readValue(new_person, User.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return true; 
	}
	
	@Path("/remove/{user_id}")
	@DELETE
	public void remove_person(@PathParam("friend_id") int user_id) { }
	
	@Path("/orders/{user_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void get_order_by_client(@PathParam("user_id") int user_id) { }
	
	@Path("/tastes/{user_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void get_tastes_by_client(@PathParam("user_id") int user_id) { }

}
