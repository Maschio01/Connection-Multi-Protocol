import java.net.URL;
import java.util.Scanner;

public class Main {
    public static Scanner inputTerm = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        boolean valid = false;
        String protocol = "", domain = "";

        do{
            try {
                System.out.println("Enter the protocol: ");
                protocol = inputTerm.nextLine().toLowerCase();
                System.out.println("Enter a domain (without protocol): ");
                domain = inputTerm.nextLine();
                URL check = new URL(protocol+"://"+domain);
                System.out.println("URL that you wrote -> "+check);
                valid = true;
            } catch (Exception e) {System.out.println("Wrong or invalid protocol/domain");}
        }while(!valid);

        methodURL mURL = new methodURL(protocol, domain);
        methodURLConnection mURLConnection = new methodURLConnection(protocol, domain);
        methodTCP mTCP = new methodTCP(protocol, domain);
        String choose = "";
        String domainCont = "";
        System.out.println("Domain connections methods: \n 1 -> URL class \n 2 -> URLConnection class \n 3 -> TCP connection (available for http domain only)");

        do {
            valid = true;
            System.out.println("Enter the choose of the methods you want to use [1-3]: ");
            choose = inputTerm.nextLine();
            switch (choose) {
                case "1":
                domainCont = mURL.connect();
                break;
                case "2":
                domainCont = mURLConnection.connect();
                break;
                case "3":
                domainCont = mTCP.connect();
                break;
                default:
                System.out.println("wrong input of choose... Rewrite your choose from 1 to 3");
                valid = false;
                break;
            }
        }while(!valid);
        System.out.println("Domain content: \n"+domainCont);
    }
}