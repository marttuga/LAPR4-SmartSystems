package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.ordersmanagement.category.application.DefineCategoryController;

import eapli.base.ordersmanagement.customer.applicaion.RegisterCustomerController;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.order.application.NewProductOrderController;

import eapli.base.ordersmanagement.order.domain.*;
import eapli.base.ordersmanagement.product.application.ViewCatalogController;
import eapli.base.ordersmanagement.product.domain.*;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.utilitarianClasses.Utils;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;


public class NewProductOrderUI extends AbstractUI {
    private static final ViewCatalogController catalogueController = new ViewCatalogController();
    private static final NewProductOrderController productOrderController = new NewProductOrderController();
    private static final RegisterCustomerController registerCustomerController = new RegisterCustomerController();
    private static final DefineCategoryController categoryController = new DefineCategoryController();

    public boolean doShow() {

        String orderActorID = Utils.readLineFromConsole("Please enter the Sales Clerck ID: ");

        Customer customer;
        String costumerID;
do {
  costumerID = Utils.readLineFromConsole("Please enter the costumerID: " + "\n(must have 7 digits)");
}while (registerCustomerController.findByCustomerIdOrder(costumerID)==null);
        customer = registerCustomerController.findByCustomerIdOrder(costumerID);
        System.out.println(customer);

        System.out.println();

        int optionFilter = 0;
        int optionOrdering = 0;

        List<Product> productList = catalogueController.findAllProducts();
        catalogueController.printProductsList(productList);
        List<Product> productListt = new ArrayList<>();

/////////FILTERING MENU
        optionFilter = catalogueController.showOptionsFilter();
        //ORDENAR A LISTA DE PRODUTOS
        switch (optionFilter) {
            case 0:
                System.out.println("Exiting ...");
                break;

            case 1:
                String categoryCode = Utils.readLine("Category code: ");
                productListt = catalogueController.printCategoriesList(categoryCode);
                break;

            case 2:
                String brandName = Utils.readLine("Brand: ");
                productListt = catalogueController.printBrandList(brandName);
                break;

            case 3:
                String description = Utils.readLine("Description: ");
                productListt = catalogueController.printDescriptionList(description);
                break;
    /*        case 4:
                Brand and Category();
                break;
            case 5:
                Brand and Short Description();
                break;
            case 6:
                Short Description and Category();
                break;
            case 7:
                Brand, Short Description and Category();
                break;*/

            default:
                System.out.println("Option does not exist!");
                break;
        }


        //////ORDERING MENU
        optionOrdering = catalogueController.showOptionsOrdering();
        //ORDENAR A LISTA DE PRODUTOS
        switch (optionOrdering) {
            case 0:
                System.out.println("Exiting ...");
                break;
            case 1:
                catalogueController.printOrderedDescription(productListt);
                break;
            case 2:
                catalogueController.printOrderedPrice(productListt);
                break;
            default:
                System.out.println("Option does not exist!");
                break;
        }

        LineOrder lineOrder = null;
        do {

            String productCode = Utils.readLine("Insert Product Internal Code: ");
            Product product = catalogueController.findByProductCode(productCode);
            System.out.println(product);

            int quantity = Utils.readIntegerFromConsole("Insert the quantity of the product: ");
            Set<Product> p = new HashSet<>();
            for (int i = 0; i < quantity; i++) {
                p.add(product);
            }
            ProductItem productItem = productOrderController.productItem(p, quantity);

            Set<ProductItem> pi = new HashSet<>();
            pi.add(productItem);
            lineOrder = productOrderController.lineOrder(pi);

        } while (Utils.confirm("Want to add more products? (y/n)"));


        productOrderController.showOptionsPayment();
        int pm = Utils.readIntegerFromConsole("Choose the payment method: \n");
        PaymentMethod paymentMethod = productOrderController.paymentMethod(pm);

        productOrderController.showOptionsShipping();
        int sm = Utils.readIntegerFromConsole("Choose the shipping method: \n");
        ShippingMethod shippingMethod = productOrderController.shippingMethod(sm);

        productOrderController.showSalesRegion();
        int sr = Utils.readIntegerFromConsole("Choose the region: \n");
        SalesRegion salesRegion = productOrderController.salesRegion(sr);

        PriceOrder priceOrder = productOrderController.priceOfOrder(lineOrder, salesRegion, shippingMethod);

        Calendar orderDate = Calendar.getInstance();

        Random rand = new Random();
        String id = String.valueOf(rand.nextInt(999999999));
        OrderID orderID = new OrderID(id);

        ProductOrder order = productOrderController.registerNewOrder(productOrderController.orderActor(orderActorID), orderID, customer, orderDate, lineOrder, priceOrder, paymentMethod, shippingMethod, Status.REGISTERED);
        System.out.println(order);

        return true;
    }

    @Override
    public String headline() {
        return "Make a Product Order";
    }
}
