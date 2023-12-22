package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class meow extends CommandProcessor {

    public meow(){
        cmd = "meow";
        help = "Meows at you.";
        setCategory("images");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        EmbedBuilder builder = buildImage("","https://cdn.discordapp.com/emojis/484904964985061376.gif?v=1");
        event.replyEmbeds(builder.build()).queue();
    }
}
