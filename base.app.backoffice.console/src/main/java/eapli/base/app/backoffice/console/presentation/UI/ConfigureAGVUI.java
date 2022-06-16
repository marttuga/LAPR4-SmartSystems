package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.warehousemanagement.application.ConfigureAGVController;
import eapli.base.warehousemanagement.domain.*;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

public class ConfigureAGVUI extends AbstractUI {

    private final ConfigureAGVController controller = new ConfigureAGVController();

    public ConfigureAGVUI (){

    }

    protected boolean doShow() {
        try{
            System.out.println("AGV: \n");
            final String identifier = Console.readLine("AGV Identifier: ");
            final int agvDock = Console.readInteger("Base Location(Dock): ");
            final int autonomy = Console.readInteger("Autonomy: ");
            final String description = Console.readLine("Description: ");
            final String model = Console.readLine("Model: ");
            final String positionX = Console.readLine("PositionX: ");
            final String positionY = Console.readLine("PositionY: ");
            final int maxWeightCarry = Console.readInteger("Maximum Weight: ");

            int positionXint = Integer.parseInt(positionX);
            int positionYint = Integer.parseInt(positionY);

            List<Sensor> sensorList = new ArrayList<>();
            Sensor a = new Sensor("a", positionXint-1, positionYint); sensorList.add(a);
            Sensor s = new Sensor("s", positionXint,positionYint-1); sensorList.add(s);
            Sensor d = new Sensor("d", positionXint+1,positionYint); sensorList.add(d);
            Sensor w = new Sensor("w", positionXint,positionYint+1); sensorList.add(w);

            controller.configureAGV(Identifier.valueOf(identifier), Autonomy.valueOf(autonomy), AGVDock.valueOf(agvDock),
                    AGV.valueOfDescription(description), Model.valueOf(model), new Position(positionXint, positionYint),
                    sensorList, MaxWeightCarry.valueOf(maxWeightCarry));

            System.out.println("Successful operation!");
        } catch (final IntegrityViolationException | IllegalAccessException ex){
            System.out.println("There is already a AGV with that information.");
        }
        return false;
    }

    public String headline() {
        return "Configure AGV";
    }
}
