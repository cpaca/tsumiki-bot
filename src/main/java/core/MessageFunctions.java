package core;

import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface MessageFunctions {

    default List<String> SplitString(String str){
        return SplitString(str, 1900);
    }

    default List<String> SplitString(String str, int maxLength){
        return SplitString(str, maxLength, "\n");
    }

    default List<String> SplitString(String str, int maxLength, String... splits){
        ArrayList<String> out = new ArrayList<>();
        if(str.length() < maxLength){
            out.add(str);
            return out;
        }
        if(splits.length == 0){
            throw new IllegalArgumentException();
            // Rather throw an Illegal Argument Exception than deal with this shit.
        }
        else{
            String splitter = splits[0];
            String[] nextRecursion = Arrays.copyOfRange(splits,1,splits.length);
            String[] inputStringSplit = str.split(splitter);
            for(String s : inputStringSplit){
                out.addAll(SplitString(s, maxLength, nextRecursion));
            }
        }
        return out;
    }

    default void SendMessage(TextChannel channel, List<String> messages){
        for(String s : messages){
            channel.sendMessage(s).queue();
        }
    }

    default <T> ArrayList<T> removeDuplicates(ArrayList<T> input){
        ArrayList<T> output = new ArrayList<>();

        for(T t : input){
            if(!output.contains(t)){
                output.add(t);
            }
        }
        return output;
    }
}
