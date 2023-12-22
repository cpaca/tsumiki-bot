package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;

import java.util.concurrent.TimeUnit;

public class meat extends CommandProcessor {

    public meat(){
        cmd = "meat";
        help = "meat, for the first catgirl who gets it";
        setCategory("images");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        EmbedBuilder meat = buildImage("","https://cdn.discordapp.com/attachments/626218261343764503/662321236654948372/meat.png");
        EmbedBuilder meow = buildImage("","https://cdn.discordapp.com/emojis/484904964985061376.gif?v=1");
        InteractionHook msg = event.replyEmbeds(meat.build()).complete();
        msg.editOriginalEmbeds(meow.build()).queueAfter(5, TimeUnit.SECONDS);
    }

}
