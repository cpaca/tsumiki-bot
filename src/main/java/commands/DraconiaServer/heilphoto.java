package commands.DraconiaServer;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class heilphoto extends CommandProcessor {

    public heilphoto(){
        cmd = "heilphoto";
        help = "Heil da photo!";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("Heil photo!","IRm6gkR.png");
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
