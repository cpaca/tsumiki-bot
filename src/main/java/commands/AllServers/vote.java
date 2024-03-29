package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class vote extends CommandProcessor {
    static final String message = "Vote on option A,B,C, or D here!";

    public vote(){
        cmd = "vote";
        help = "Starts a vote and automatically assigns A, B, C, and D emojis to react to.";
        setCategory("TextIO");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        Message msg = event.getChannel().sendMessage(vote.message).complete();
        msg.addReaction("\uD83C\uDD70").queue();
        msg.addReaction("🅱").queue();
        msg.addReaction("\uD83C\uDDE8").queue();
        msg.addReaction("\uD83C\uDDE9").queue();
    }
}
