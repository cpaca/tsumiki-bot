package util;

public class STATICS {

    // This file is in .gitignore ONLY because the tokens are in here.
    // I'll know if this has failed because
    public static final boolean is_dev = false;

    private static String TOKEN = "MzkwMzQ3NjQwNDM4NTIxODU2.XtbATQ.OJgMa3INRq-VAEBRsr8GEK38Woo";
    private static String DEV_TOKEN = "NzI1NTg0OTk0NDQ5NDkwMDQy.XvQ33w.ZSKxQQ7fOLWZZB81uClgR81pDk8";
    public static String IMGPATH;
    // Staff Discord names/discriminators
    // How to build:
    // [name] = new User([name, string],[discriminator, int])
    public static Account cpaca = new Account("Tsumiki","0001","205011703891623936");
    public static Account altCpaca = new Account("oneupp","1757","288105216413532161");
    public Account Muted = new Account("Bumblebirbies","1315","202112030793007104");
    public static Account Bot = new Account("game-announcement-bot","9946","390347640438521856");

    public static String GET_TOKEN(){
        if(is_dev){
            return DEV_TOKEN;
        }
        return TOKEN;
    }

}
