
package co.edu.ims.ejbs;

import co.edu.ims.modelo.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Stateless
@Path("/persona")
public class PersonaEJB {
    
     @PersistenceContext(unitName = "controlAccesoPU")
    protected EntityManager em;
        
    @GET
    @Produces("application/json")
    @Path("{id}")
    public Persona buscar(@PathParam("id") Integer id){
        System.out.println("pasa por aqui...");
       return em.find(Persona.class , id);
        
    }
    
    @GET
    @Produces("application/json")
    public List<Persona> buscar(){
        String jpql = "SELECT per FROM Persona per";
        TypedQuery<Persona> q = em.createQuery(jpql, Persona.class);
        List<Persona> resultado = q.getResultList();
        return resultado;
    }
    
    
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Persona agregar(Persona entity){
        em.persist(entity);
        em.flush();
        return entity;
    }
    
    @DELETE
    @Path("(id)")
    @Produces("application/json")
    public String eliminar(@PathParam("id") Integer pId){
        Persona p = em.find(Persona.class, pId);
        if(p != null){
            em.remove(p);
        }
        return "()";
    }
    
}
