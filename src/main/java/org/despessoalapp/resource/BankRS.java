package org.despessoalapp.resource;

import io.quarkus.panache.common.Sort;
import org.despessoalapp.Resources;
import org.despessoalapp.dto.BankDto;
import org.despessoalapp.model.Bank;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Tag(name = "Bank", description = "Resource responsible for availability of endpoints related to Banks")
@Path(Resources.BANK)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class BankRS {

    @GET
    @Operation(description = "Bank list")
    public Response list() {
        return Response.ok(Bank.find("active", true, Sort.descending("createdAt"))).build(); }

    @POST
    @Operation(description = "Save a bank")
    public Response save(BankDto dto) {
        Bank entity = new Bank(dto);
        entity.persistAndFlush();
        return Response.ok(entity).build();
    }

}