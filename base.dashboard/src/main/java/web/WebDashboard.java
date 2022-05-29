package web;

import eapli.base.Application;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.application.AGVService;
import eapli.base.warehousemanagement.application.GetAGVController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.repositories.AGVRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class WebDashboard {
    static private final String BASE_FOLDER ="base.app.web.dashboard/src/main/java/base/dashboard/web/www";
    static private ServerSocket sock;
    //static private final AGVRepository agvRepository = PersistenceContext.repositories().agv();
    //static private final AGVService agvService = new AGVService();

    public static void main(String[] args) throws IOException {
        Socket cliSock;
        //String localPort = "32509";

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
            //incAccessesCounter();
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

/*    private static AGV getAGV(List<AGV> agv, Square sq){
        for(int i = 0 ; i < agv.size(); i++){
            if(agv.get(i).getCurrPosition().equals(sq)){
                return agv.get(i);
            }
        }
        return null;
    }*/
}
