package core;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public abstract class CommandListener extends ListenerAdapter implements MessageFunctions {

    public static final String prefix = "!";

    private static List<CommandListener> listeners = new ArrayList<>();

    private List<String> GuildRestrictions = new ArrayList<>();
    protected boolean AllGuilds = false;

    protected List<Command> commands = new ArrayList<>();

    protected CommandListener(){
        listeners.add(this);
        addCommands();
    }

    public abstract void addCommands();

    protected void addGuildRestriction(String s) {
        GuildRestrictions.add(s);
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if (!AllGuilds) {
            if(!event.isFromGuild()){
                return;
            }
            boolean GuildOK = false;
            for (String id : GuildRestrictions) {
                if (event.getGuild().getId().equals(id)) {
                    GuildOK = true;
                    break;
                }
            }
            if (!GuildOK) {
                return;
            }
        }

        String message = event.getMessage().getContentRaw();
        for(Command c:commands) {
            if (c.getCmd().equals("")) {
                c.MessageReceived(message, event);
            }
        }
        boolean nsfw;
        ChannelType type = event.getChannelType();
        if(type.equals(ChannelType.TEXT)){
            nsfw = event.getTextChannel().isNSFW();
        }
        else if(type.equals(ChannelType.PRIVATE)){
            nsfw = false; // private channels can't be NSFW'd
        }
        else{
            nsfw = false;
        }
        if (message.startsWith("!")) {
            message = message.substring(1);
            String command = message.toLowerCase();
            if (command.contains(" ")) {
                command = command.substring(0, command.indexOf(" "));
                message = message.substring(message.indexOf(" ") + 1);
            } else {
                message = "";
            }
            for (Command c : commands) {
                if(c.nsfw && !nsfw){
                    continue;
                }
                if (c.getCmd().equalsIgnoreCase(command)) {
                    c.onMessage(message,event);
                }
                if (c.getCmd().equals("")){
                    c.onMessage(message,event);
                }
            }

            if(command.equals("help")){
                help(event, message);
            }
        }
    }

    private void help(MessageReceivedEvent event, String message){
        TextChannel channel = event.getTextChannel();
        // trim the edges of the message
        while(message.length() != 0 && message.charAt(0) == ' '){
            message = message.substring(1);
        }
        while(message.length() != 0 && message.charAt(message.length() - 1) == ' '){
            message = message.substring(0, message.length() - 1);
        }

        boolean getCategories;
        if(message.length() != 0) {
            if (!CategoryHandler.IsCategory(message)) {
                channel.sendMessage("That is not a category! (This message may be sent multiple times)").queue();
                return;
            }
            // User has specified a category, thus they do not need to be told the list of categories.
            getCategories = false;
        }
        else{
            // User has not specified a category, thus they need to be told the list of categories.
            getCategories = true;
        }

        if(getCategories){

            ArrayList<Integer> categoryNums = new ArrayList<>();

            for(Command c : commands){
                if(c.isPassive()) {
                    continue;
                }
                categoryNums.add(c.getCategory());
            }

            categoryNums = removeDuplicates(categoryNums);

            // User needs to know the list of command categories
            StringBuilder out = new StringBuilder();
            out.append("**To get a command, use \"!help [category]\".**\n");
            out.append("**So, to get information on the \"images\" category, use **\n");
            out.append("**\"!help images\" (without quotation marks)**\n\n\n");
            out.append("**__Command categories:__**\n");
            for(int i : categoryNums){
                String catName = CategoryHandler.getCategoryName(i);
                out.append(catName).append("\n");
            }
            List<String> toSend = SplitString(out.toString());
            SendMessage(event.getTextChannel(), toSend);
            
        }
        else{
            StringBuilder text = new StringBuilder(".\n");
            int category = CategoryHandler.getCategoryNum(message);
            for(Command c: commands){
                String help = c.getHelp();
                if(help.length() == 0){
                    continue;
                }
                if(c.category != category){
                    continue;
                }
                if(text.length() + help.length() >= 1900){
                    channel.sendMessage (text).queue();
                    text = new StringBuilder(".\n");
                }
                text.append(c.getHelp());
                text.append("\n");
            }
            if(!text.toString().equalsIgnoreCase(".\n")){
                channel.sendMessage(text).queue();
            }
        }
    }

}