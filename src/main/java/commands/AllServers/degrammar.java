package commands.AllServers;

import core.Command;
import core.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class degrammar extends Command {

    public degrammar(){
        cmd = "degrammar";
        help = "Makes the grammar in your sentence *worse*.";
        setCategory("TextIO");
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        StringBuilder out = new StringBuilder();
        boolean first = true;
        for(String word : message.split(" ")) {
            if(first){
                first = false;
            }
            else{
                out.append(" ");
            }
            out.append(scrambleWord(word));
        }
        event.getChannel().sendMessage(out.toString()).queue();
    }

    public String scrambleWord(String word){
        String originWord = word;
        word = word.replace("'","");
        word = word.replace(",","");
        word = word.replace(".","");
        word = word.replace("!","");
        word = word.replace("?","");
        if (originWord.equals(word) && word.length() > 1) {
            int num = word.length() - 1;
            num = Main.rand.nextInt(num);
            String before = word.substring(num, num+1);
            String after = word.substring(num+1, num+2);
            word = word.substring(0, num) + after + before + word.substring(num+2);
        }
        return word;
    }

}
