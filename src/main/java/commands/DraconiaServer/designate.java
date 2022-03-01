package commands.DraconiaServer;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class designate extends CommandProcessor {

    public designate(){
        cmd = "designate";
        help = "designates the channel as the server-status channel";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {

    }
}
