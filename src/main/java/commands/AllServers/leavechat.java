package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class leavechat extends CommandProcessor {

    public leavechat(){
        cmd = "leavechat";
        help = "<Assistant has left the chatroom>";
        setCategory("TextIO");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        event.getChannel().sendMessage(mention(event.getMember()) + " has left the chatroom.").queue();
    }
}
