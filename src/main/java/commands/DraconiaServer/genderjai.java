package commands.DraconiaServer;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class genderjai extends CommandProcessor {

    public genderjai(){
        cmd = "genderjai";
        help = "What is jai's gender?";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("Jai's gender is 2 girls","PXUv9Rq.png");
        event.getChannel().sendMessageEmbeds(builder.build()).queue();
    }
}
