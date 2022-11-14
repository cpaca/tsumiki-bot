package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class trap extends CommandProcessor {

    public trap(){
        cmd = "trap";
        help = "Shows you an image of a trap. Or maybe I'm tricking you and this is all another trap.";
        setCategory("images");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandInteractionEvent event) {
        EmbedBuilder builder = buildImgur("IT'S A TRAP","gfsxJVA.gif");
        event.replyEmbeds(builder.build()).queue();
    }
}
