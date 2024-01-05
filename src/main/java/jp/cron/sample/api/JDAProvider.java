package jp.cron.sample.api;

import net.dv8tion.jda.api.JDA;
import org.springframework.stereotype.Service;

@Service
public class JDAProvider {
    private JDA jda = null;

    public void set(JDA jda) {
        this.jda = jda;
    }

    public JDA get() {
        return this.jda;
    }

    public boolean isReady() {
        return this.jda != null;
    }

}
