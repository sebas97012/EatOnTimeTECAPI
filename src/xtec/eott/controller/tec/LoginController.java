package xtec.eott.controller.tec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import xtec.eott.DAO.Login;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class LoginController {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean login(String new_login) { 
		Login login = null;
		try {
			login = new ObjectMapper().readValue(new_login, Login.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(login.user_id);
		System.out.println(login.password);
		return true; 
	}
}