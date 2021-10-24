package commands.SCP;

import core.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Basic914Test extends Command {

    public Basic914Test(){
        cmd = "914test";
        help = "Gives a blank scp-914 test.";
        setCategory("Fac19-23");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        event.getChannel().sendMessage("```" +
                "**Name:**\n" +
                "**Date:**\n" +
                "**Total Items:**\n" +
                "\n" +
                "**Input:**\n" +
                "**Setting:**\n" +
                "**Output:**\n" +
                "\n" +
                "**Input:**\n" +
                "**Setting:**\n" +
                "**Output:**\n" +
                "```").queue();
    }

    @Override
    public boolean canUseCommand(MessageReceivedEvent event) {
        if(event.getGuild().getId().equals("569363224009637920")){
            return true;
        }
        return super.canUseCommand(event);
    }
}
