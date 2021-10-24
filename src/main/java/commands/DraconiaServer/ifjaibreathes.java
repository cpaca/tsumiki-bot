package commands.DraconiaServer;

import core.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ifjaibreathes extends Command {

    public ifjaibreathes(){
        cmd = "ifjaibreathes";
        help = "If she breathes...";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("THOOOOOOOT","QucdN9L.png");
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
