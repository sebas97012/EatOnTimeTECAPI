package xtec.eott.controller.tec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.query.Query;
import xtec.eott.DAO.HibernateUtil;
import xtec.eott.DAO.User;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/person")
public class PersonController {
	
	@Path("/signup")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sign_up(String new_person) { // JSON: {"idUser":XXXXXXXXX, "name":"XXXXX", "phone":"XXXXXXXX", "province": {"idProvince":X, "provinceName":"San José"}, "email":"XXX@XXX"}
		ObjectMapper mapper = new ObjectMapper();
		try {
			User new_user = mapper.readValue(new_person, User.class);
			new_user.create();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok("Usuario registrado exitosamente").build();
	}
	
	@Path("/orders/{user_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void get_order_by_client(@PathParam("user_id") int user_id) { }
	
	@Path("/tastes/{user_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void get_tastes_by_client(@PathParam("user_id") int user_id) { }

}
