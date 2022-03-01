package commands.DraconiaServer;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class notacommand extends CommandProcessor {

    public notacommand(){
        cmd = "notacommand";
        help = "I hope this isn't a command.";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("Oh god oh fuck it's a command","upPpVA0.png");
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
