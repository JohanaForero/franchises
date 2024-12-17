package com.accenture.franchises;

import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.transitions.Mongod;
import de.flapdoodle.embed.mongo.transitions.RunningMongodProcess;
import de.flapdoodle.reverse.TransitionWalker;
import de.flapdoodle.reverse.transitions.Start;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EmbeddedMongoConfig {
    private static TransitionWalker.ReachedState<RunningMongodProcess> running;

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (running != null && running.current() != null) {
                try {
                    running.current().wait(5000);
                    running.current().stop();
                } catch (Exception e) {
                    System.err.println("Error stopping MongoDB: " + e.getMessage());
                }
            }
        }));
        try {
            final Mongod serverMongoDB = Mongod.builder()
                    .net(Start.to(Net.class).initializedWith(Net.defaults().withPort(28017)))
                    .build();
            running = serverMongoDB.start(Version.Main.V5_0);
        } catch (Exception e) {
            throw new IllegalStateException("Error starting embedded MongoDB", e);
        }
    }

}
