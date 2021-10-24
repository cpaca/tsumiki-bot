package commands.UWSLPassive;

import core.Command;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class OK extends Command {

    public OK(){
        // passive
    }

    @Override
    public void MessageReceived(String message, MessageReceivedEvent event){
        message = message.toLowerCase();
        if(message.startsWith("ok") ||
        message.endsWith("ok") ||
        message.contains(" ok ")){
            Emote emote = event.getGuild().getEmoteById("422468565304606752");
            if(emote != null) {
                event.getMessage().addReaction(emote).queue();
            }
        }
        if(message.startsWith("egg") ||
        message.endsWith("egg") ||
        message.contains(" egg ")){
            event.getMessage().addReaction("\uD83E\uDD5A").queue();
        }
    }
}
