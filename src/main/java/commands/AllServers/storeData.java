package commands.AllServers;

import Filehandling.Data;
import Filehandling.Filehandler;
import core.CommandProcessor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class storeData extends CommandProcessor {

    public storeData(){
        cmd = "storeData";
        help = "Toggle between storing and not storing data. Alternatively, wipe all of it.";
        setCategory("Info");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        if(message.equalsIgnoreCase("true")){

            Data d = Filehandler.getUserData(event.getAuthor());
            d.setData("optin","true");
            Filehandler.saveData(d);

            event.getChannel().sendMessage("Storing data now!").queue();
        }
        else if(message.equalsIgnoreCase("false")){

            Data d = Filehandler.getUserData(event.getAuthor());
            d.setData("optin","false");
            Filehandler.saveData(d);

            event.getChannel().sendMessage("No longer storing data. Note that this will not wipe your data.\n" +
                    "To wipe your data entirely, use ``!storeData wipe``").queue();
        }
        else if(message.equalsIgnoreCase("wipe")){
            event.getChannel().sendMessage("Are you sure? Data is irrecoverable if wiped.\n" +
                    "If you are sure, type ``!storeData wipe " + event.getAuthor().getId() + "``").queue();
        }
        else if(message.equalsIgnoreCase("wipe " + event.getAuthor().getId())){

            Data d = Filehandler.getUserData(event.getAuthor());
            d.wipeData();
            Filehandler.saveData(d);

            event.getChannel().sendMessage("Wiping data. Note that this will also opt you out.").queue();
        }
        else{
            event.getChannel().sendMessage("Invalid input. " +
                    "``!storeData true`` to allow me to store data. \n" +
                    "``!storeData false`` to stop me from storing data. \n" +
                    "``!storeData wipe`` to wipe all data I have on you.").queue();
        }
    }
}
