package eapli.base.app.backoffice.console.presentation.UI;

import eapli.application.RequestAgvController;
import eapli.protocol.client.AGVDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.protocol.client.FailedRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class GetAGVInfoUI extends AbstractUI {

    private static final Logger LOGGER = LogManager.getLogger(GetAGVInfoUI.class);

    private final RequestAgvController theController = new RequestAgvController();

    @Override
    public boolean doShow() {
        final String id = selectID();
        showAgvInfo(id);
        return false;
    }

    @Override
    public String headline() {
        return null;
    }

    private String selectID() {
        String id;
        id = Console.readLine("Please enter an AGV ID:\n");
        return id;
    }

    private AGVDTO showAgvInfo(final String id){
        AGVDTO agvdto;
        try {
            agvdto = theController.getAgvs(id);
            return agvdto;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FailedRequestException e) {
            throw new RuntimeException(e);
        }
    }
}
