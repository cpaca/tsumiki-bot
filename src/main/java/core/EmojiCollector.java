package core;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;

import java.util.Map;
import java.util.TreeMap;

public class EmojiCollector {

    private static final EmojiCollector self = new EmojiCollector();

    private static final Map<String,String> emojis = new TreeMap<>();

    private EmojiCollector(){

    }

    private static void initalize(){
        char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        String[] letteremojis = "\uD83C\uDDE6 \uD83C\uDDE7 \uD83C\uDDE8 \uD83C\uDDE9 \uD83C\uDDEA \uD83C\uDDEB \uD83C\uDDEC \uD83C\uDDED \uD83C\uDDEE \uD83C\uDDEF \uD83C\uDDF0 \uD83C\uDDF1 \uD83C\uDDF2 \uD83C\uDDF3 \uD83C\uDDF4 \uD83C\uDDF5 \uD83C\uDDF6 \uD83C\uDDF7 \uD83C\uDDF8 \uD83C\uDDF9 \uD83C\uDDFA \uD83C\uDDFB \uD83C\uDDFC \uD83C\uDDFD \uD83C\uDDFE \uD83C\uDDFF".split(" ");
        for (int i = 0; i < letters.length; i++) {
            emojis.put(String.valueOf(letters[i]),letteremojis[i]);
        }


        // **ACCHI KOCCHI SERVER RESTRICTED**
        emojis.put("tsusatisfied","429362977473429514");
    }

    public static String getEmoji(String emojiname){
        if(emojis.size() == 0){
            initalize();
        }
        return emojis.get(emojiname);
    }

    public static Emote getEmote(String emotename, Guild guild){
        return guild.getEmoteById(getEmoji(emotename));
    }


}
