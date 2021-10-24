package commands.AllServers;

import core.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class mentionme extends Command {

    public mentionme(){
        cmd = "mentionme";
        help = "Mentions the person who runs the command";
        setCategory("TextIO");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        event.getChannel().sendMessage("You wanted to be mentioned, " + mention(event.getMember())).queue();
    }
}
