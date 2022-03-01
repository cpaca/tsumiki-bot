package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class stop extends CommandProcessor {

    private static final String STOP =
            "█████░█████░█████░████░░ \n" +
                    "█░░░░░░░█░░░█░░░█░█░░░█░ \n" +
                    "█████░░░█░░░█░░░█░████░░ \n" +
                    "░░░░█░░░█░░░█░░░█░█░░░░░ \n" +
                    "█████░░░█░░░█████░█░░░░░";

    public stop(){
        cmd = "stop";
        help = "Tells everyone to stop, in big bold text.";
        setCategory("TextIO");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        event.getChannel().sendMessage(STOP).queue();
    }
}
