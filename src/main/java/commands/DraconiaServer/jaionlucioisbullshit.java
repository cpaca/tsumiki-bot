package commands.DraconiaServer;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class jaionlucioisbullshit extends CommandProcessor {

    public jaionlucioisbullshit(){
        cmd = "jaionlucioisbullshit";
        help = "Jai on lucio is bullshit.";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("Jai on lucio is utter bullshit.","3Rdkjwb.gif");
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
