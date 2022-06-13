package eapli.application;

import eapli.base.ordersmanagement.order.domain.ProductOrderDto;
import eapli.base.ordersmanagement.order.domain.Status;
import eapli.protocol.client.CustomerProxy;
import eapli.protocol.client.FailedRequestException;

import java.io.IOException;
import java.util.List;

public class SeeOpenOrdersController {

    static final CustomerProxy proxy = new CustomerProxy();

    public  List<ProductOrderDto> getOpenOrders(final Status id, final String c) throws IOException, FailedRequestException {
        return proxy.getOpenOrders(id,c);
    }
}
