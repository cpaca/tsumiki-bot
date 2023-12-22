package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class hammertime extends CommandProcessor {

    private static final String hammertime =
            "" +
                    "█░█░███░█████░█████░███░██░░███░███░█████░███\n" +
                    "█░█░█░█░█░█░█░█░█░█░█░░░█░█░░█░░░█░░█░█░█░█░░\n" +
                    "███░███░█░█░█░█░█░█░███░██░░░█░░░█░░█░█░█░███\n" +
                    "█░█░█░█░█░░░█░█░░░█░█░░░█░█░░█░░░█░░█░░░█░█░░\n" +
                    "█░█░█░█░█░░░█░█░░░█░███░█░█░░█░░███░█░░░█░███\n";

    public hammertime(){
        cmd = "hammertime";
        help = "What time is it?";
        setCategory("TextIO");
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        event.reply(hammertime).queue();
    }
}
