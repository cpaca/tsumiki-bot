package commands.AllServers;

import core.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class edit extends Command {

    public edit(){
        cmd = "edit";
        help = "Don't... don't put a * after a message to fix your mistakes";
        setCategory("images");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("Don't use the * to edit messages! Discord has Message editting!","fmrLCWr.png");
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
