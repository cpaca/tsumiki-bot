package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class selfShutdown extends CommandProcessor {

    public selfShutdown(){
        cmd = "shutdown";
        help = "Shuts down the bot. Only usable if you're the dev!";
        setCategory("Developer");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        if(super.isDeveloper(event.getUser())){
            System.out.println("Shutting down.");
            event.reply("Understood.").queue();
            event.getJDA().shutdownNow();
        }
    }
}
