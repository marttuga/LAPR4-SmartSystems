package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.AGV;
import eapli.base.warehousemanagement.repositories.AGVRepository;

public class GetAGVController {
        private final AGVRepository agvRepository = PersistenceContext.repositories().agv();
        public GetAGVController(){

        }

        public Iterable<AGV> findAll(){
            return agvRepository.findAll();
        }

}
