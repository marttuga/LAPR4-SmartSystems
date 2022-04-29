package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.app.backoffice.console.presentation.MainMenu;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.product.application.SpecifyNewProductController;
import eapli.base.ordersmanagement.product.domain.*;
import eapli.base.warehousemanagement.domain.*;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.io.util.Console;

/**
 *
 * @author João Tiago Araújo <<1200584@isep.ipp.pt>>
 */


public class SpecifyNewProductUI {

    private final SpecifyNewProductController controller = new SpecifyNewProductController();
    MainMenu mainMenu;

    public SpecifyNewProductUI (){

    }

    protected boolean doShow() {
        try{

            int option = Console.readInteger("Do you want to specify all the data?\n[1] - yes;\n[0] - no.");
            if (option == 0){
                System.out.println("Product: \n");
                final String uniqueInternalCode = Console.readLine("Unique Internal Code: ");
                final String shortDescription = Console.readLine("Short description: ");
                final String extendedDescription = Console.readLine("Extended description: ");
                controller.specifyNewProductMandatory(UniqueInternalCode.valueOf(uniqueInternalCode), ShortDescription.valueOf(shortDescription), ExtendedDescription.valueOf(extendedDescription));
            } else {
                System.out.println("Product: \n");
                final String uniqueInternalCode = Console.readLine("Unique Internal Code: ");
                final String shortDescription = Console.readLine("Short description: ");
                final String extendedDescription = Console.readLine("Extended description: ");
                final String technicalDescription = Console.readLine("Technical description: ");
                final String brand = Console.readLine("Brand: ");
                final String reference = Console.readLine("Reference: ");
                final String productionCode = Console.readLine("Production code: ");
                final String weight = Console.readLine("Weight: ");
                final String barcode = Console.readLine("Barcode(EAN-13): ");
                final String priceDetail = Console.readLine("Price: ");
                final String categoryCode = Console.readLine("Category: ");
                final Double width = Console.readDouble("Width: ");
                final Double length = Console.readDouble("Length: ");
                final Double height = Console.readDouble("Height: ");
                final String warehouseID = Console.readLine("Warehouse ID: ");
                final String aisleID = Console.readLine("Aisle ID: ");
                final String rowID = Console.readLine("Row ID: ");
                final String shelfID = Console.readLine("Shelf ID: ");

                controller.specifyNewProduct(UniqueInternalCode.valueOf(uniqueInternalCode), ShortDescription.valueOf(shortDescription), ExtendedDescription.valueOf(extendedDescription), TechnicalDescription.valueOf(technicalDescription), Brand.valueOf(brand), Reference.valueOf(reference),ProductionCode.valueOf(productionCode), Weight.valueOf(weight), Barcode.valueOf(barcode), ProductPriceDetail.valueOf(priceDetail), CategoryCode.valueOf(categoryCode), Dimension.valueOf(width,height,length), WarehouseID.valueOf(warehouseID), Aisle.valueOf(aisleID), Row.valueOf(rowID), Shelf.valueOf(shelfID));

            }
            System.out.println("Successful operation!");
        } catch (final IntegrityViolationException | IllegalAccessException ex){
            System.out.println("There is already a product with that information.");
        }
        return false;
    }

    public String headline() {
        return "Specify New Product";
    }
}

