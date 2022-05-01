package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.*;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ConfigureAGVController {

    private final AGVService agvService = new AGVService();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();

    public AGV configureAGV(Identifier identifier, int autonomy, AGVDock agvDock, String AGVDescription, Model model, MaxWeightCarry maxWeightCarry) throws IllegalAccessException{
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE);
        //begin transaction
        txCtx.beginTransaction();
        //final AGV agv = agvService.createAGV(identifier,autonomy,agvDock,AGVDescription,model, maxWeightCarry);
        final AGV agv = new AGV(identifier, autonomy, agvDock, AGVDescription, model, maxWeightCarry);
        this.agvRepository.save(agv);
        txCtx.commit();

        return agv;
    }
}
