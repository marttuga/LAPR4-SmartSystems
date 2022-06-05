package eapli.base.ordersmanagement.CustomerCliOrderServer.application;

import eapli.base.ordersmanagement.CustomerCliOrderServer.BookingToken;
import eapli.base.ordersmanagement.CustomerCliOrderServer.CsvProtocolProxy;
import eapli.base.ordersmanagement.CustomerCliOrderServer.FailedRequestException;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.shoppingCart.domain.ShoppingCart;

import java.io.IOException;
import java.util.List;

public class ProtocolProxyController {
    private static final CsvProtocolProxy proxy = new CsvProtocolProxy();


    public BookingToken addProd(final String pi) throws FailedRequestException, IOException {
        return  proxy.addProd(pi);
    }
    public BookingToken checkOpenOrders(List<String> pi) throws FailedRequestException, IOException {
        return  proxy.checkOpenOrders(pi);
    }
}
