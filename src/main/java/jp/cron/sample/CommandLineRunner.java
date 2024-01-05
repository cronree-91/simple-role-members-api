package jp.cron.sample;

import jp.cron.sample.util.ILogger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner, ILogger {

    @Autowired
    JDABuilder jdaBuilder;
    @Override
    public void run(String... args) throws Exception {
        JDA jda = jdaBuilder.build();

        while (jda.getStatus() != JDA.Status.SHUTDOWN) {
        }

        getLogger().warn("SHUTTING DOWN...");

        // SHUTDOWN

        getLogger().warn("SHUTDOWN COMPLETE");
        System.exit(0);
    }
}