package xtec.eott.controller.tec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.query.Query;
import xtec.eott.DAO.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/friend")
public class FriendController {

	@Path("/find/name")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get_people_by_name(String json_name) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		User new_user = mapper.readValue(json_name, User.class);
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from User where name = :name");
		query.setParameter("name", new_user.getName());
		List<String> matches = query.getResultList();
		session.close();
		if(matches.isEmpty()) return Response.ok("No se encontraton personas").build();
		return Response.ok(mapper.writeValueAsString(matches)).build();
	}

	@Path("/find/phone")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get_people_by_phone(String phone) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		User new_user = mapper.readValue(phone, User.class);
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from User where phone = :ph");
		query.setParameter("ph", new_user.getPhone());
		List<String> matches = query.getResultList();
		session.close();
		if(matches.isEmpty()) return Response.ok("No se encontraton personas").build();
		return Response.ok(mapper.writeValueAsString(matches)).build();
	}

	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add_friend(String add_info) throws JsonProcessingException { //{idUser: XXXXX, idFriend: XXXX}
		ObjectMapper mapper = new ObjectMapper();
		Friend new_friend = mapper.readValue(add_info, Friend.class);
		new_friend.create();

		Friend new_friend2 = new Friend();
		new_friend2.setIdFriend(new_friend.getIdUser());
		new_friend2.setIdUser(new_friend.getIdFriend());
		new_friend2.create();

		return Response.ok("Amigo agregado exitosamente").build();
	}

	@Path("/get/{user_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get_user_friends(@PathParam("user_id") int user_id) throws JsonProcessingException {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select idFriend from Friend where idUser = :id");
		query.setParameter("id", user_id);
		List<Integer> friends = query.getResultList();
		ArrayList<UserFriendDTO> friend_dto = new ArrayList<>();

		for(Integer friend_id : friends) {
			User friend = new User();
			friend = (User) friend.read(friend_id);
			friend_dto.add(new UserFriendDTO(friend.getIdUser(), friend.getName()));
		}
		session.close();

		ObjectMapper mapper = new ObjectMapper();
		return Response.ok(mapper.writeValueAsString(friend_dto)).build();
	}

	@Path("/delete")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete_friend(String data) throws JsonProcessingException { // {idUser: XXXXX, idDeleted: XXXXXX}
		ObjectMapper mapper = new ObjectMapper();
		Friend del = mapper.readValue(data, Friend.class);
		del.delete();
		return Response.ok("Amigo eliminado").build();
	}
}
