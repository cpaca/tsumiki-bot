package commands.UWSLPassive;

import core.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Egg extends Command {

    public Egg(){
        // passive
    }

    @Override
    public void MessageReceived(String message, MessageReceivedEvent event){
        message = message.toLowerCase();
        if(message.startsWith("egg") ||
                message.endsWith("egg") ||
                message.contains(" egg ")){
            event.getMessage().addReaction("\uD83E\uDD5A").queue();
        }
    }

}
