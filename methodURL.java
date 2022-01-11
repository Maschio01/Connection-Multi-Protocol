import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class methodURL {
    String protocol = "", domain = "", content = "";
    URL url;
    InputStream is;
    Scanner urlInput;

    public methodURL(String protocol, String domain){
        this.protocol = protocol;
        this.domain = domain;
    }

    public String connect(){
        try {
            url = new URL(protocol+"://"+domain);
            is = url.openStream();
            urlInput = new Scanner(is);
            while (urlInput.hasNext()) {content += urlInput.nextLine()+"\n";}
            is.close();
        } catch (Exception e) {e.printStackTrace();}
        return content;
    }
}
