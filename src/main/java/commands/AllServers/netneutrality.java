package commands.AllServers;

import core.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class netneutrality extends Command {

    public netneutrality(){
        cmd = "netneutrality";
        help = "(Outdated Command) Net neutrality is dead!";
        setCategory("images");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("NET NEUTRALITY HAS DIED. To continue reading this message, please pay $5.","fjChkf0.png");
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
