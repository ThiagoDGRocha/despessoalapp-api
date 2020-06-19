package org.despessoalapp.controle;

import org.despessoalapp.Resources;
import org.despessoalapp.dto.BankDto;
import org.despessoalapp.model.Bank;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Tag(name = "Bancos", description = "Recurso responsável por disponiblizar endpoints relacionados ao Banco")
@Path(Resources.BANK)
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
@Transactional
public class BankControll {

    @GET
    @Operation(description = "Lista bancos")
    public Response list() { return Response.ok(Bank.listAll()).build(); }

    @POST
    @Operation(description = "Salva um banco")
    public Response save(BankDto dto) {
        Bank bank = new Bank(dto);
        bank.persist();
        return Response.ok(bank).build();
    }

}