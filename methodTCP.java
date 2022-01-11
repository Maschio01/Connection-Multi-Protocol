import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class methodTCP{
    String protocol = "", domain = "", content = "";
    Socket socket;
    PrintWriter urlOutput;
    Scanner urlInput;

    public methodTCP(String protocol, String domain){
        this.protocol = protocol;
        this.domain = domain;
    }

    public String connect(){
        try {
            if (!protocol.equals("http")) {
                System.out.println("Unhandled protocol");
                return "error";
            }
            InetAddress addr = InetAddress.getByName(domain);
            socket = new Socket(addr, 80);
            urlOutput = new PrintWriter(socket.getOutputStream(), false);
            urlInput = new Scanner(socket.getInputStream());

            urlOutput.println("GET / HTTP/1.1");
            urlOutput.println("Host: " + domain);
            urlOutput.println("");
            urlOutput.flush();

            Thread.sleep(500);
            while (urlInput.hasNext()) {
                String input = urlInput.nextLine();
                System.out.println(input);
                content += input+"\n";
            }
            socket.close();
        } catch (Exception e) {System.out.println("Unable to create the connection with: " + protocol + "://" + domain);}
        return content;
    }
}