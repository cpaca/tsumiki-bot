package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ea extends CommandProcessor {

    public ea(){
        cmd = "ea";
        help = "This is EA in a nutshell";
        setCategory("images");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandInteractionEvent event) {
        EmbedBuilder builder = buildImgur("And here, this is EA in a nutshell.","hseH4nl.png");
        event.replyEmbeds(builder.build()).queue();
    }
}
