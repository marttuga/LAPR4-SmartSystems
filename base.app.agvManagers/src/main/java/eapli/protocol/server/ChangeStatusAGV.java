package eapli.protocol.server;

import eapli.base.warehousemanagement.application.AGVChangeStatusController;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.domain.Status;

public class ChangeStatusAGV extends AGVManagerProtocol{

    private final Status status;

    public ChangeStatusAGV(final AGVChangeStatusController controller, final String request, final Status status ){
        super(controller, request);
        this.status = status;
    }

    @Override
    public String execute() {
        Status agv;
        try {
            agv = status;
        }catch (final NumberFormatException e){
            return buildBadRequest("Invalid AGV Status");
        }
        try {
            final AGV agv_info = controller.statusAGV(status);
            return buildResponse(agv_info);
        }catch (final Exception e) {
            return buildServerError(e.getMessage());
        }

    }

    private String buildResponse(AGV agv){
        final var sb = new StringBuilder();
        String info;
        sb.append(agv.toString());
        info = sb.toString();
        return info;
    }
}
