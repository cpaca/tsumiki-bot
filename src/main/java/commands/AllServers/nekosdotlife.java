package commands.AllServers;

import core.CommandProcessor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class nekosdotlife extends CommandProcessor {

    //Remember: https://github.com/Nekos-life/nekos.py/blob/master/nekos/nekos.py
    private Map<String,Boolean> cmds;

    public nekosdotlife(){
        cmd = "nekosdotlife";
        help = "Run /nekosdotlife help for help on this one. This is a VERY BIG one.";
        setCategory("images");

        cmds = getCmds();
    }

    @Override
    protected CommandDataImpl UpdateCommandData(CommandDataImpl data) {
        data.addOption(OptionType.STRING,"text", "parameter");

        return super.UpdateCommandData(data);
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event) {
        OptionMapping option = event.getOption("text");
        String subcomm;
        if(option == null){
            subcomm = "help";
        }
        else{
            subcomm = option.getAsString();
        }

        if(subcomm.equals("help")){
            String out = "The nekosdotlife command provides a lot of random images. " +
                    "To get the nekos, do \"nekosdotlife neko\". " +
                    "To get kemonomimi, do \"nekosdotlife kemonomimi\".\n" +
                    "If you want a list of valid options, do \"/nekosdotlife list\".\n" +
                    "Note: Some commands are marked NSFW, but this is VERY subjective.\n" +
                    "I've been told some of the lewd ones are tame, and some of the tame ones are lewd. So, whatever.\n";
            event.reply(out).queue();
        }
        else if(subcomm.equals("list")){
            OptionMapping commandOption = event.getOption("NSFW");
            if(commandOption == null){
                event.reply("NPE error, NDL command, tell the dev. Or just try again.").queue();
                return;
            }
            boolean isNSFW = commandOption.getAsBoolean();

            if(isNSFW && !(event.getTextChannel().isNSFW()) ){
                event.reply("Just for safety, I won't list the NSFW commands in a SFW channel.").queue();
                return;
            }

            ArrayList<String> subcomms = new ArrayList<>();
            for(Map.Entry<String, Boolean> entry : cmds.entrySet()){
                if(entry.getValue() == isNSFW){
                    subcomms.add(entry.getKey());
                }
            }

            StringBuilder out = new StringBuilder("List of available commands:\n| ");
            for(String s:subcomms){
                out.append(s += " | ");
            }
            event.reply(out.toString()).queue();
        }
        else{
            // "Custom" subcommand.
            boolean NSFW = cmds.get(subcomm);

            if(NSFW && !event.getTextChannel().isNSFW()){
                event.reply("Cannot use NSFW subcommands in a SFW channel.").queue();
                return;
            }

            String imageURL = getImage(subcomm);
            if(imageURL.toUpperCase().contains("<ERROR>")){
                event.reply("An unknown error has occured. I have contacted the bot owner. Try again?").queue();
                System.out.println("---------------------------");
                System.out.println("NekosDotLife command error!");
                System.out.println(imageURL);
                System.out.println("NekosDotLife command error!");
                System.out.println("---------------------------");
                return;
            }
            String desc = "URL: " + imageURL + "\n";
            EmbedBuilder builder = buildImage(desc,imageURL);
            event.replyEmbeds(builder.build()).queue();
        }

    }

    private Map<String,Boolean> getCmds(){
        Map<String,Boolean> outputs = new HashMap<>();
        outputs.put("neko",false);
        outputs.put("hug",false);

        outputs.put("holo",true);
        outputs.put("hololewd",true);
        outputs.put("holoero",true);
        return outputs;
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
