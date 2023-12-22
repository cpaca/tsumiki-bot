package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class stop extends CommandProcessor {

    private static final String STOP =
            "█████░█████░█████░████░░ \n" +
                    "█░░░░░░░█░░░█░░░█░█░░░█░ \n" +
                    "█████░░░█░░░█░░░█░████░░ \n" +
                    "░░░░█░░░█░░░█░░░█░█░░░░░ \n" +
                    "█████░░░█░░░█████░█░░░░░";

    public stop(){
        cmd = "stop";
        help = "Tells everyone to stop, in big bold text.";
        setCategory("TextIO");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        event.reply(STOP).queue();
    }
}
