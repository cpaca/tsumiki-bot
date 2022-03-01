package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.TreeMap;

public class nekosdotlife extends CommandProcessor {

    //Remember: https://github.com/Nekos-life/nekos.py/blob/master/nekos/nekos.py
    private Map<String,Boolean> cmds;

    public nekosdotlife(){
        cmd = "nekosdotlife";
        help = "Run without parameters for help on how this works. This one is a **big one.**";
        setCategory("images");

        cmds = getCmds();
    }

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        if(message.length() == 0){
            help(event);
            return;
        }
        String type;
        if(message.contains(" ")) {
            type = message.substring(message.indexOf(" "));
            message = message.substring(message.indexOf(" ") + 1);
        }
        else{
            type = message;
            message = "";
        }
        Boolean nsfw = cmds.get(type);
        if(nsfw == null){
            event.getChannel().sendMessage("Invalid type!").queue();
            return;
        }
        if(nsfw){
            //ToS reasons.
            event.getChannel().sendMessage("NSFW commands are off, after I caught wind of TOS problems!").queue();
            return;
        }
        if(!event.getTextChannel().isNSFW()){
            if(nsfw){
                event.getChannel().sendMessage("Cannot use NSFW commands in SFW channel!").queue();
                return;
            }
        }
        String imageURL = getImage(type);
        if(imageURL.contains("<ERROR>")){
            event.getChannel().sendMessage("An unknown error has occured. I have contacted the bot owner.").queue();
            System.out.println("---------------------------");
            System.out.println("NekosDotLife command error!");
            System.out.println(imageURL);
            System.out.println("NekosDotLife command error!");
            System.out.println("---------------------------");
            return;
        }
        String desc = "URL: " + imageURL + "\n";
        if(!nsfw) {
            desc += "If the image is an NSFW one, please go contact the https://nekos.life owners that the link above is NSFW, despite being in a SFW section.";
        }
        EmbedBuilder builder = buildImage(desc,imageURL);
        event.getChannel().sendMessage(builder.build()).queue();
    }

    private Map<String,Boolean> getCmds(){
        Map<String,Boolean> outputs = new TreeMap<>();
        outputs.put("neko",false);
        outputs.put("hug",false);

        outputs.put("holo",true);
        outputs.put("hololewd",true);
        outputs.put("holoero",true);
        return outputs;
    }

    private void help(MessageReceivedEvent event){
        String out = "The nekosdotlife command provides a lot of random images. " +
                "To get the nekos, do \"nekosdotlife neko\". " +
                "To get kemonomimi, do \"nekosdotlife kemonomimi\".\n" +
                "Warning, some are not immediately obvious. List of available parameters: \n" +
                "(NSFW ones will not appear unless you are in an NSFW channel)\n | ";
        boolean nsfw = event.getTextChannel().isNSFW();
        for ( String cmd : cmds.keySet() ) {
            if(cmds.get(cmd)) {
                if (!nsfw)
                    continue;
            }
            if(out.length() > 1000){
                event.getChannel().sendMessage(out).queue();
                out = "**Extended nekosdotlife parameters**: | ";
            }
            out += cmd + " | ";
        }
        event.getChannel().sendMessage(out).queue();
    }

    protected static String getImage(String url_){
        try{
            URL url = new URL("https://nekos.life/api/v2/img/" + url_);
            //the string attachment is just some transforming stuff

            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder builder = new StringBuilder();
            String line;

            // read each line and write to out
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            //Now just parsing from Data => ImageURL
            String out = builder.toString();
            out = out.substring(out.indexOf("https"),out.length() - 2);
            return out;

        }catch (Exception e){
            return "<ERROR> " + e.toString();
        }
    }

}
