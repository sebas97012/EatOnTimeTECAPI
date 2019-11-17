package xtec.eott.controller.tec;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/friend")
public class FriendController {
	
	@Path("/add/{friend_id}")
	@POST
	public void add_friend(@PathParam("friend_id")int friend_id) { }

}
