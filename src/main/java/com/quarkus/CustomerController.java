package com.quarkus;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("customers")
@ApplicationScoped
public class CustomerController {

    @Inject
    CustomerRepository customerRepository;

    @Inject EventBus bus;

    @GET
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @POST
    public Response create(Customer customer) {
        customerRepository.createCustomer(customer);
        return Response.status(201).build();

    }

    @PUT
    public Response update(Customer customer) {
        customerRepository.updateCustomer(customer);
        return Response.status(204).build();
    }

    @DELETE
    public Response delete(@QueryParam("id") Integer customerId) {
        customerRepository.deleteCustomer(customerId);
        return Response.status(204).build();
    }

    @GET
    @Path("/call")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> call(@QueryParam("id") Integer customerId) throws JsonProcessingException {

        return bus.<String>request("callcustomer", JsonHelper.read(customerRepository.findCustomerById(customerId)))
                .onItem().transform(Message::body);

    }

}