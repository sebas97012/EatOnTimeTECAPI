package xtec.eott.controller.tec;

import xtec.eott.DAO.Province;
import xtec.eott.DAO.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class Hello {

	@Path("/w")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getTestService() {
		User sebas = new User();
		sebas = (User)sebas.read(305070015);
		return sebas.getName()+sebas.getProvince().getProvinceName();
	}
}