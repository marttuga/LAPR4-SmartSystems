package eapli.base.ordersmanagement.CustomerCliOrderServer.application;

import eapli.base.ordersmanagement.CustomerCliOrderServer.BookingToken;
import eapli.base.ordersmanagement.CustomerCliOrderServer.CsvAddProductProtocolProxy;
import eapli.base.ordersmanagement.CustomerCliOrderServer.CsvCheckOpenOrdersProtocolProxy;
import eapli.base.ordersmanagement.CustomerCliOrderServer.FailedRequestException;
import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.shoppingCart.domain.ShoppingCart;

import java.io.IOException;
import java.util.List;

public class ProtocolProxyController {
    private static final CsvAddProductProtocolProxy proxy = new CsvAddProductProtocolProxy();
    private static final CsvCheckOpenOrdersProtocolProxy proxyy = new CsvCheckOpenOrdersProtocolProxy();

    public BookingToken addProd(final ShoppingCart pi) throws FailedRequestException, IOException {
        return  proxy.addProd(pi);
    }
    public BookingToken checkOpenOrders(List<ProductOrder> pi) throws FailedRequestException, IOException {
        return  proxyy.checkOpenOrders(pi);
    }
}
