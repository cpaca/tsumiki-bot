package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;


public class australianserver extends CommandProcessor {

    public australianserver(){
        cmd = "australianserver";
        help = "Use when someone *doesn't* swear";
        setCategory("images");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        EmbedBuilder builder = buildImgur("sOrRy,tHiS iS aN aUsTrAlIaN sErVeR sO yOu DiDn'T sWeAr EnOuGh","L1BPVUT.png");
        event.replyEmbeds(builder.build()).queue();
    }
}
