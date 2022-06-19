package web;

import eapli.base.warehousemanagement.application.GetAGVController;
import eapli.base.warehousemanagement.domain.AGV;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.util.*;

/*
    Get the port number to use as first argument in the
    command line.
    Documents directory is sub-folder base.dashboard/src/main/java/base/dashboard/web/www
	Reply to GET requests for some files types:
	(HTML,PDF,GIF,PNG,JavaSript).
	Web services: GET /agvStatus

	Adaptation of WebDashboard to use SSL/TLS,
	thus it's a HTTPS server and not a HTTP server.
	Used "make cert" to create the server's public key
	certificate and private key.

 */

public class WebDashboard {
    static private final String BASE_FOLDER ="base.dashboard/src/main/java/base/dashboard/web/www";
    static private SSLServerSocket sock;

    public static void main(String[] args) throws Exception {
        SSLSocket cliSock;

        if (args.length != 1) {
            System.out.println("Local port number required at the command line.");
            System.exit(1);
        }

        System.setProperty("javax.net.ssl.keyStore", "server.jks");     //Define a KeyStore
        System.setProperty("javax.net.ssl.keyStorePassword", "forgotten");  //Define a Password da KeyStore

        try {
            SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            sock = (SSLServerSocket) sslF.createServerSocket(Integer.parseInt(args[0]));
        }

        catch(IOException ex) {
            System.out.println("Server failed to open local port " + args[0]);
            System.exit(1);
        }

        while (true) {
            cliSock= (SSLSocket) sock.accept();
            HTTPAjaxRequest req = new HTTPAjaxRequest(cliSock, BASE_FOLDER);
            //getAGVStatus();
            req.start();
        }
    }


    public static synchronized String getAGVStatus() throws IOException {


        try {

            String textHtml = "<hr>";
            textHtml = textHtml + "<hr><p>Número de AGV's: " + getAGVfromDB().size() + " </p><hr>";

            return textHtml;
        }catch(ArrayIndexOutOfBoundsException ex){
            return "<hr><ul></ul><hr><p>Waiting...</p></hr>";
        }


    }


    /*public static List<AGV> findAllAGV() {
            return (agvRepository.findAllAGV());
    }*/

    /*public void printAGVList(List<AGV> agvList) {
        agvService.printAGVsList(agvList);
    }*/


    private static List<AGV> getAGVfromDB(){
        GetAGVController ctrl = new GetAGVController();
        Iterator<AGV> AGVit = ctrl.findAll().iterator();
        List<AGV> AGVList = new ArrayList<>();
        while(AGVit.hasNext()){
            AGVList.add(AGVit.next());
        }
        return AGVList;
    }
}

