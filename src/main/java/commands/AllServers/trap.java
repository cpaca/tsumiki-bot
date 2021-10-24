package commands.AllServers;

import core.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class trap extends Command {

    public trap(){
        cmd = "trap";
        help = "Shows you an image of a trap. Or maybe I'm tricking you and this is all another trap.";
        setCategory("images");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("IT'S A TRAP","gfsxJVA.gif");
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
