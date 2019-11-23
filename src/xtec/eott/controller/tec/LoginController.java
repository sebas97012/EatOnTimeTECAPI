package xtec.eott.controller.tec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import xtec.eott.DAO.Login;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(String new_login) { // JSON {"user_id":XXXXXXXXXX, "password":"mypasss"}
		Login login = null;
		try {
			login = new ObjectMapper().readValue(new_login, Login.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return Response.ok("Inicio de sesión exitoso")
						.header("Acces-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.allow("OPTIONS")
						.build();
	}
}
