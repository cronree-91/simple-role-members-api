package jp.cron.sample.bot;

import jp.cron.sample.api.JDAProvider;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeneralListener extends Listener {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JDAProvider jdaProvider;

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        logger.info("Bot is ready");
        logger.info("BOT NAME: " + event.getJDA().getSelfUser().getName());

        event.getJDA().getPresence().setPresence(Activity.playing("Ready!"), false);

        jdaProvider.set(event.getJDA());
    }

}


