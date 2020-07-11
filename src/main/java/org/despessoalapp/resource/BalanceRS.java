package org.despessoalapp.resource;

import org.despessoalapp.Resources;
import org.despessoalapp.service.BalanceService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Tag(name = "Bank balance", description = "Resource responsible for availability of endpoints related to Bank balances")
@Path(Resources.BALANCES)
@Produces(MediaType.APPLICATION_JSON)
public class BalanceRS {

    @Inject
    BalanceService service;

    @GET
    @Operation(description = "List active balance")
    public Response list() { return Response.ok(service.getList()).build(); }

}