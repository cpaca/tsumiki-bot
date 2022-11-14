package commands.DraconiaServer;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class lag extends CommandProcessor {

    public lag(){
        cmd = "lag";
        help = "help me i'm lagging";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("L A G","kDZ0iSg.png");
        event.getChannel().sendMessageEmbeds(builder.build()).queue();
    }
}
