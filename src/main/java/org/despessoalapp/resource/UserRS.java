package org.despessoalapp.resource;

import org.despessoalapp.Resources;
import org.despessoalapp.dto.UserDto;
import org.despessoalapp.model.User;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Tag(name = "User", description = "Resource responsible for availability of endpoints related to Users")
@Path(Resources.BANK)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class UserRS {

    @GET
    @Operation(description = "Login to app")
    public Response login(@NotBlank @QueryParam("email") String email, @NotBlank @QueryParam("password") String password) {
        return Response.ok(User.find("email = ?1 and password = ?2", email, password)).build();
    }

    @POST
    @Operation(description = "Save a user")
    public Response save(UserDto dto) {
        User user = new User(dto);
        user.persistAndFlush();
        return Response.ok().build();
    }

    @PUT
    @Operation(description = "Update a user")
    public Response update(Long id, UserDto dto) {
        User.update("fullName = ?1 and password = ?2",
                dto.getFullName(), dto.getPassword());

        return Response.ok().build();
    }

    @DELETE
    @Operation(description = "Inactive a user")
    public Response delete(Long id) {
        User.update("active = ?1 where id = ?2", false, id);

        return Response.ok().build();
    }

}
