package commands.DraconiaServer;

import core.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class beenacl extends Command {

    public beenacl(){
        cmd = "beenacl";
        help = "Bees and Salt mixing together";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("Beesalt","t5e2pfB.png");
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
