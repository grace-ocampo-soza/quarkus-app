package com.quarkus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.context.ManagedExecutor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CustomerService {

    @Inject
    ManagedExecutor executor;

    @ConsumeEvent("callcustomer")
    public Uni<String> process(String c) {
        Customer customer = JsonHelper.parse(c);
        return Uni.createFrom().item(() -> "Hello! I am " + customer.getName() + " " + customer.getSurname() + ". How are you doing?");
    }
}
