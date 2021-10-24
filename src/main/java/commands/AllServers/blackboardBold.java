package commands.AllServers;

import core.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;

public class blackboardBold extends Command {

    // apparantly the blackboard boldface versions count as 2 characters
    Map<String, String> transformations = new HashMap<>();

    public blackboardBold(){
        cmd = "blackboardbold";
        help = "Turns a string into blackboard boldface";
        setCategory("TextIO");

        String[] from = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".split("");
        int index = 0;

        char firstChar = '\uD835';
        final int firstLetterNum = 56632;
        final int letters = 26 + 26; // 26 letters in the alphabet + 26 lowercase
        int num = firstLetterNum;
        for(int i = 0; i < letters; i++){
            char secondChar = (char) num;
            num++;
            String letter = "" + firstChar + secondChar;
            transformations.put(from[index], letter);
            index++;
        }

        final int firstNumberNum = 57304;
        final int numbers = 10;
        num = firstNumberNum;
        for(int i = 0; i < numbers; i++){
            char secondChar = (char) num;
            num++;
            String letter = "" + firstChar + secondChar;
            transformations.put(from[index], letter);
            index++;
        }

        String[] finalfrom = "[ ] C H N P Q R Z".split(" ");
        String[] finalto = "『 』 ℂ ℍ ℕ ℙ ℚ ℝ ℤ".split(" ");
        for(int i = 0; i< finalfrom.length; i++){
            transformations.put(finalfrom[i], finalto[i]);
        }

        // Testing to make sure it works
        /*
        transformations.forEach((f,t) -> System.out.println("" + f + " " + t));
        //*/
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        if(message.length() == 0){
            return;
        }
        final String[] data = new String[] {message};
        // big brain!
        transformations.forEach((f, t) -> data[0] = data[0].replace(f,t));

        event.getChannel().sendMessage(data[0]).queue();
    }
}
