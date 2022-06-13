package eapli.base.app.backoffice.console.presentation.UI;

import eapli.application.SeeOpenOrdersController;
import eapli.base.ordersmanagement.CustomerCliOrderServer.application.ProtocolProxyController;
import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.order.application.CheckOpenOrderController;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.domain.ProductOrderDto;
import eapli.base.ordersmanagement.order.domain.Status;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.protocol.client.AGVDTO;
import eapli.protocol.client.FailedRequestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckOpenOrderUI extends AbstractUI {
      CheckOpenOrderController checkOpenOrderController = new CheckOpenOrderController();
        private static final ProtocolProxyController addProductProtocolProxyController = new ProtocolProxyController();
        private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private static final SeeOpenOrdersController theController = new SeeOpenOrdersController();

        @Override
        protected boolean doShow() {
            try {
                Customer customer;
                String email = authz.session().get().authenticatedUser().email().toString();
                customer = checkOpenOrderController.findByCustomerEmail(email);
                System.out.println();
                System.out.println("              OPEN ORDERS :             ");
                System.out.println();
                List<ProductOrder> ordersList = checkOpenOrderController.findOpenOrders(Status.DELIVERED,email);
                checkOpenOrderController.printOrdersList(ordersList);

                //checkOpenOrderController.printList(ordersList)
                //addProductProtocolProxyController.checkOpenOrders( checkOpenOrderController.printList(ordersList));
               theController.getOpenOrders(Status.DELIVERED,email);
            } catch (final IntegrityViolationException ex) {
                System.out.println("Error checking the orders");
            } catch (IOException | FailedRequestException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public String headline() {
            return "Check open orders";
        }
}