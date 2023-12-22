package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class netneutrality extends CommandProcessor {

    public netneutrality(){
        cmd = "netneutrality";
        help = "(Outdated Command) Net neutrality is dead!";
        setCategory("images");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        EmbedBuilder builder = buildImgur("NET NEUTRALITY HAS DIED. To continue reading this message, please pay $5.","fjChkf0.png");
        event.replyEmbeds(builder.build()).queue();
    }
}
