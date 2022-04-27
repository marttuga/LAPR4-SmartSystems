package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.Utils;
import eapli.base.ordersmanagement.customer.application.RegisterCustomerController;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.order.application.NewProductOrderController;
import eapli.base.ordersmanagement.product.application.ProductSorter;
import eapli.base.ordersmanagement.product.application.ViewCatalogController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.apache.commons.lang3.StringUtils;
import java.util.Calendar;

public class NewProductOrderUI {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private static final ProductSorter productSorter = new ProductSorter();
    private static final ViewCatalogController catalogueController = new ViewCatalogController();
    private static final NewProductOrderController productOrderController = new NewProductOrderController();
    private static final RegisterCustomerController registerCustomerController = new RegisterCustomerController();

    public void run() {
        boolean continueRun;
        do {
            continueRun = false;
            String costumerID = Utils.readLineFromConsole("Please enter the costumerID: " + "\n(must have 7 numbers)");
            assert costumerID != null;
            if (costumerID.length() != 7 || StringUtils.isBlank(costumerID)) {
                System.out.println("\nThe data inserted was not valid! Please try again");
                break;
            } else {
                    for (Customer customer : registerCustomerController.findAllCustomers()) {
                    if (String.valueOf(customer.getCustomerId()).equals(costumerID)) {
                        System.out.println(customer);
                    }
                }

            }
            System.out.println("Customer does not exist");


            Calendar orderDate = Calendar.getInstance();
       /* final Dish theDish = selectDish();

        controller.registerMeal(MealType.LUNCH, currentDay, theDish);
    } catch (@SuppressWarnings("unused") final IntegrityViolationException ex) {
        System.out.println("There is already a meal for that day/type in that menu");
    }
            return false;
}

    private Dish selectDish() {
        System.out.println("List of Dishes - Select a Dish");
        final Iterable<Dish> listDish = listDishService.allDishes();
        final SelectWidget<Dish> selectorDish = new SelectWidget<>("Select a dish", listDish,
                new DishPrinter());
        selectorDish.show();
        return selectorDish.selectedElement();
    }

    @Override
    public String headline() {
        return "Register Meal On a Menu";
    }

*/
        } while (continueRun);
    }
}
