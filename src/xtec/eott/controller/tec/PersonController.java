package xtec.eott.controller.tec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.hibernate.Session;
import org.hibernate.query.Query;
import xtec.eott.DAO.HibernateUtil;
import xtec.eott.DAO.Preference;
import xtec.eott.DAO.User;
import xtec.eott.DAO.UserPreference;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

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

    /**
     *  Obtiene las preferencias que se pueden seleccionar.
     * @return JSON ARRAY con las preferencias
     * @throws JsonProcessingException
     */
	@Path("/default/preferences")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response get_preferences() throws JsonProcessingException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("select preference from Preference");
        List<String> preferences = (List<String>) query.getResultList();
        ObjectMapper mapper = new ObjectMapper();
        String pref_arr = mapper.writeValueAsString(preferences);
        return Response.ok(pref_arr).build();
    }

    @Path("/assign/preferences/{user_id}")
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
    public Response assign_user_preferences(@PathParam("user_id") int user_id, String preferences) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<String> prefs = (List<String>) mapper.readValue(preferences, List.class);

		for(String p: prefs) {
			UserPreference new_pref = new UserPreference();
			new_pref.setIdPreference(get_pref_id(p));
			new_pref.setIdUser(user_id);
			new_pref.create();
		}
		return Response.ok("Preferencias asignadas exitosamente").build();
	}

	@Path("/details/{user_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get_user_info(@PathParam("user_id") int user_id) throws JsonProcessingException {
		User user = new User();
		user = (User) user.read(user_id);
		ObjectMapper mapper = new ObjectMapper();
		return Response.ok(mapper.writeValueAsString(user)).build();
	}

	/**
	 * Obtiene el id de una preferencias.
	 * @param name el nombre de la preferencia.
	 * @return el id.
	 */
	private int get_pref_id(String name) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select idPreference from Preference where preference = :name");
		query.setParameter("name", name);
		return (int)query.getSingleResult();
	}

	/**
	 * Obtiene los nombres de las preferencias de un usuario.
	 * @param user_id el id del usuario.
	 * @return los gustos.
	 * @throws JsonProcessingException
	 */
	@Path("/preferences/{user_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get_user_preferences(@PathParam("user_id") int user_id) throws JsonProcessingException {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select idPreference from UserPreference where idUser = :id");
		query.setParameter("id", user_id);
		List<Integer> preferences = query.getResultList();

		ArrayList<String> pref_names = new ArrayList<>();

		for(Integer i : preferences) {
			Preference userp = new Preference();
			userp = (Preference) userp.read(i);
			pref_names.add(userp.getPreference());
		}
		ObjectMapper mapper = new ObjectMapper();
		String response_json = mapper.writeValueAsString(pref_names);
		return Response.ok(response_json).build();
	}

}
