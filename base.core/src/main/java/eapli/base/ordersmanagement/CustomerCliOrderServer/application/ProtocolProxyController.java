package eapli.base.ordersmanagement.CustomerCliOrderServer.application;

import eapli.base.ordersmanagement.CustomerCliOrderServer.domain.BookingToken;
import eapli.base.ordersmanagement.CustomerCliOrderServer.service.CsvProtocolProxy;
import eapli.base.ordersmanagement.CustomerCliOrderServer.service.FailedRequestException;
import eapli.base.ordersmanagement.order.domain.ProductOrderDto;

import java.io.IOException;
import java.util.List;

public class ProtocolProxyController {
    private static final CsvProtocolProxy proxy = new CsvProtocolProxy();


    public BookingToken addProd(final String pi) throws FailedRequestException, IOException {
        return  proxy.addProd(pi);
    }
    public List<ProductOrderDto> checkOpenOrders(List<ProductOrderDto> pi) throws FailedRequestException, IOException {
        return  proxy.checkOpenOrders(pi);
    }
}
