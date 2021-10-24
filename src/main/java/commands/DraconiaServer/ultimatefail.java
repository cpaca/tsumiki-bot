package commands.DraconiaServer;

import core.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ultimatefail extends Command {

    public ultimatefail(){
        cmd = "ultimatefail";
        help = "Ultimate fail.";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("FAAAAAAIL","cPdewdX.png");
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
