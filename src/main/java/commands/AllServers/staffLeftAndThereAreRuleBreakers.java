package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class staffLeftAndThereAreRuleBreakers extends CommandProcessor {

    public staffLeftAndThereAreRuleBreakers(){
        cmd = "staffleftandtherearerulebreakers";
        help = "Oh god oh fuck staff left and people are breaking rules";
        setCategory("images");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        EmbedBuilder builder = buildImgur("WHERE IS YOUR GOD NOW??","oNGysET.png");
        event.getChannel().sendMessageEmbeds(builder.build()).queue();
    }
}
