package core;

import ListenerV2.*;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import util.STATICS;
import net.dv8tion.jda.api.OnlineStatus;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static final Random rand = new Random();

    private static ShardManager jda = null;
    private static Guild devServer = null;
    private static Member owner = null;

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

        //*
        builder.addEventListeners(KaliumListener.getInstance(),
                AllServerListenerV2.getInstance(),
                AcchiKocchiListenerV2.getInstance(),
                DraconiaListenerV2.getInstance(),
                SCPListener.getInstance(),
                UWSLListener.getInstance(),
                QuestionListener.getInstance());
        /*/
        // developer mode.
        builder.addEventListeners(AllServerListenerV2.getInstance());
        //*/

        try {
            jda = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public static Guild getDevServer() {
        if(devServer == null){
            devServer = jda.getGuildById("387019628204785664");
        }
        return devServer;
    }

    public static Member getOwner() {
        if(owner == null){
            owner = getDevServer().getMemberById("205011703891623936");
        }
        return owner;
    }
}
