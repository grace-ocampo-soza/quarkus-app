package com.quarkus;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.Message;
import org.eclipse.microprofile.context.ManagedExecutor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class GreetingService {

    @Inject ManagedExecutor executor;

    @ConsumeEvent("greeting")
    public String consume(String name) {
        return "Hello"+ name.toUpperCase();
    }

    @ConsumeEvent
    public Uni<String> process(String name) {
       return Uni.createFrom().item(() -> name.toUpperCase()).emitOn(executor);
    }

   @ConsumeEvent
    public void consume(Message<String> msg) {
       System.out.println(msg.address());
       System.out.println(msg.body());
   }
}
