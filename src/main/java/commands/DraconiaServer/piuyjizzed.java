package commands.DraconiaServer;

import core.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class piuyjizzed extends Command {

    public piuyjizzed(){
        cmd = "piuyjizzed";
        help = "[DATA EXPUNGED]";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("","GGItVCE.png");
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
