package util;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.exceptions.PermissionException;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;

public class BonusFunctions {

    private JDA jda;

    public BonusFunctions(JDA jda_){
        jda = jda_;
    }
    public static String replace(String input, String replaceto, String replacefrom){
        return String.join(replaceto, input.split(replacefrom));
    }

    public static String remove(String input, String remove){
        return replace(input,"",remove);
    }

    public static boolean contains(String input, String target){
        return (!(remove(input,target).equals(input)));
    }

    public String multireplace(String input, String replacefrom, String... replaceto){
        String out = "";
        String[] string = input.split(replacefrom);
        int i = 0;
        if(replaceto.length == 0){
            System.out.println("Returned null.");
            return "";
        }
        if(input.split(replacefrom).length == 1){
            return input;
        }
        for (int j = 0; j < string.length; j++) {
            out += string[j];
            if(j < string.length - 1){
                out += replaceto[i];
                i++;
                if(i >= replaceto.length){
                    i = 0;
                }
            }
        }
        return out;
    }

    public static String reverse(String input){
        String[] item = input.split("");
        ArrayUtils.reverse(item);
        return String.join("",item);
    }

    public Guild getGuild(String id){
        return jda.getGuildById(id);
    }

    public Member getMem(String id, Guild guild){
        return guild.getMemberById(id);
    }

    public Member getMem(String memberID, String guildID){
        return getGuild(guildID).getMemberById(memberID);
    }

    public Account getAcc(String memberID, String guildID){
        return new Account(getMem(memberID,guildID).getUser());
    }

    public Account getAcc(Member mem){
        return new Account(mem.getUser());
    }

    /////
    /* VERY IMPORTANT THING
    DO NOT REMOVE
    EVER
    OR IT WILL BREAK
    LITERALLY FUCKING EVERYTHING
     */
    public static void sendMsg(MessageChannel channel, String send, String filename){
        try{
            channel.sendMessage(send).queue();
            channel.sendFile(new File(filename)).queue();
        }catch(InsufficientPermissionException e){return;}
    }
    public static void sendMsg(MessageChannel channel, String send){
        try{
            channel.sendMessage(send).queue();
        }catch(InsufficientPermissionException e){return;}
    }

    public static void sendFile(MessageChannel channel, String file){
        try{
            channel.sendFile(new File(file)).queue();
        }catch(InsufficientPermissionException e){return;}
    }

    public static void sendFile(MessageChannel channel, File file){
        try{
            channel.sendFile(file).queue();
        }catch(InsufficientPermissionException e){return;}
    }
    public static void sendFile(MessageChannel channel, String file, String msg_){
        try{
            channel.sendMessage(msg_).queue();
            channel.sendFile(new File(file)).queue();
        }catch(InsufficientPermissionException e){return;}
    }
    public static void sendPrivateMessage(User user_, String content) {
        // openPrivateChannel provides a RestAction<PrivateChannel>
        // which means it supplies you with the resulting channel
        if(user_ == null)
            return;
        // catch nullpointerexception
        try{
            user_.openPrivateChannel().queue((channel) ->
            {
                channel.sendMessage(content).queue();
            });
        }catch(PermissionException e){
            return;
        }
    }
    public static void sendPrivateFile(User user_, String filename){
        if (user_ == null)
            return;
        // catch nullpointerexception
        try{
            user_.openPrivateChannel().queue((channel) ->
            {
                channel.sendFile(new File(filename)).queue();
            });
            sendPrivateMessage(user_,filename);
        }catch(PermissionException e){
            return;
        }
    }



}
