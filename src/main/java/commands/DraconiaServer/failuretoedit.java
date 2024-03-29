package commands.DraconiaServer;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class failuretoedit extends CommandProcessor {

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
        event.getChannel().sendMessageEmbeds(nested.build()).queue();
        event.getChannel().sendMessageEmbeds(doublenest.build()).queue();
        event.getChannel().sendMessageEmbeds(failure.build()).queue();
    }
}
