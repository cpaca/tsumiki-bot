package commands.DraconiaServer;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class beebeinganidiot extends CommandProcessor {

    public beebeinganidiot(){
        cmd = "beebeinganidiot";
        help = "Beestupid";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("rerorerorerorero","4rNluUa.png");
        event.getChannel().sendMessageEmbeds(builder.build()).queue();
        EmbedBuilder builder2 = buildImgur("The seerfsooooons!","hOrx6vu.png");
        event.getChannel().sendMessageEmbeds(builder2.build()).queue();
    }
}
