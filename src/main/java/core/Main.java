package core;

import commands.AcchiKocchi.getroleinfo;
import commands.AcchiKocchi.role;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import util.STATICS;
import net.dv8tion.jda.api.OnlineStatus;

import javax.security.auth.login.LoginException;
import java.util.*;

public class Main {

    public static final Random rand = new Random();

    private static ShardManager manager = null;
    private static Guild devServer = null;
    private static Member owner = null;

    private static ArrayList<CommandProcessor> commands = new ArrayList<>();

    public static void main(String[] args){

        ////////////////
        List<GatewayIntent> intents = new ArrayList<>();
        intents.add(GatewayIntent.DIRECT_MESSAGES);
        intents.add(GatewayIntent.GUILD_MESSAGES);
        intents.add(GatewayIntent.GUILD_VOICE_STATES);
        intents.add(GatewayIntent.GUILD_EMOJIS);

        DefaultShardManagerBuilder builder;
        builder = DefaultShardManagerBuilder.createDefault(STATICS.GET_TOKEN(), intents);

        builder.setToken(STATICS.GET_TOKEN());
        builder.setAutoReconnect(true);
        builder.setStatus(OnlineStatus.ONLINE);

        AddCommands();

        commands.forEach(builder::addEventListeners);

        try {
            manager = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

        // Note: CommandListUpdateAction will be shortened to CLUA

        // Note: There are faster methods of doing this
        // but keep in mind that something manager is NOT ready yet.
        // So it's better to prepare this while we wait for it to ready up.
        Map<Long, List<CommandData>> commandsToAdd = new HashMap<>();

        for(CommandProcessor c : commands){
            long guildID = c.getGuild();

            if(commandsToAdd.containsKey(guildID)){
                // note that I think get() returns a pointer to the data
                // as opposed to returning a copy of the data
                // so this should affect the original map too
                // as we're not overriding this variable, just calling a method within it
                commandsToAdd.get(guildID).add(c.getCommandData());
            }
            else{
                List<CommandData> list = new ArrayList<>();
                list.add(c.getCommandData());
                commandsToAdd.put(guildID, list);
            }
        }

        int index = 0;
        while(!commandsToAdd.isEmpty()){
            Set<Long> keys = commandsToAdd.keySet();
            index = index % keys.size();
            long guildID = (long) keys.toArray()[index];
            List<CommandData> toAdd = commandsToAdd.get(guildID);

            if(guildID == 0){
                // global
                JDA jda = manager.getShards().get(0);
                CommandListUpdateAction CLUA = jda.updateCommands();
                CLUA.addCommands(toAdd).queue();
                commandsToAdd.remove(guildID);

                System.out.println("Added global commands");
            }
            else{
                Guild guild = manager.getGuildById(guildID);
                if(guild == null){
                    // guild doesn't exist, need to try again
                    continue;
                }
                CommandListUpdateAction CLUA = guild.updateCommands();
                CLUA.addCommands(toAdd).queue();

                System.out.println("Added commands for guild: " + guild.getName());
            }

            commandsToAdd.remove(guildID);
            index++;
        }

    }

    public static Guild getDevServer() {
        if(devServer == null){
            devServer = manager.getGuildById("387019628204785664");
        }
        return devServer;
    }

    public static Member getOwner() {
        if(owner == null){
            owner = getDevServer().getMemberById("205011703891623936");
        }
        return owner;
    }


    // NOTE: I SUGGEST COLLAPSING THE FOLLOWING FUNCTION
    // It is... long.
    private static void AddCommands(){
        commands.add(new role());
        commands.add(new getroleinfo());
    }
}
