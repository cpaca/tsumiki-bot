package core;

import Filehandling.Data;
import Filehandling.Filehandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

import javax.annotation.Nonnull;

public abstract class CommandProcessor extends ListenerAdapter implements MessageFunctions {

    protected String cmd = "";
    protected String help = "";
    protected String desc = "";
    protected int category = CategoryHandler.getCategoryNum("No Category");
    public final boolean nsfw;
    protected long guild = 0;

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
    // Keep this command in until all functions are updated.
    protected void MessageReceived(String message, MessageReceivedEvent event){

    }

    // Slash command preprocessing:
    // Check if the command event is for THIS command or for some other command.
    // Note: The docs still say to use "SlashCommandEvent"
    // but it doesn't exist anymore, and checking DV8FromTheWorld/JDA #1971 it was renamed
    // to SlashCommandInteractionEvent
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        String name = event.getName();
        if(name.equals(this.getCmd())){
            ProcessSlashCommand(event);
        }
    }

    // By default, do nothing.
    protected void ProcessSlashCommand(SlashCommandInteractionEvent event){

    }

    // Get this command's CommandData
    public CommandDataImpl getCommandData(){
        if(getCmd().equals("")){
            return null;
        }
        if(getDesc().equals("")){
            return null;
        }
        CommandDataImpl out = new CommandDataImpl(getCmd(), getDesc());
        out = UpdateCommandData(out);
        return out;
    }

    // In case the command wants to do something EX add options
    protected CommandDataImpl UpdateCommandData(CommandDataImpl data){
        return data;
    }

    public String getCmd(){
        return cmd;
    }
    public String getHelp(){
        return help;
    }
    public String getDesc(){
        if(desc.equals("")) {
            return getHelp();
        }
        return desc;
    }
    public int getCategory() {
        return category;
    }
    public boolean isPassive(){
        return help.equals("");
    }
    public long getGuild(){
        return guild;
    }
    protected void setCategory(String name) {
        category = CategoryHandler.getCategoryNum(name);
    }
    protected String leftPad(String s, int len, String adjustant){
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
