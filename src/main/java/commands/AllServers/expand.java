package commands.AllServers;

import core.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class expand extends Command {

    public expand(){
        cmd = "expand";
        help = "e x p a n d s a messge";
        setCategory("TextIO");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        if(message.length() == 0)
            return;
        StringBuilder out = new StringBuilder();
        for(char c:message.toCharArray()){
            out.append(c);
            out.append(" ");
        }
        if(out.length() > 1900){
            event.getChannel().sendMessage("Message too l o n g!").queue();
            return;
        }
        event.getChannel().sendMessage(out.toString()).queue();
    }
}
