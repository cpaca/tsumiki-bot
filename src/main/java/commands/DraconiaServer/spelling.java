package commands.DraconiaServer;

import core.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class spelling extends Command {

    public spelling(){
        cmd = "spelling";
        help = "corect speling fr ou al";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("Corect Speling!","EBqAbth.png");
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
