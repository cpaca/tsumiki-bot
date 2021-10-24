package commands.DraconiaServer;

import ListenerV2.DraconiaListenerV2;
import core.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class endgame extends Command {

    public endgame(){
        cmd = "endgame";
        help = "Tells everyone that the game has ended. Usage: ``endgame [game name]``";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        DraconiaListenerV2.Registered.sendMessage("Game ended by " + event.getMember().getNickname() + ". Game name: " + message).queue();
    }
}
