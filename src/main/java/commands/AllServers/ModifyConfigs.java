package commands.AllServers;

import Filehandling.Data;
import Filehandling.Filehandler;
import core.CommandProcessor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public class ModifyConfigs extends CommandProcessor {

    public ModifyConfigs(){
        cmd = "dev";
        help = "Used by the developer to do random things.";
        setCategory("Developer");
    }

    @Override
    protected CommandDataImpl UpdateCommandData(CommandDataImpl data) {
        SubcommandData activate = new SubcommandData("activate", "Activate developer mode");
        SubcommandData deactivate = new SubcommandData("deactivate", "Deactivate developer mode");

        data.addSubcommands(activate,deactivate);

        return super.UpdateCommandData(data);
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandInteractionEvent event) {
        if(event.getUser().getIdLong() == 205011703891623936L){
            String subcomm = event.getSubcommandName();
            Data c = Filehandler.getConfig("developer");

            if(c == null){
                event.reply("Wait, the dev file doesn't exist?").queue();
                return;
            }

            if(subcomm == null){
                event.reply("You didn't insert anything.").queue();
                return;
            }
            else if(subcomm.equals("activate")){
                c.setData("O5","1");
                event.reply("Activating dev mode.").queue();

                return;
            }
            else if(subcomm.equals("deactivate")){
                c.setData("O5","0");
                event.reply("Deactivating dev mode").queue();

                return;
            }

            Filehandler.saveData(c);
        }
        else{
            event.reply("You aren't a dev.").queue();
        }
    }

    @Override
    public boolean canUseCommand(MessageReceivedEvent event) {
        return true; // The developer override shit.
    }
}
