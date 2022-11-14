package commands.DraconiaServer;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class endgame extends CommandProcessor {

    public endgame(){
        cmd = "endgame";
        help = "Tells everyone that the game has ended. Usage: ``endgame [game name]``";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {

    }
}
