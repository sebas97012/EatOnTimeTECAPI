package xtec.eott.controller.tec;




import xtec.eott.DAO.Dish;
import xtec.eott.DAO.Menu;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/menu")
public class MenuController {
	
	@Path("/get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Menu get_menu() { return new Menu(); }
	
	@Path("/add/{dish_id}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean add_dish() { return true; }
	
	@Path("/remove/{dish_id}")
	@DELETE
	public boolean remove_dish(@PathParam("dish_id") int dish_id) { return true; }
	
	@Path("/get/recommendations/{user_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Dish> get_recommendations(@PathParam("user_id") int user_id) { return new ArrayList<Dish>(); }
	
	@Path("/publish")
	@POST
	public boolean share_menu() { return true; }
}
