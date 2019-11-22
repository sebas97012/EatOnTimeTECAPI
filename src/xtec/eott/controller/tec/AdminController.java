package xtec.eott.controller.tec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.query.Query;
import xtec.eott.DAO.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/admin")
public class AdminController {

    @Path("/roles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get_roles() throws JsonProcessingException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("select roleName from Role ");
        List<String> roles = (List<String>) query.getResultList();
        ObjectMapper mapper = new ObjectMapper();
        String role_arr = mapper.writeValueAsString(roles);
        return Response.ok(role_arr).build();
    }

    /**
     * Asigna los roles a algun usuario.
     * @param data los datos del usuario y los roles asignados.
     * @return Respuesta de la operación.
     * @throws JsonProcessingException
     */
    @Path("/assign/roles")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assign_role(String data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        AssignRoleDAO u_role = mapper.readValue(data, AssignRoleDAO.class);

        for(String role : u_role.roles) {
            UserRole urole = new UserRole();
            urole.setIdUser(u_role.user_id);
            urole.setIdRole(get_role_id(role));
            urole.create();
        }
        return Response.ok("Roles asignados exitosamente").build();
    }

    @Path("/add/preferences")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add_preferences(String preferences) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<String> prefs = mapper.readValue(preferences, List.class);

        for(String pref : prefs) {
            Preference new_pref = new Preference();
            new_pref.setPreference(pref);
            new_pref.create();
        }
        return Response.ok("Preferencias agregadas exitosamente").build();
    }

    /**
     * Obtiene el id de un role.
     * @param role el nombre del role.
     * @return el id.
     */
    private int get_role_id(String role) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("select idRole from Role where roleName = :name");
        query.setParameter("name", role);
        return (int)query.getSingleResult();
    }
}
