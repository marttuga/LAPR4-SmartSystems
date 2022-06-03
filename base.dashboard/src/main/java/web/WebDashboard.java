package web;

import eapli.base.warehousemanagement.application.GetAGVController;
import eapli.base.warehousemanagement.domain.AGV;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class WebDashboard {
    static private final String BASE_FOLDER ="base.dashboard/src/main/java/base/dashboard/web/www";
    static private ServerSocket sock;
    //static private final AGVRepository agvRepository = PersistenceContext.repositories().agv();
    //static private final AGVService agvService = new AGVService();

    public static void main(String[] args) throws IOException {
        Socket cliSock;

        if (args.length != 1) {
            System.out.println("Local port number required at the command line.");
            System.exit(1);
        }
        try {
            sock = new ServerSocket(Integer.parseInt(args[0]));
        } catch (IOException ex) {
            System.out.println("Server failed to open local port " + args[0]);
            System.exit(1);
        }
        while (true) {
            cliSock = sock.accept();
            HTTPAjaxRequest req = new HTTPAjaxRequest(cliSock, BASE_FOLDER);
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
