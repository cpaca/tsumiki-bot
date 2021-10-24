package commands.AllServers;

import core.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class australianserver extends Command {

    public australianserver(){
        cmd = "australianserver";
        help = "Use when someone *doesn't* swear";
        setCategory("images");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("sOrRy,tHiS iS aN aUsTrAlIaN sErVeR sO yOu DiDn'T sWeAr EnOuGh","L1BPVUT.png");
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
