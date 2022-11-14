package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class usercount extends CommandProcessor {

    public usercount(){
        cmd = "usercount";
        help = "Tells you the # of players in the server.";
        setCategory("Info");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandInteractionEvent event) {
        String out = "";

        Guild g = event.getGuild();
        if(g == null){
            out += "Did you run this command in a discord server? Somehow I can't find this server...\n";
            out += "I'm as surprised as you are.\n";
            event.reply(out).queue();
            return;
        }
        event.reply("There are " + g.getMembers().size() + " users in the guild.").queue();
    }
}
