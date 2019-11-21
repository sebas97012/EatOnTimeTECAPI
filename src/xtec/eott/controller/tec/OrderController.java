package xtec.eott.controller.tec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.query.Query;
import xtec.eott.DAO.HibernateUtil;
import xtec.eott.DAO.OrderDish;
import xtec.eott.DAO.Orders;
import xtec.eott.DAO.PlaceOrderDAO;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

@Path("/order")
public class OrderController {

	/**
	 * Crea una orden nueva.
	 * @param order los datos de la orden.
	 * @return la respuesta de la operación.
	 * @throws JsonProcessingException
	 */
	@Path("/place")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create_order(String order) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		PlaceOrderDAO new_order = mapper.readValue(order, PlaceOrderDAO.class);
		int order_id = generate_order_id(1, 1000);
		for (int i = 0; i < new_order.users.size(); i++) {
			Orders order_tmp = new Orders();
			order_tmp.setIdCook(assign_cook());
			order_tmp.setDate(new Timestamp(System.currentTimeMillis()));
			order_tmp.setIdClient(new_order.users.get(i));
			order_tmp.setObservation(new_order.observations.get(i));
			order_tmp.setIdOrder(order_id);

			OrderDish order_dish = new OrderDish();
			order_dish.setIdDish(get_id_dish(new_order.dishes.get(i)));
			order_dish.setIdOrder(order_id);

			int dish_points = get_dish_points(new_order.dishes.get(i)); // TODO: asignar puntos por platillo.

			order_tmp.create();
			order_dish.create();
		}
		return Response.ok("Order creada con exito").build();
	}

	/**
	 * Obtiene el id del platillo.
	 * @param name el nombre del platillo.
	 * @return el id.
	 */
	private int get_id_dish(String name) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select idDish from Dish where name = :dname");
		query.setParameter("dname", name);
		int id = (int) query.getSingleResult();
		session.close();
		return id;
	}

	/**
	 * Obtiene los puntos de un platillo.
	 * @param name el nombre del platillo.
	 * @return los puntos del platillo.
	 */
	private int get_dish_points(String name) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select points from Dish where name = :dname");
		query.setParameter("dname", name);
		int points = (int) query.getSingleResult();
		session.close();
		return points;
	}

	/**
	 * Genera un identificador de orden.
	 * @param min rango inferior.
	 * @param max rango superior.
	 * @return el id de la orden.
	 */
	public static int generate_order_id(double min, double max){
		int x = (int) ((int)(Math.random()*((max-min)+1))+min);
		return x;
	}

	/**
	 * Elige un cocinero para asignarlo a una orden.
	 * @return El id del cook elegido.
	 */
	private int assign_cook() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select idUser from UserRole where idRole = :cook");
		query.setParameter("cook", 5);
		List<Integer> cooks = query.getResultList();
		Random rd = new Random();
		return cooks.get(rd.nextInt(cooks.size()));
	}
	
	@Path("/get/{order_id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Orders get_order(int order_id) { return new Orders(); }
	
	@Path("/remove")
	@DELETE
	public boolean remove_order(int order_id) { return true; }
	
	@Path("/update/{order_id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean update_dish(@PathParam("order_id") int order_id, String new_vals) { 
		Orders order = null;
		try {
			order = new ObjectMapper().readValue(new_vals, Orders.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return true; 
		}
}
