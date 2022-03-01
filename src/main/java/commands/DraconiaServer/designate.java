package commands.DraconiaServer;

import core.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class designate extends Command {

    public designate(){
        cmd = "designate";
        help = "designates the channel as the server-status channel";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {

    }
}
