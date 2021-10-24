package commands.UWSLPassive;

import core.Command;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.concurrent.TimeUnit;

public class PrayerReact extends Command {

    public PrayerReact(){

    }

    @Override
    public void MessageReceived(String message, MessageReceivedEvent event){
        if(event.getChannel().getId().equals("663851002390380614")){
            Emote e = event.getGuild().getEmoteById("467923623752237056");
            if(e == null)
                return;
            event.getMessage().addReaction(e).queueAfter(10, TimeUnit.SECONDS);
        }
    }
}
