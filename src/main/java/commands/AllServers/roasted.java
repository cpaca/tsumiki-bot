package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class roasted extends CommandProcessor {

    public roasted(){
        cmd = "roasted";
        help = "Use when someone has been roasted.";
        setCategory("images");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        EmbedBuilder builder = buildImgur("BUUUUUUUURNED","zRT5ai7.png");
        event.replyEmbeds(builder.build()).queue();
    }
}
