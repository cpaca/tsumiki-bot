package commands.SCP;

import Filehandling.Filehandler;
import core.CommandProcessor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class download914test extends CommandProcessor {

    public static final String requestURL = "http://www.scp-wiki.net/ajax-module-connector.php";
    public static final String baseURL = "http://www.scp-wiki.net/fragment:experiment-log-914-";

    public download914test(){
        cmd = "download914test";
        help = "Downloads SCP-914 tests.";
        setCategory("Developer");
    }

    /*/
    // Example code.
    public static void main(String[] args){

        try {
            getData("http://www.scp-wiki.net/fragment:experiment-log-914-001");
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    //*/

    @Override
    protected void MessageReceived(String message, MessageReceivedEvent event) {
        boolean valid = false;
        if(event.getAuthor().getId().equals("205011703891623936"))
            valid = true;
        if(!valid)
            return;
        int num;
        try {
            num = Integer.parseInt(message);
        } catch (NumberFormatException nfe){
            event.getChannel().sendMessage("\"" + message + "\" is an invalid number!").queue();
            return;
        }

        if(num > 99){
            event.getChannel().sendMessage("Too large! Unless you're in the future, then you need to change my code...").queue();
            return;
        }

        StringBuilder HundredsPlace = new StringBuilder(Integer.toString(num));
        while(HundredsPlace.length() < 3){
            HundredsPlace.insert(0, "0");
        }
        Scanner scan;
        try {
            scan = new Scanner(toBufferedReader(getData(baseURL + HundredsPlace)));
        } catch (IOException ioe){
            event.getChannel().sendMessage("Unknown IO Exception!").queue();
            ioe.printStackTrace();
            return;
        }
        for(int i = 0; i < 4; i++){
            if(scan.hasNextLine())
                scan.nextLine();
        }

        int OnesPlace = 0;
        PrintStream stream = getStream(HundredsPlace + "00");
        boolean printedToStream = false;
        while(scan.hasNextLine()){
            String line = scan.nextLine();

            if(isFileBreak(line)) {
                if(printedToStream) {
                    stream.close();
                    OnesPlace++;
                    if (OnesPlace == 100) {
                        break;
                    }
                    String fileName = Integer.toString(OnesPlace);
                    if (fileName.length() < 2) {
                        fileName = "0" + fileName;
                    }
                    fileName = HundredsPlace + fileName;
                    stream = getStream(fileName);
                    printedToStream = false;
                }
            }
            else{
                stream.println(line);
                if(line.length() > 1) {
                    printedToStream = true;
                }
            }
        }
        scan.close();
        stream.close();
        event.getChannel().sendMessage("Successful download!").queue();
    }

    private static boolean isFileBreak(String line){
        char[] arr = line.toCharArray();
        int dashes = 0;
        for (char c : arr) {
            if (c == '-') {
                dashes++;
            } else {
                return false;
            }
        }
        return dashes > 3;
    }

    private static PrintStream getStream(String filenum){
        File out = Filehandler.getFile("914tests\\" + filenum + ".txt");
        PrintStream stream = null;
        try {
            stream = new PrintStream(out);
        } catch (FileNotFoundException fnfe){
            System.out.println("download914test error! Printstream-file-not-found?!");
            fnfe.printStackTrace();
        }
        return stream;
    }

    private static String getData(String siteURL) throws IOException {
        String token = getToken(siteURL);
        String pageID = getPageID(siteURL);

        HttpPost post = new HttpPost(requestURL);
        post.addHeader("Cookie","wikidot_token7=" + token);
        post.addHeader("Host", "www.scp-wiki.net");
        post.addHeader("Origin", "http://scp-wiki.net");
        post.addHeader("Referer", siteURL);
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("page_id", pageID));
        pairs.add(new BasicNameValuePair("wikidot_token7",token));
        pairs.add(new BasicNameValuePair("moduleName", "viewsource/ViewSourceModule"));

        try{
            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(pairs);
            post.setEntity(encodedFormEntity);
        } catch (IOException ioe){
            ioe.printStackTrace();
            return null;
        }

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpResponse response = httpclient.execute(post);
        BufferedReader reader = toBufferedReader(response.getEntity().getContent());

        StringBuilder out = new StringBuilder();
        String data;
        while((data = reader.readLine()) != null) {
            data = fixHTML(data);
            BufferedReader line = toBufferedReader(data);
            String input;
            while ((input = line.readLine()) != null) {
                input = fixLine(input + "\n");
                out.append(input);
            }
        }
        httpclient.close();
        return out.toString();
    }

    private static BufferedReader toBufferedReader(Reader r){
        return new BufferedReader(r);
    }

    private static BufferedReader toBufferedReader(String str){
        return toBufferedReader(new StringReader(str));
    }

    private static BufferedReader toBufferedReader(InputStream stream){
        return toBufferedReader(new InputStreamReader(stream));
    }

    private static String fixHTML(String s){
        s = s.replace("\\/","/");
        s = s.replace("<br />\\n","\n");
        s = s.replace("\\n", "\n");
        s = s.replace("\n[[/collapsible]]\n","\n");
        s = s.replace("\n[[/collapsible]] \n","\n");
        s = s.replace("&quot;","\"");
        s = s.replace("&nbsp;"," ");
        s = s.replace("&amp;","&");

        StringBuilder out = new StringBuilder();

        boolean slash = false;
        StringBuilder hexchars = new StringBuilder();
        int charsToSearchFor = -1;
        for(char c: s.toCharArray()){
            if(charsToSearchFor > 0){
                hexchars.append(c);
                charsToSearchFor--;
                continue;
            }
            else if(charsToSearchFor == 0){
                char AsciiChar = (char) Integer.parseInt(hexchars.toString(), 16);
                out.append(AsciiChar);
                hexchars = new StringBuilder();

                charsToSearchFor--;
            }

            if(c == '\\'){
                if(slash){
                    out.append("\\\\");
                    slash = false;
                }
                else{
                    slash = true;
                }
                continue;
            }

            if(slash){
                if(c == 'u'){
                    slash = false;
                    charsToSearchFor = 4;
                    continue;
                }
                else{
                    out.append("\\");
                    slash = false;
                }
            }
            else{
                out.append(c);
            }
        }

        return out.toString();
    }

    private static String fixLine(String line){
        if(line.startsWith("[[collapsible show=\"") && line.endsWith("]]\n")){
            return "";
        }
        return line;
    }

    private static String getToken(String siteURL) throws IOException {
        HttpPost site = new HttpPost(siteURL);
        CloseableHttpClient siteClient = HttpClients.createDefault();
        HttpResponse siteResponse = siteClient.execute(site);
        Header h = siteResponse.getFirstHeader("Set-Cookie");
        for (HeaderElement element : h.getElements()) {
            if(element.getName().equals("wikidot_token7")){
                return element.getValue();
            }
        }
        return null;
    }

    private static String getPageID(String URL) throws IOException {
        String website = Jsoup.connect(URL).get().toString();
        final String toSearch = "WIKIREQUEST.info.pageId = ";
        int indexID = website.indexOf("WIKIREQUEST.info.pageId = ");
        if(indexID == -1){
            throw new IOException("Could not find Page ID!");
        }
        indexID += toSearch.length();
        StringBuilder out = new StringBuilder();
        while(true){
            String letter = website.substring(indexID, indexID + 1);

            // good thing they have a semicolon right after the number.
            if(letter.equals(";")){
                return out.toString();
            }
            out.append(letter);

            indexID++;
        }
    }
}
