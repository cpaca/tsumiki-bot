package commands.DraconiaServer;

import core.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class beebeinganidiot extends Command {

    public beebeinganidiot(){
        cmd = "beebeinganidiot";
        help = "Beestupid";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("rerorerorerorero","4rNluUa.png");
        event.getChannel().sendMessage(builder.build()).queue();
        EmbedBuilder builder2 = buildImgur("The seerfsooooons!","hOrx6vu.png");
        event.getChannel().sendMessage(builder2.build()).queue();
    }
}
