package resources;

import dao.PassportsDAO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import modal.Passport;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HEAD;
import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Path("passportRWS")
public class PassportsResource {

    @Context
    private UriInfo context;

    private PassportsDAO dao = new PassportsDAO();

    @OPTIONS
    public Response doOptionsCollection() {
        Set<String> api = new TreeSet<>();
        api.add("GET");
        api.add("POST");
        api.add("DELETE");
        api.add("HEAD");

        return Response
                .noContent()
                .allow(api)
                .build();
    }

    @HEAD
    public Response doHeadCollection() {
        return Response
                .noContent()
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAllPassports() {

        List<Passport> passportList = dao.getAllPassportDetails();

        GenericEntity<List<Passport>> entity;
        entity = new GenericEntity<List<Passport>>(passportList) {
        };

        return Response
                .status(Response.Status.OK)
                .entity(entity)
                .build();
    }

    @DELETE
    public Response deleteAllPassports() {

        dao.deleteAllPassportDetails();

        return Response
                .noContent()
                .build();
    }

    /**
     * ** ID based filter ***
     */
    @OPTIONS
    @Path("id/{id}")
    public Response doOptionsCollectionForId() {
        Set<String> api = new TreeSet<>();
        api.add("GET");
        api.add("DELETE");
        api.add("HEAD");
        api.add("PUT");

        return Response
                .noContent()
                .allow(api)
                .build();

    }

    @HEAD
    @Path("id/{id}")
    public Response doHeadCollectionForId() {
        return Response
                .noContent()
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @GET
    @Path("id/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getPassportsById(@PathParam("id") String id) {

        Passport passport = dao.getPassportById(id);

        GenericEntity<Passport> entity;
        entity = new GenericEntity<Passport>(passport) {
        };

        if (passport == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("<Passport Club Not Found />")
                    .build();
        } else {
            return Response
                    .status(Response.Status.OK)
                    .entity(entity)
                    .build();
        }

    }

    @DELETE
    @Path("id/{id}")
    public Response deleteAllPassportsById(@PathParam("id") String id) {

        dao.deletePassportDetailsById(id);

        return Response
                .noContent()
                .build();
    }

    /**
     * ** COUNTRY based filter ***
     */
    @OPTIONS
    @Path("country/{country}")
    public Response doOptionsCollectionForCountry() {
        Set<String> api = new TreeSet<>();
        api.add("GET");
        api.add("DELETE");
        api.add("HEAD");

        return Response
                .noContent()
                .allow(api)
                .build();

    }

    @HEAD
    @Path("country/{country}")
    public Response doHeadCollectionForCountry() {
        return Response
                .noContent()
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @GET
    @Path("country/{country}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getPassportsByCountry(@PathParam("country") String country) {

        List<Passport> passportList = dao.getPassportByCountry(country);

        GenericEntity<List<Passport>> entity;
        entity = new GenericEntity<List<Passport>>(passportList) {
        };

        if ((passportList == null) || (passportList.isEmpty())) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("<Passport Not Found />")
                    .build();
        } else {
            return Response
                    .status(Response.Status.OK)
                    .entity(entity)
                    .build();
        }

    }

    @DELETE
    @Path("country/{country}")
    public Response deleteAllPassportsByCountry(@PathParam("country") String country) {

        dao.deletePassportDetailsByCountry(country);

        return Response
                .noContent()
                .build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addPassport(Passport passport) {

        if (dao.addPassport(passport) < 0) {
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity("<duplicateNameError />")
                    .build();
        }

        return Response
                .status(Response.Status.CREATED)
                .entity(passport)
                .build();
    }

    @PUT
    @Path("id/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response putBankAccount(Passport passport,
            @PathParam("id") String id) {

        if (passport.getId().equalsIgnoreCase(id)) {

            if (dao.getPassportById(id) != null) {

                dao.updatePassport(passport);

                return Response
                        .status(Response.Status.OK)
                        .entity(passport)
                        .build();
            } else {
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .entity("<Passport Not Found />")
                        .build();
            }
        } else {
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity("<mismatchError />")
                    .build();
        }
    }

}
