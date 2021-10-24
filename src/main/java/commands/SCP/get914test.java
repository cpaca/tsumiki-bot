package commands.SCP;

import Filehandling.Filehandler;
import core.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class get914test extends Command {

    public get914test(){
        cmd = "get914test";
        help = "Gets a specific 914 test.";
        setCategory("Fac19-23");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        int testnum;
        try {
            testnum = Integer.parseInt(message);
        } catch (NumberFormatException nfe){
            event.getChannel().sendMessage("\"" + message + "\" is not a valid number!").queue();
            return;
        }
        if(testnum > 10000){
            event.getChannel().sendMessage("You must be really far in the future, " +
                    "because I'm built to force-stop requests for test 10,000 and higher!").queue();
            return;
        }
        if(testnum < 100){
            event.getChannel().sendMessage("Test number too small!").queue();
            return;
        }
        String testID = Integer.toString(testnum);
        while(testID.length() < 5){
            testID = "0" + testID;
        }
        Scanner scan;
        try {
            scan = new Scanner(Filehandler.getFile("914tests\\" + testID + ".txt"));
        }catch (FileNotFoundException fnfe){
            event.getChannel().sendMessage("File-not-found-error! If the test you asked for actually does exist," +
                    "then go tell Miniwa to run download914test!").queue();
            return;
        }
        List<String> fields = new ArrayList<>();
        String title = "";
        String out = "";
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            if(line.length() > 900){
                event.getChannel().sendMessage("**__Internal error; one line of input was >900 chars!__**").queue();
                return;
            }
            if(out.length() + line.length() > 1000){
                fields.add(out);
                out = line;
            }
            else{
                if(title.equals("")){
                    title = line;
                }
                out += line + "\n";
            }
        }
        if(!out.equals("")) {
            fields.add(out);
        }
        EmbedBuilder builder = new EmbedBuilder();
        int numFields = 0;
        for (String field : fields){
            if(numFields == 4){
                numFields = 0;
                event.getChannel().sendMessage(builder.build()).queue();
                builder = new EmbedBuilder();
            }
            numFields++;
            builder.addField("",field,false);
        }
        if(numFields != 0) {
            event.getChannel().sendMessage(builder.build()).queue();
        }
    }

    @Override
    public boolean canUseCommand(MessageReceivedEvent event) {
        if(event.getGuild().getId().equals("569363224009637920")){
            return true;
        }
        return super.canUseCommand(event);
    }
}
