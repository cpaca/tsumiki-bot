package commands.DraconiaServer;

import ListenerV2.DraconiaListenerV2;
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
        DraconiaListenerV2.Registered = event.getChannel();
    }
}
