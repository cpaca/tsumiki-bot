package commands.DraconiaServer;

import core.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class failuretoedit extends Command {

    public failuretoedit(){
        cmd = "failuretoedit";
        help = "This is the sxth time I've written this. (editted) (edited) (editttted)";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder nested = buildImgur("Nested!","fcPfozw.png");
        EmbedBuilder doublenest = buildImgur("Double Nested!","mIloho5.png");
        EmbedBuilder failure = buildImgur("FAIL.","KclcO0t.png");
        event.getChannel().sendMessage(nested.build()).queue();
        event.getChannel().sendMessage(doublenest.build()).queue();
        event.getChannel().sendMessage(failure.build()).queue();
    }
}
