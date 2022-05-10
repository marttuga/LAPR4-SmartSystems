package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.category.application.DefineCategoryController;

import eapli.base.ordersmanagement.customer.applicaion.RegisterCustomerController;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.customer.domain.CustomerId;
import eapli.base.ordersmanagement.order.application.NewProductOrderController;

import eapli.base.ordersmanagement.order.domain.*;
import eapli.base.ordersmanagement.product.application.ViewCatalogController;
import eapli.base.ordersmanagement.product.domain.*;
import eapli.base.ordersmanagement.shoppingCart.domain.ProductItem;
import eapli.base.utilitarianClasses.Utils;
import eapli.framework.presentation.console.AbstractUI;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;


public class NewProductOrderUI extends AbstractUI {
    private static final ViewCatalogController catalogueController = new ViewCatalogController();
    private static final NewProductOrderController productOrderController = new NewProductOrderController();
    private static final RegisterCustomerController registerCustomerController = new RegisterCustomerController();
    private static final DefineCategoryController categoryController = new DefineCategoryController();
    // private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();

    public boolean doShow() {

        String orderActorID = Utils.readLineFromConsole("Please enter the Sales Clerck ID: ");

        LineOrder lineOrder;
        Customer customer;
        Set<ProductItem> pi = new HashSet<>();
        String costumerID = Utils.readLineFromConsole("Please enter the costumerID: " + "\n(must have 7 digits)");

        customer = registerCustomerController.findByCustomerId(CustomerId.valueOf(costumerID));
        System.out.println(customer.toString());

        System.out.println();
        do {
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


            String productCode = Utils.readLine("Insert Product Internal Code: ");
            Product product = catalogueController.findByProductCode(productCode);
            System.out.println(product);

            int quantity = Utils.readIntegerFromConsole("Insert the quantity of the product: ");
            Set<Product> p = new HashSet<>();
            for (int i = 0; i < quantity; i++) {
                p.add(product);
            }
            ProductItem productItem = productOrderController.productItemSet(p, quantity);


            pi.add(productItem);

        } while (Utils.confirm("Want to add more products? (y/n)"));

        lineOrder = productOrderController.lineOrderSet(pi);

        PaymentMethod paymentMethod = productOrderController.paymentMethod(productOrderController.showOptionsPayment());

        ShippingMethod shippingMethod = productOrderController.shippingMethod(productOrderController.showOptionsShipping());

        SalesRegion salesRegion = productOrderController.salesRegion(productOrderController.showSalesRegion());
        PriceOrder priceOrder = productOrderController.priceOfOrder(lineOrder, salesRegion, shippingMethod);
        System.out.println(priceOrder);
        Calendar orderDate = Calendar.getInstance();

        String id = RandomStringUtils.randomAlphanumeric(6);
        OrderID orderID = new OrderID(id);

        OrderActor orderActor = productOrderController.orderActor(orderActorID);

        ProductOrder order = productOrderController.registerNewOrder(orderActor, orderID, customer, orderDate, lineOrder, priceOrder, paymentMethod, shippingMethod, Status.REGISTERED);
        System.out.println(order);
        return true;
    }

    @Override
    public String headline() {
        return "Make a Product Order";
    }
}
