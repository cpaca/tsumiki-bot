package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class grammar extends CommandProcessor {

    public grammar(){
        cmd = "grammar";
        help = "Proper grammar working properly";
        setCategory("images");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("And here, we have bad grammar.exe running.","9snc5WN.jpg");
        event.getChannel().sendMessageEmbeds(builder.build()).queue();
    }
}
