package commands.AllServers;

import core.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.concurrent.TimeUnit;

public class meat extends Command {

    public meat(){
        cmd = "meat";
        help = "meat, for the first catgirl who gets it";
        setCategory("images");
    }

    @Override
    public void MessageReceived(String s, MessageReceivedEvent event){
        EmbedBuilder meat = buildImage("","https://cdn.discordapp.com/attachments/626218261343764503/662321236654948372/meat.png");
        EmbedBuilder meow = buildImage("","https://cdn.discordapp.com/emojis/484904964985061376.gif?v=1");
        Message msg = event.getChannel().sendMessage(meat.build()).complete();
        msg.editMessage(meow.build()).queueAfter(5, TimeUnit.SECONDS);
    }

}
