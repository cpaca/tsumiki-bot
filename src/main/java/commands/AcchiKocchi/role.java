package commands.AcchiKocchi;

import core.CommandProcessor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class role extends CommandProcessor {

    public role(){
        cmd = "role";
        help = "Used to add or remove roles from yourself.";
        setCategory("Acchi Kocchi");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        if(!message.contains(" ")){
            event.getChannel().sendMessage("Invalid input.").queue();
            return;
        }
        Member m = event.getMember();
        if(m == null) {
            return;
        }
        String operation = message.substring(0,message.indexOf(" "));
        message = message.substring(message.indexOf(" ") + 1);
        message = message.toLowerCase();
        if(!getroleinfo.map.containsKey(message)){
            event.getChannel().sendMessage("Invalid role!").queue();
            return;
        }
        Role role = event.getGuild().getRoleById(getroleinfo.map.get(message));
        if(role == null){
            event.getChannel().sendMessage("Looks like some RoleIDs have changed. Go tell Tsumiki-chan, please!").queue();
            return;
        }
        if(operation.equals("add")){
            event.getGuild().addRoleToMember(m,role).queue();
        }
        else if(operation.equals("remove")){
            event.getGuild().removeRoleFromMember(m,role).queue();
        }
        else{
            event.getChannel().sendMessage("Invalid operation.").queue();
        }
    }
}
