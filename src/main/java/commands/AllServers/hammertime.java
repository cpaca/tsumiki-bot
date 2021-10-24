package commands.AllServers;

import core.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class hammertime extends Command {

    private static final String hammertime =
            "" +
                    "█░█░███░█████░█████░███░██░░███░███░█████░███\n" +
                    "█░█░█░█░█░█░█░█░█░█░█░░░█░█░░█░░░█░░█░█░█░█░░\n" +
                    "███░███░█░█░█░█░█░█░███░██░░░█░░░█░░█░█░█░███\n" +
                    "█░█░█░█░█░░░█░█░░░█░█░░░█░█░░█░░░█░░█░░░█░█░░\n" +
                    "█░█░█░█░█░░░█░█░░░█░███░█░█░░█░░███░█░░░█░███\n";

    public hammertime(){
        cmd = "hammertime";
        help = "What time is it?";
        setCategory("TextIO");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        event.getChannel().sendMessage(hammertime).queue();
    }
}
