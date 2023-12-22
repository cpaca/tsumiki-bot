package commands.AllServers;

import Filehandling.Data;
import Filehandling.Filehandler;
import core.CommandProcessor;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

import java.util.*;

public class storeData extends CommandProcessor {

    public storeData(){
        cmd = "data";
        help = "Do \"/data help\" for proper help.";
        setCategory("Info");
    }

    @Override
    protected CommandDataImpl UpdateCommandData(CommandDataImpl data) {
        SubcommandData optin = new SubcommandData("optin", "Opts in to having data stored");
        SubcommandData optout = new SubcommandData("optout", "Opts out to having data stored");
        SubcommandData wipe = new SubcommandData("wipe", "Wipes all your data. WARNING: THIS CANNOT BE UNDONE");
        SubcommandData help = new SubcommandData("help", "Full information on sub-command data.");
        SubcommandData priv = new SubcommandData("priv", "Full privacy information. Because Discord requires I have a privacy policy. For some reason.");

        SubcommandData down = new SubcommandData("download", "Downloads your data");
        down.addOption(OptionType.INTEGER, "page", "What page of data to look at", false);

        data.addSubcommands(optin, optout, wipe, help, priv, down);

        return super.UpdateCommandData(data);
    }

    @Override
    protected void ProcessSlashCommand(SlashCommandEvent event){
        String subcommand = event.getSubcommandName();
        if(subcommand == null){
            subcommand = "help";
        }

        String out = "";

        Data data = Filehandler.getUserData(event.getUser());
        //System.out.println(data.getData("optin"));

        if(subcommand.equals("optin")){
            data.setData("optin", "true");

            out += "Opting you in to data collection.";
            event.reply(out).queue();
            Filehandler.saveData(data);
        } else if(subcommand.equals("optout")){
            data.setData("optin", "false");

            out += "Opting you out of data collection.\n";
            out += "Note: You still need to do [/data wipe] to wipe all of your data.\n";
            out += "This is becaue you can do opt-out in one command, but wiping data needs to be run twice.\n";
            event.reply(out).queue();
        } else if(subcommand.equals("wipe")){
            String lastCheck = data.getData("data - wipe");
            Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            long currTime = c.getTimeInMillis();
            long lastTime = Long.parseLong(lastCheck);
            long expireTime = lastTime + (15 * 1000);
            if(currTime < expireTime){
                event.reply("Wiping all of your data.").queue();
                data.wipeData();
            }
            else{
                data.setData("data - wipe", currTime);
                event.reply("**Warning: This cannot be revoked.** Use the command again in the next 15 seconds to really wipe your data.").queue();
            }
            Filehandler.saveData(data);
        } else if(subcommand.equals("priv")){
            out += "Privacy policy:\n";
            out += "Data we collect is available to anyone who is able to hack into our servers\n";
            out += "and also available to anyone who hacks into your account and does /data down\n";
            out += "and also available to anyone who bribes the bot dev with money\n";
            out += "and also to whoever looks in the server's files\n";
            out += "Basically, this is a bot made by one guy. Don't trust its data privacy to be that good.\n";
            event.reply(out).queue();
        } else if(subcommand.equals("down")){
            OptionMapping option = event.getOption("page");
            int page = 1;
            if(option != null){
                page = option.getAsInt();
            }

            int minIndex = (page-1)*10;
            int maxIndex = (page)*10;

            Map<String, String> allData = data.getAllData();
            List<String> keys = new ArrayList<>(allData.keySet());
            StringBuilder builder = new StringBuilder();
            keys.sort(String::compareTo);
            for(int i = 0; i < keys.size(); i++){
                if(i >= minIndex && i < maxIndex){
                    String key = keys.get(i);
                    String val = allData.get(key);
                    builder.append(key).append(" | ").append(val);
                    builder.append("\n");
                }
            }
            out += builder.toString();
            if(out.equals("")){
                event.reply("No data on this page to download.").queue();
            } else if(out.length() > 1900){
                event.reply("Hm. Go ask the bot dev <@!205011703891623936> about this. This page exceeds the character limit.").queue();
            } else{
                event.reply(out).queue();
            }
        } else {
            // either Help
            // or just... idk
            String optin = "``/data optin`` - Opts in to having data stored. Needed for things like wordle and coingame.\n";
            String optout = "``/data optout`` - Opts out to having data stored. Can't use coingame if you do this.\n";
            String wipe = "``/data wipe`` - Wipes all of your data. Non recoverable.\n";

            String help = "``/data help`` - sends this message\n";
            String priv = "``/data priv`` - Sends the privacy policy for this bot. Discord doesn't like it if I don't have this.\n";
            String down = "``/data down`` - Downloads all of your data, then sends it in a message. \n";
            event.reply(optin + optout + wipe + help + priv + down).queue();
        }
    }
}
