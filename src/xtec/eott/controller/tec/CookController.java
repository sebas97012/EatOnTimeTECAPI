package xtec.eott.controller.tec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.query.Query;
import xtec.eott.DAO.HibernateUtil;
import xtec.eott.DAO.Orders;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cook")
public class CookController {

    @Path("/orders/{cook_id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get_orders(@PathParam("cook_id") int cook_id) throws JsonProcessingException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from Orders where idCook = :id");
        query.setParameter("id", cook_id);
        List<Orders> orders = query.getResultList();
        ObjectMapper mapper = new ObjectMapper();
        return Response.ok(mapper.writeValueAsString(orders)).build();
    }

    public Response change_status() {
        return Response.ok().build(); // TODO: Implement
    }
}
