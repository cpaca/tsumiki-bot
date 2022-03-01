package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class catify extends CommandProcessor {

    private ArrayList<Catifier> catifiers = new ArrayList<>();
    private static String letters = "";

    public catify(){
        cmd = "catify";
        help = "catifies your message";
        setCategory("TextIO");

        catifiers.add(new Catifier("na","nya").setMiddle(true)); //professionalism to professio*nya*llism
        catifiers.add(new Catifier("no","nyo").setMiddle(true)); //snowman to s*nyo*man

        catifiers.add(new Catifier("a","nya").setStart(true)); // apple to *nya*pple
        catifiers.add(new Catifier("ya","nya").setStart(true)); // yard to *nya*rd
        catifiers.add(new Catifier("me","meow").setEnd(true)); // cumbersome to cumberso*meow*
        catifiers.add(new Catifier("ow","meow").setStart(true)); // owner to *meow*ner
        catifiers.add(new Catifier("mr","mrow").setEnd(true)); // afspacecomr to afspaceco*mrow*

        ////////////////////////////////////////////
        // The rest is just doing that with various casings.
        ////////////////////////////////////////////
        catifiers.add(new Catifier("Na","Nya").setMiddle(true));
        catifiers.add(new Catifier("nA","nYA").setMiddle(true));
        catifiers.add(new Catifier("NA","NYA").setMiddle(true));

        catifiers.add(new Catifier("No","Nyo").setMiddle(true));
        catifiers.add(new Catifier("nO","nYO").setMiddle(true));
        catifiers.add(new Catifier("NO","NYO").setMiddle(true));

        catifiers.add(new Catifier("A","NYA").setStart(true));

        catifiers.add(new Catifier("Ya","NYa").setStart(true));
        catifiers.add(new Catifier("yA","nYA").setStart(true));
        catifiers.add(new Catifier("YA","NYA").setStart(true));

        catifiers.add(new Catifier("Me","Meow").setEnd(true));
        catifiers.add(new Catifier("mE","meOW").setEnd(true));
        catifiers.add(new Catifier("ME","MEOW").setEnd(true));
        catifiers.add(new Catifier("Mr","Mrow").setEnd(true));
        catifiers.add(new Catifier("mR","mrOW").setEnd(true));
        catifiers.add(new Catifier("MR","MROW").setEnd(true));

        catifiers.add(new Catifier("Ow","MEow").setStart(true));
        catifiers.add(new Catifier("oW","meOW").setStart(true));
        catifiers.add(new Catifier("OW","MEOW").setStart(true));

        if(letters.length() == 0) {
            String lowercase = "abcdefghijklmnopqrstuvwxyz";
            letters = lowercase + lowercase.toUpperCase();
        }
    }

    @Override
    protected void MessageReceived(@Nonnull String message, MessageReceivedEvent event) {
        String str = catifyString(message);
        if(str.equals(""))
            return;
        event.getChannel().sendMessage(str).queue();
    }

    @Nonnull
    private String catifyString(@Nonnull String input){
        StringBuilder output = new StringBuilder();
        StringBuilder word = new StringBuilder();
        while(input.length() > 0 ){
            String letter = input.substring(0,1);

            if(letters.contains(letter)){
                word.append(letter);
            }
            else{
                output.append(catifyWord(word.toString()));
                word = new StringBuilder();

                output.append(letter);
            }

            input = input.substring(1);
        }
        output.append(catifyWord(word.toString()));
        //just in case the message ends in a letter so it doesn't miss a word.
        return output.toString();
    }

    private String catifyWord(String input){
        // alphabet only!
        if(input.length() == 0) {
            return "";
        }
        final String init = input;
        for ( Catifier c : catifiers ) {
            input = c.catify(input);
        }
        if(input.equals(init)){
            if(input.lastIndexOf("m") == -1 && input.lastIndexOf("n") == -1){
                return input;
            }
            String replacement;
            int replacementindex;
            if(input.lastIndexOf("m") > input.lastIndexOf("n")){
                replacementindex = input.lastIndexOf("m");
                replacement = "meow";
            }
            else{
                replacementindex = input.lastIndexOf("n");
                replacement = "nyan";
            }
            String pre = input.substring(0,replacementindex);
            String post = input.substring(replacementindex+1);
            if(pre.length() != 0){
                pre = pre + "-";
            }
            if(post.length() != 0){
                post = "-" + post;
            }
            return pre + replacement + post;
        }
        return input;

        /*
        String output = " " + input + " "; // done to account for prefixes and suffixes

        output = output.replace("na","\\*nya\\*"); //professionalism to professio*nya*llism
        output = output.replace("no","\\*nyo\\*"); //snowman to s*nyo*man

        output = output.replace(" a"," nya");  // apple to *nya*pple
        output = output.replace(" ya"," nya"); // yard to *nya*rd
        output = output.replace("me ","meow "); // cumbersome to cumberso*meow*
        output = output.replace(" ow"," meow"); // owner to *meow*ner
        output = output.replace("mr ","\\mrow \\"); // afspacecomr to afspaceco*mrow*

        ////////////////////////////////////////////
        // The rest is just doing that with various casings.
        ////////////////////////////////////////////

        output = output.replace("Na","\\*Nya\\*");
        output = output.replace("nA","\\*nyA\\*");
        output = output.replace("NA","\\*NYA\\*");

        output = output.replace("No","\\*Nyo\\*");
        output = output.replace("nO","\\*nyO\\*");
        output = output.replace("NO","\\*NYO\\*");

        output = output.replace(" A"," NYA");

        output = output.replace(" Ya"," Nya");
        output = output.replace(" yA"," nyA");
        output = output.replace(" YA"," NYA");

        output = output.replace("Me ","Meow ");
        output = output.replace("mE ","mEOW ");
        output = output.replace("ME ","MEOW ");
        output = output.replace("Mr ","Mrow ");
        output = output.replace("mR ","mROW ");
        output = output.replace("MR ","MROW ");

        output = output.replace(" Ow"," meOW");
        output = output.replace(" oW"," meOW");
        output = output.replace(" OW"," MEOW");

        return output;
        */
    }

    private class Catifier{

        private String from, to;
        private boolean start, middle, end;
        //middle will do start AND end as well. EG:
        // fauna => fau*nya*, if middle OR end is active.
        // nap => *nya*p, if middle OR start is active.

        Catifier(String from, String to){
            this.from = from;
            this.to = to;
        }

        Catifier setStart(boolean start){
            this.start = start;
            return this;
        }

        Catifier setMiddle(boolean middle) {
            this.middle = middle;
            return this;
        }

        Catifier setEnd(boolean end) {
            this.end = end;
            return this;
        }

        String catify(String s){
            if(middle){
                return s.replace(from,to);
            }
            String out = s;
            if(start && s.startsWith(from)){
                out = to + out.substring(from.length());
            }
            if(end && s.endsWith(from)){
                int end_index = (0) + out.length() - from.length();
                out = out.substring(0,end_index) + to;
            }
            return out;
        }
    }
}
