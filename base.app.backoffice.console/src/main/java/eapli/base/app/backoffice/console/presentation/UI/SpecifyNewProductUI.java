package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.product.application.SpecifyNewProductController;
import eapli.base.ordersmanagement.product.domain.*;
import eapli.base.warehousemanagement.domain.*;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author João Tiago Araújo <<1200584@isep.ipp.pt>>
 */


public class SpecifyNewProductUI extends AbstractUI {

    private final SpecifyNewProductController controller = new SpecifyNewProductController();

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
                final String barcode = Console.readLine("Barcode(EAN-13): ");
                controller.specifyNewProductMandatory(UniqueInternalCode.valueOf(uniqueInternalCode), ShortDescription.valueOf(shortDescription), ExtendedDescription.valueOf(extendedDescription), Barcode.valueOf(barcode));
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
                final int aisleID = Console.readInteger("Aisle ID: ");
                final int rowID = Console.readInteger("Row ID: ");
                final int shelfID = Console.readInteger("Shelf ID: ");

                controller.specifyNewProduct(UniqueInternalCode.valueOf(uniqueInternalCode), ShortDescription.valueOf(shortDescription), ExtendedDescription.valueOf(extendedDescription), TechnicalDescription.valueOf(technicalDescription), Brand.valueOf(brand), Reference.valueOf(reference),ProductionCode.valueOf(productionCode), Weight.valueOf(weight), Barcode.valueOf(barcode), ProductPriceDetail.valueOf(priceDetail), CategoryCode.valueOf(categoryCode), Dimension.valueOf(width,height,length), Product.valueOfAisleId(aisleID), Product.valueOfRowId(rowID), Product.valueOfShelfId(shelfID));
                //controller.specifyNewProduct(UniqueInternalCode.valueOf(uniqueInternalCode), ShortDescription.valueOf(shortDescription), ExtendedDescription.valueOf(extendedDescription), TechnicalDescription.valueOf(technicalDescription), Brand.valueOf(brand), Reference.valueOf(reference),ProductionCode.valueOf(productionCode), Weight.valueOf(weight), Barcode.valueOf(barcode), ProductPriceDetail.valueOf(priceDetail), Dimension.valueOf(width,height,length));

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

