package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class staffLeftAndThereAreRuleBreakers extends CommandProcessor {

    public staffLeftAndThereAreRuleBreakers(){
        cmd = "staffleftandtherearerulebreakers";
        help = "Oh god oh fuck staff left and people are breaking rules";
        setCategory("images");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        EmbedBuilder builder = buildImgur("WHERE IS YOUR GOD NOW??","oNGysET.png");
        event.replyEmbeds(builder.build()).queue();
    }
}
