package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.ordersmanagement.CustomerCliOrderServer.FailedRequestException;
import eapli.base.ordersmanagement.CustomerCliOrderServer.application.ProtocolProxyController;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.order.application.CheckOpenOrderController;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.domain.Status;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;
import java.util.List;

public class CheckOpenOrderUI extends AbstractUI {
    CheckOpenOrderController checkOpenOrderController = new CheckOpenOrderController();
    private static final ProtocolProxyController addProductProtocolProxyController = new ProtocolProxyController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();


    @Override
    protected boolean doShow() {
        try {
            Customer customer;
            String email = authz.session().get().authenticatedUser().email().toString();
            customer = checkOpenOrderController.findByCustomerEmail(email);
            System.out.println();
            System.out.println("              OPEN ORDERS :             ");
            System.out.println();
            List<ProductOrder> ordersList = checkOpenOrderController.findOpenOrders(Status.DELIVERED,customer);
            checkOpenOrderController.printOrdersList(ordersList);


            addProductProtocolProxyController.checkOpenOrders( checkOpenOrderController.printList(ordersList));

        } catch (final IntegrityViolationException ex) {
            System.out.println("Error checking the orders");
        } catch (FailedRequestException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String headline() {
        return "Check open orders";
    }

}