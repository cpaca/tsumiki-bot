package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class care extends CommandProcessor {

    public care(){
        cmd = "care";
        help = "When you really don't care.";
        setCategory("TextIO");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        event.getChannel().sendMessage(mention(event.getMember()) + " cares.\nNow it's your turn to tell a lie!").queue();
    }
}
