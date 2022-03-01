package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class suckdown extends CommandProcessor {

    public suckdown(){
        cmd = "suckdown";
        help = "Tells the person below that they __suck__";
        setCategory("TextIO");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        event.getChannel().sendMessage(":arrow_double_down: The next person to send a message sucks! :arrow_double_down:").queue();
    }
}
