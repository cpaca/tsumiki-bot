package ListenerV2;

import commands.AllServers.*;
import core.CommandListener;

public class AllServerListenerV2 extends CommandListener {

    private static AllServerListenerV2 self = new AllServerListenerV2();

    private AllServerListenerV2(){
        //addGuildRestriction("387019628204785664");
        AllGuilds = true;
    }

    public static AllServerListenerV2 getInstance(){
        return self;
    }

    @Override
    public void addCommands() {

        //*
        commands.add(new suckdown());
        commands.add(new suckup());
        commands.add(new time());
        commands.add(new christianserver());
        commands.add(new australianserver());
        commands.add(new staffLeftAndThereAreRuleBreakers());
        commands.add(new netneutrality());
        commands.add(new edit());
        commands.add(new grammar());
        commands.add(new ea());
        commands.add(new roll());
        commands.add(new catify());
        commands.add(new mentionme());
        commands.add(new care());
        commands.add(new id());
        commands.add(new usercount()); // useful cmd
        commands.add(new roasted());
        commands.add(new vote());
        commands.add(new expand());
        commands.add(new trap());
        commands.add(new leavechat());
        commands.add(new stop());
        commands.add(new hammertime());
        commands.add(new nekosdotlife());
        commands.add(new guildcount());
        commands.add(new coingame());
        commands.add(new approaching());
        commands.add(new meow());
        commands.add(new meat());
        commands.add(new degrammar());
        commands.add(new blackboardBold());
        commands.add(new storeData());
        /*/

        //*/


        //PASSIVE, NON-COMMAND
        commands.add(new mentionsOwner());
        commands.add(new ModifyConfigs());
        commands.add(new ThanosSnap());
        commands.add(new selfShutdown());
        commands.add(new MudaeCheating());
    }
    ///// SMALL THING FOR UWSL
    //        try{
    //            if(guild.getId().equals("289920126457741312")){
    //                if(contains(msg," ok ") || msg.equals("ok")){
    //                    message.addReaction(guild.getEmoteById("422468565304606752")).queue();
    //                }
    //                if(contains(msg.toLowerCase(),"egg")){
    //                    message.addReaction("\uD83E\uDD5A").queue();
    //                }
    //            }
    //        } catch (Exception e){
    //
    //        }
}
