package commands.DraconiaServer;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ultimatefail extends CommandProcessor {

    public ultimatefail(){
        cmd = "ultimatefail";
        help = "Ultimate fail.";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("FAAAAAAIL","cPdewdX.png");
        event.getChannel().sendMessageEmbeds(builder.build()).queue();
    }
}
