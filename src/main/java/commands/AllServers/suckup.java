package commands.AllServers;

import core.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class suckup extends Command {

    public suckup(){
        cmd = "suckup";
        help = "Tells the person above that they __suck__";
        setCategory("TextIO");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        event.getChannel().sendMessage(":arrow_double_up: The last person to send a message sucks! :arrow_double_up:").queue();
    }

}
