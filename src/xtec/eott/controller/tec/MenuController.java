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
import java.util.Collections;
import java.util.List;

@Path("/menu")
public class MenuController {

    /**
     * Obtiene el menú del día, con toda la información de los platillos.
     * @return el menú con los platillos.
     */
	@Path("/get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get_menu() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MenuDish");
		List<MenuDish> dishes_on_menu = (List<MenuDish>)query.getResultList();

		ArrayList<Dish> dishes_details = new ArrayList<Dish>();

		for(MenuDish md : dishes_on_menu) {
			Query dish_query = session.createQuery("from Dish where idDish = :id");
			dish_query.setParameter("id", md.getIdDish());
			dishes_details.add((Dish) dish_query.getSingleResult());
		}

		ObjectMapper mapper = new ObjectMapper();
		String menu_json = "";
		try {
			menu_json = mapper.writeValueAsString(dishes_details);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		session.close();
		return Response.ok(menu_json).build();
	}

    /**
     * Obtiene el id del menú actual.
     * @return el id actual.
     */
	@Path("/id/current")
	@GET
	public Response get_current_menu_id() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select idMenu from Menu");
		List<Integer> ids = query.getResultList();
		int current_menu_id = Collections.max(ids);
		session.close();
		return Response.ok(current_menu_id).build();
	}

    /**
     * Obtiene los nombres de todos los platillos disponibles.
     * @return JSON Array con los nombres de los platillos.
     */
	@Path("/dish/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response get_all_dishes() {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("select name from Dish");
        List<String> dishes_name = query.getResultList();
        ObjectMapper mapper = new ObjectMapper();
        String names_json = "";
        try {
            names_json = mapper.writeValueAsString(dishes_name);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Response.ok(names_json).build(); // OUTPUT EXAMPLE: ["Chifrijo","Arroz con Pollo","Suflé de Atún","Ensalada César","Pollo Frito]
    }

    /**
     * Agrega todos los platillos del menú del día.
     * @return La respuesta de la operación.
     */
	@Path("/add/dish/all")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	public Response add_dishes(String dishes) throws JsonProcessingException { // JSON EXPECTED -> ["A", "B", "C", "D", ....]
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		Query query = session.createQuery("delete from MenuDish");
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();

	    ObjectMapper mapper = new ObjectMapper();
	    List<String> dishes_lst = mapper.readValue(dishes, List.class);
        for(String d : dishes_lst) {
            MenuDish curr_dish = new MenuDish();
            curr_dish.setIdMenu(1);
            curr_dish.setIdDish(get_dish_id(d));
            curr_dish.create();
        }
	    return Response.ok("Platillos agregados exitosamente").build();
    }

    /**
     * Obtiene el id de un platillo a partir del nombre.
     * @param name el nombre del platillo.
     * @return el id del platillo.
     */
    private int get_dish_id(String name) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("select idDish from Dish where name = :dname");
        query.setParameter("dname", name);
        int id = (int) query.getSingleResult();
        session.close();
        return id;
    }

    /**
     * Elimina un platillo del menú del día.
     * @param dish_id el id del platillo.
     * @return La respuesta de la operación.
     */
	@Path("/remove/{dish_id}")
	@DELETE
	public Response remove_dish(@PathParam("dish_id") int dish_id) { // NO JSON NEEDED, JUST PUT THE dish_id in the URL.
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from MenuDish where idDish = :dish");
        query.setParameter("dish", dish_id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return Response.ok("Eliminación realizada satisfactoriamente").build();
    }

    /**
     * Obtiene las recomendaciones del usuario.
     * @param user_id el id del usuario.
     * @return JSON Array con las recomendaciones del usuario.
     */
	@Path("/get/recommendations/{user_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get_recommendations(@PathParam("user_id") int user_id) { //NO JSON NEEDED, JUST PUT THE user_id in the URL.
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select idPreference from UserPreference where idUser = :id");
		query.setParameter("id", user_id);

		List<Integer> id_preferences = (List<Integer>)query.getResultList();

		ArrayList<Dish> total_dish_pref = new ArrayList<>();

		Query query_menu = session.createQuery("from MenuDish");
		List<MenuDish> dishes_on_menu = (List<MenuDish>)query_menu.getResultList();

		ArrayList<Dish> dishes_details = new ArrayList<Dish>();

		for(MenuDish md : dishes_on_menu) {
			Query dish_query = session.createQuery("from Dish where idDish = :id");
			dish_query.setParameter("id", md.getIdDish());
			dishes_details.add((Dish) dish_query.getSingleResult());
		}

		for (Integer i : id_preferences) {
			for(Dish d : dishes_details) {
				if(d.getPreference() == i) {
					//System.out.println(d.getName() +" " + d.getPreference());
					total_dish_pref.add(d);
				}
			}
		}

		ObjectMapper mapper = new ObjectMapper();
		String recommendations_json = "";

		try {
			recommendations_json = mapper.writeValueAsString(total_dish_pref);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		session.close();
		return Response.ok(recommendations_json).build();
	}
	
	@Path("/publish")
	@POST
	public boolean share_menu() { return true; }
}
