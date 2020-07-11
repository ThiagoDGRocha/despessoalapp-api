package org.despessoalapp.resource;

import io.quarkus.panache.common.Sort;
import org.despessoalapp.Resources;
import org.despessoalapp.dto.TransactionDto;
import org.despessoalapp.model.Transaction;
import org.despessoalapp.service.BalanceService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Tag(name = "Transaction", description = "Resource responsible for availability of endpoints related to Transactions")
@Path(Resources.TRANSACTION)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class TransactionRS {

    @Inject
    BalanceService service;

    @GET
    @Operation(description = "Transaction list")
    public Response list(@NotBlank @QueryParam("index") int index, @NotBlank @QueryParam("offset") int offset) {
        return Response.ok(
                Transaction.find("active", true, Sort.descending("createdAt")).
                        page(index, offset)).build(); }

    @POST
    @Operation(description = "Save a Transaction")
    public Response save(TransactionDto dto) {
        Transaction entity = new Transaction(dto);
        entity.persistAndFlush();

        service.updateBalance(entity, "save");
        return Response.ok().build();
    }

    @DELETE
    @Operation(description = "Cancel a Transaction")
    public Response cancel(Long id) {
        Transaction entity = Transaction.findById(id);
        Transaction.update("active = ?1 where id = ?2", false, entity.getId());

        service.updateBalance(entity, "cancel");
        return Response.ok().build();
    }
}