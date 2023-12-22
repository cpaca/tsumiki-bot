package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class grammar extends CommandProcessor {

    public grammar(){
        cmd = "grammar";
        help = "Proper grammar working properly";
        setCategory("images");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        EmbedBuilder builder = buildImgur("And here, we have bad grammar.exe running.","9snc5WN.jpg");
        event.replyEmbeds(builder.build()).queue();
    }
}
