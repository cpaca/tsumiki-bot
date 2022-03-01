package core;

import Filehandling.Data;
import Filehandling.Filehandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public abstract class CommandProcessor extends ListenerAdapter implements MessageFunctions{

    protected String cmd = "";
    protected String help = "";
    protected int category = CategoryHandler.getCategoryNum("No Category");
    public final boolean nsfw;
    private static CommandProcessor instance;

    public CommandProcessor(){
        this(false);
    }

    public CommandProcessor(boolean nsfw){
        this.nsfw = nsfw;
    }

    public boolean canUseCommand(MessageReceivedEvent event){
        // scp-914 server
        if(event.getGuild().getId().equals("569363224009637920")){
            // ethernet cable
            if(event.getChannel().getId().equals("639356953272909834")){
                return true;
            }
            return isDeveloper(event);
        }
        return true;
    }

    protected final boolean isDeveloper(MessageReceivedEvent event){
        if(!event.getAuthor().getId().equals("205011703891623936"))
            return false;
        // do not run this command very often, it opens and closes files.
        Data c = Filehandler.getConfig("developer");
        if(c == null)
            return false;
        return c.getData("O5").equals("1");
    }

    // Note: OnMessageReceived shouldn't need any preprocessing.



    // Slash command preprocessing:
    // Check if the command event is for THIS command or for some other command.
    // Note: The docs still say to use "SlashCommandEvent"
    // but it doesn't exist anymore, and checking DV8FromTheWorld/JDA #1971 it was renamed
    // to SlashCommandInteractionEvent
    public void onSlashCommand(SlashCommandInteractionEvent event){

    }

    // By default, do nothing.
    protected void ProcessSlashCommand(SlashCommandInteractionEvent event){

    }

    // By default, do nothing.
    // Let whatever this command is handle doing this.
    protected void MessageReceived(String message, MessageReceivedEvent event){

    }

    public String getCmd(){
        return cmd;
    }
    public int getCategory() {
        return category;
    }
    public boolean isPassive(){
        return help.equals("");
    }
    protected void setCategory(String name) {
        category = CategoryHandler.getCategoryNum(name);
    }
    protected String adjustString(String s, int len, String adjustant){
        // Note from future me: This is just string left pad?
        while(s.length() < len){
            s = adjustant + s;
        }
        return s;
    }

    protected EmbedBuilder buildImage(String desc, String imageURL){
        //Reminder to self: https://gist.github.com/zekroTJA/c8ed671204dafbbdf89c36fc3a1827e1
        EmbedBuilder eb = new EmbedBuilder();
        if(!desc.equals("")) {
            eb.setDescription(desc);
        }
        eb.setImage(imageURL);
        return eb;
    }

    protected EmbedBuilder buildImgur(String desc, String ImgurID){
        return buildImage(desc,"https://imgur.com/" + ImgurID);
    }

    protected String mention(User u){
        return "<@!" + u.getId() + ">";
    }

    protected String mention(Member m){
        return mention(m.getUser());
    }

    // Whether a user is opted into or out of saving data
    // If they aren't opted in *or* out, it opts them out and tells them how to opt in.
    public static boolean isOptedIn(User user, MessageChannel channel){
        Data d = Filehandler.getUserData(user);
        if(d.getData("optin").equalsIgnoreCase("true")){
            return true;
        }
        else if(d.getData("optin").equalsIgnoreCase("false")){
            return false;
        }
        else{
            d.setData("optin","false");
            Filehandler.saveData(d);

            channel.sendMessage("To opt into saving data (e.g. high scores), do ``!storeData true``.\n" +
                    "By default, you have been opted-out of storing this information.").complete();

            return false;
        }
    }
}
