package web;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HTTPAjaxRequest extends Thread{

    String baseFolder;
    Socket sock;
    DataInputStream inS;
    DataOutputStream outS;


    public HTTPAjaxRequest(Socket cliSock, String baseFolder) {

    }

    public void run() {
        try {
            outS = new DataOutputStream(sock.getOutputStream());
            inS = new DataInputStream(sock.getInputStream());
        } catch (IOException ex) {
            System.out.println("Thread error on data streams creation");
        }

        try {
            HTTPmessage request = new HTTPmessage(inS);
            HTTPmessage response = new HTTPmessage();

            if (request.getMethod().equals("GET")) {
                if (request.getURI().equals("/agvStatus")) {
                    response.setContentFromString(
                            WebDashboard.getAGVStatus(), "text/html");
                    response.setResponseStatus("200 Ok");
                } else {
                    String fullname = baseFolder + "/";
                    if (request.getURI().equals("/")) fullname = fullname + "index.html";
                    else fullname = fullname + request.getURI();
                    if (response.setContentFromFile(fullname)) {
                        response.setResponseStatus("200 Ok");
                    } else {
                        response.setContentFromString(
                                "<html><body><h1>404 File not found</h1></body></html>",
                                "text/html");
                        response.setResponseStatus("404 Not Found");
                    }
                }
                response.send(outS);
            } else { // NOT GET
                response.setContentFromString(
                        "<html><body><h1>ERROR: 405 Method Not Allowed</h1></body></html>",
                        "text/html");
                response.setResponseStatus("405 Method Not Allowed");
                response.send(outS);
            }
        } catch (IOException ex) {
            System.out.println("Thread error when reading request");
        }
        try {
            sock.close();
        } catch (IOException ex) {
            System.out.println("CLOSE IOException");
        }
    }
}
