package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class guildcount extends CommandProcessor {

    public guildcount(){
        cmd = "guildcount";
        help = "Counts how many guilds which use this bot.";
        setCategory("Developer");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        if(event.getUser().getId().equals("205011703891623936")){
            event.reply("Bot is running on " + event.getJDA().getGuilds().size() + " guilds.").queue();
            return;
        }
        event.reply("Bot is running on [REDACTED] guilds.").queue();
    }
}
