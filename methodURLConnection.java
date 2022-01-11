import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class methodURLConnection {
    String protocol = "", domain = "", content = "";
    URL url;
    URLConnection urlConn;
    InputStream is;
    Scanner urlInput;

    public methodURLConnection(String protocol, String domain){
        this.protocol = protocol;
        this.domain = domain;
    }

    public String connect(){
        try {
            url = new URL(protocol + "://" + domain);
            urlConn = url.openConnection();
            if (protocol.equals("http") || protocol.equals("https")) {
                HttpURLConnection httpConn = (HttpURLConnection) urlConn;
                httpConn.setRequestMethod("GET");
                urlConn.connect();
                System.out.println("http response code: " + httpConn.getResponseCode());
            } else {
                urlConn.connect();
            }
            is = urlConn.getInputStream();
            urlInput = new Scanner(is);
            while (urlInput.hasNext()) {
                content += urlInput.nextLine()+"\n";
            }
            is.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return content;
    }
}
