package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class meow extends CommandProcessor {

    public meow(){
        cmd = "meow";
        help = "Meows at you.";
        setCategory("images");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event){
        EmbedBuilder builder = buildImage("","https://cdn.discordapp.com/emojis/484904964985061376.gif?v=1");
        event.getChannel().sendMessageEmbeds(builder.build()).queue();
    }
}
