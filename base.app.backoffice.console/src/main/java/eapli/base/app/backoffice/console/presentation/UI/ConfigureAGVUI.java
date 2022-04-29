package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.app.backoffice.console.presentation.MainMenu;
import eapli.base.warehousemanagement.application.ConfigureAGVController;
import eapli.base.warehousemanagement.domain.*;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class ConfigureAGVUI extends AbstractUI {

    private final ConfigureAGVController controller = new ConfigureAGVController();
    MainMenu mainMenu;

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
            final Double maxWeightCarry = Console.readDouble("Maximum Weight: ");
                controller.configureAGV(Identifier.valueOf(identifier), AGV.valueOfAutonomy(autonomy), AGVDock.valueOf(agvDock), AGV.valueOfDescription(description), Model.valueOf(model), MaxWeightCarry.valueOf(maxWeightCarry));

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
