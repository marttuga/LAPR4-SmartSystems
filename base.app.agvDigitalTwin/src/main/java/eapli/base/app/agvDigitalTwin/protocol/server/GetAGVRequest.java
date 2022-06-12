package eapli.base.app.agvDigitalTwin.protocol.server;

import eapli.base.warehousemanagement.application.AgvRequestController;
import eapli.base.warehousemanagement.domain.AGV;

public class GetAGVRequest extends AGVTwinProtocolRequest{

    private final String id;

    public GetAGVRequest(final AgvRequestController controller, final String request, final String id ){
        super(controller, request);
        this.id = id;
    }

    @Override
    public String execute() {
        String agv;
        try {
            agv = id;
        }catch (final NumberFormatException e){
            return buildBadRequest("Invalid AGV ID");
        }
        try {
            final AGV agv_info = controller.findAgv(agv);
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
