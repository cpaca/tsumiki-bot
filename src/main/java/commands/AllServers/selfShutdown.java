package commands.AllServers;

import core.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class selfShutdown extends Command {

    public selfShutdown(){
        //not a command. Developer item.
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        if(message.equalsIgnoreCase("Shutdown. Now.")){
            if(super.isDeveloper(event)){
                System.out.println("Shutting down.");
                event.getChannel().sendMessage("Understood.").complete();
                event.getJDA().shutdownNow();
            }
        }
    }
}
