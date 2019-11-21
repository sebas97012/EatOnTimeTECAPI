package xtec.eott.controller.tec;

import xtec.eott.controller.dashboard.DashboardClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/dashboard")
public class DashboardController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/client/{user_id}")
	public DashboardClient get_dashboard_client(@PathParam("friend_id") int user_id) { return new DashboardClient(); }

}
