package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Event;
import entity.Pet;
import myfacade.MyFacade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author andre
 */

@Path("pet")
public class RESTPet
{

    @Context
    private UriInfo context;

    Gson gson;
    MyFacade f = new MyFacade(Persistence.createEntityManagerFactory("pu"));

    public RESTPet()
    {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPetsJson()
    {
        String json = gson.toJson(f.getAllPets());

        return Response.ok(json).build();
    }

    @Path("petcount")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPetsAmountJson()
    {
        String json = gson.toJson(f.getTotalAmountOfPets());

        return Response.status(Response.Status.ACCEPTED).entity("{\"petCount\":" + "\"" + json + "\"}").build();

    }

    @Path("petlist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPetsInfoJson()
    {
        String json = gson.toJson(f.getAllPetsInfo());
        
        return Response.ok(json).build();
    }

    @Path("livingpetslist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLivingPetsJson()
    {
        String json = gson.toJson(f.getAllLivingPets());

        return Response.ok(json).build();
    }

    @Path("peteventday/{year}-{month}-{date}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPetsWithEventsOnGivenDayJson(@DefaultValue("1970") @PathParam("year") String year,@DefaultValue("01") @PathParam("month") String month,@DefaultValue("01") @PathParam("date") String date) throws ParseException
    {
        String string_date = date + "-" + month + "-" + year;

        SimpleDateFormat fo = new SimpleDateFormat("dd-MM-yyyy");

        Date d = fo.parse(string_date);
        long ldate = d.getTime();

        String json2 = gson.toJson(f.getAllPetsWithEventsOnGivenDay(new Date(ldate)));

        Date test = new Date(ldate);
        System.out.println(test.toString());

        return Response.ok(json2).build();
    }

    @Path("editpet")
    @PUT
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPetJson(String json) 
    {

        Pet pet = gson.fromJson(json, Pet.class);
        try
        {
            f.editPet(pet);

            return Response.ok().entity(json).build();

        } catch (Exception e)
        {
            System.out.println(e);
            return Response.status(Response.Status.NOT_FOUND).entity("{\"status\":\"PET NOT FOUND\"}").build();
        }
    }

    @Path("addevent/{id}")
    @PUT
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEventJson(String json, @PathParam("id") int id)
    {

        Pet pet = new Pet();
        pet.setId(id);
        Event event = gson.fromJson(json, Event.class);
        System.out.println(json);
        try
        {
            f.getPet(pet);
            pet = f.getPet(pet);
            event.setPet(pet);
            pet.addEvent(event);

            f.editPet(pet);

            return Response.ok().entity(json).build();

        } catch (Exception e)
        {
            System.out.println(e);
            return Response.status(Response.Status.NOT_FOUND).entity("{\"status\":\"PET NOT FOUND\"}").build();
        }
    }
}
