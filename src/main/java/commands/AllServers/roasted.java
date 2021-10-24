package commands.AllServers;

import core.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class roasted extends Command {

    public roasted(){
        cmd = "roasted";
        help = "Use when someone has been roasted.";
        setCategory("images");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("BUUUUUUUURNED","zRT5ai7.png");
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
