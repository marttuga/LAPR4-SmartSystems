package eapli.protocolServer;

import eapli.base.ordersmanagement.customer.domain.Customer;
import eapli.base.ordersmanagement.order.application.CheckOpenOrderController;

import eapli.base.ordersmanagement.order.domain.ProductOrder;
import eapli.base.ordersmanagement.order.domain.Status;
import eapli.framework.csv.CsvRecord;

import java.util.List;

public class GetOpenOrdersRequest extends OrderProtocolRequest {

    private final Status statu;
    private final String co;
    public GetOpenOrdersRequest(final CheckOpenOrderController controller, final String request, final Status status , final String c){
        super(controller, request);
        this.statu= status;
        this.co= c;
    }

    @Override
    public String execute() {
        Status status;
        String c;
        try {
            status = statu;
        }catch (final NumberFormatException e){
            return buildBadRequest("Invalid Satus");
        }
        try {
            c=co;
        }catch (final NumberFormatException e){
                return buildBadRequest("Invalid Customer");
        }
        try {
            final List<ProductOrder> orders = controller.findOpenOrders(status,c);
            return buildResponse(orders);
        }catch (final Exception e) {
            return buildServerError(e.getMessage());
        }

    }

    private String buildResponse(List<ProductOrder> orders){
        final var sb = new StringBuilder();

        // header
        sb.append("\"ID\", \"CUSTOMER\", \"STATUS\"\n");

        // result rows
        for (final ProductOrder each : orders) {
            sb.append(
                    CsvRecord.valueOf(new Object[] {
                            each.getOrderID(),
                            each.getCustomer(),
                            each.getStatus()
                    }, new boolean[] { false, false, false }));
            // end of line
            sb.append("\n");
        }

        // end of message
        sb.append("\n");

        return sb.toString();
    }
}
