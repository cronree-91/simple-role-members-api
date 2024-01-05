package jp.cron.sample.bot;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.vdurmont.emoji.EmojiParser;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class Bot {

    @Autowired
    List<Listener> listeners;

    @Value("${bot.setting.ownerId}")
    String ownerId;
    @Value("${bot.setting.token}")
    String token;

    public Bot() {
    }

    @Bean
    public JDABuilder jdaBuilder() {
        return JDABuilder.create(token, List.of(GatewayIntent.GUILD_MEMBERS))
                .setBulkDeleteSplittingEnabled(true)
                .setAutoReconnect(true)
                .setEnableShutdownHook(true)
                .setActivity(Activity.playing("準備中... / Loading..."))
                .addEventListeners(listeners.toArray(new Listener[0]));
    }
}
