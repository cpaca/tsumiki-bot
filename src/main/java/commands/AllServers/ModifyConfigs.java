package commands.AllServers;

import Filehandling.Data;
import Filehandling.Filehandler;
import core.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ModifyConfigs extends Command {

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        if(event.getAuthor().getId().equals("205011703891623936")){
            Data c = Filehandler.getConfig("developer");
            if(message.equalsIgnoreCase("activate developer mode")){
                if(c != null) {
                    c.setData("O5", "1");
                    Filehandler.saveData(c);

                    event.getChannel().sendMessage("Understood. Welcome, O5-1.").queue();
                }
            }
            else if(message.equalsIgnoreCase("deactivate developer mode")){
                if(c != null){
                    c.setData("O5", "0");
                    Filehandler.saveData(c);

                    event.getChannel().sendMessage("Understood. Returning to hiding.").queue();
                }
            }
            if(!super.isDeveloper(event)){
                return;
            }
            if(message.equalsIgnoreCase("join vc")){
                joinMemberVoiceChannel(event.getMember());
            }
            if(message.equalsIgnoreCase("leave vc")){
                leaveVoiceChannel(event.getGuild());
            }
        }
    }

    @Override
    public boolean canUseCommand(MessageReceivedEvent event) {
        return true; // The developer override shit.
    }
}
