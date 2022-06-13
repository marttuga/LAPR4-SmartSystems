package eapli.base.warehousemanagement.activity;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Activity implements Runnable {

    private static final Logger logger = LogManager.getLogManager().getLogger(String.valueOf(Activity.class));
    private static WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouse();


    @Override
    public void run() {
        Iterable<Warehouse> warehouse;
        //warehouse = warehouseRepository.findByID();
    }
}
