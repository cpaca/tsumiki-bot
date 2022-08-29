package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class edit extends CommandProcessor {

    public edit(){
        cmd = "edit";
        help = "Don't... don't put a * after a message to fix your mistakes";
        setCategory("images");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandInteractionEvent event) {
        EmbedBuilder builder = buildImgur("Don't use the * to edit messages! Discord has Message editting!","fmrLCWr.png");
        event.replyEmbeds(builder.build()).queue();
    }
}
