package commands.DraconiaServer;

import core.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class yourecrap extends Command {

    public yourecrap(){
        cmd = "yourecrap";
        help = "Nope, you're just shite.";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("Nope! You're just shite!","xdc5L7J.png");
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
