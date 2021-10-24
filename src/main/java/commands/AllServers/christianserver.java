package commands.AllServers;

import core.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class christianserver extends Command {

    public christianserver(){
        cmd = "christianserver";
        help = "Use when someone swears.";
        setCategory("images");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("sOrRy,tHiS iS a ChRiStIaN SeRvEr So No SwEaRiNg","5t3zwBU.png");
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
