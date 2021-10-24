package commands.DraconiaServer;

import core.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class prick extends Command {

    public prick(){
        cmd = "prick";
        help = "What a prick.";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("What a bloody fucking prick.","niVRyaN.png");
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
