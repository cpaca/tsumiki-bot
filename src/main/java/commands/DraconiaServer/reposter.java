package commands.DraconiaServer;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class reposter extends CommandProcessor {

    public reposter(){
        cmd = "reposter";
        help = "WRYYYpost!";
        setCategory("Draconia");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder rp1 = buildImgur("Repost # 1","lR2Hlkd.png");
        EmbedBuilder rp2 = buildImgur("Repost # 2","ssYF9mu.png");
        event.getChannel().sendMessageEmbeds(rp1.build()).queue();
        event.getChannel().sendMessageEmbeds(rp2.build()).queue();

    }
}
