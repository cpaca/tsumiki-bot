package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class christianserver extends CommandProcessor {

    public christianserver(){
        cmd = "christianserver";
        help = "Use when someone swears.";
        setCategory("images");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandInteractionEvent event) {
        EmbedBuilder builder = buildImgur("sOrRy,tHiS iS a ChRiStIaN SeRvEr So No SwEaRiNg","5t3zwBU.png");
        event.replyEmbeds(builder.build()).queue();
    }
}
