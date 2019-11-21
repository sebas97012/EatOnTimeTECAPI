package xtec.eott.controller.tec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.query.Query;
import xtec.eott.DAO.Dish;
import xtec.eott.DAO.HibernateUtil;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/dish")
public class DishController {
	
	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add_dish(String dish) { // {"name":"XXXX", "points": XXX, "ingredients":"XXX,XXX,XXX", "calories":XXX, "price":XXXX, "preference": X}
		ObjectMapper mapper = new ObjectMapper();
		Dish new_dish = null;
		try {
			new_dish = mapper.readValue(dish, Dish.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		new_dish.create();
		return Response.ok("Platillo creado exitosamente").build();
	}

	@Path("/get/id")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getIdDish(String dish_name) throws JsonProcessingException { // {"name": "XXXXXX"}
		ObjectMapper mapper = new ObjectMapper();
		Dish dish = mapper.readValue(dish_name, Dish.class);
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select idDish from Dish where name = :dname");
		query.setParameter("dname", dish.getName());
		int id = (int) query.getSingleResult();
		session.close();
		return Response.ok(id).build();
	}
	
	@Path("/remove/{dish_id}")
	@DELETE
	public Response remove_dish(@PathParam("dish_id") int dish_id) {
        return Response.ok().build();
	}
	
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
