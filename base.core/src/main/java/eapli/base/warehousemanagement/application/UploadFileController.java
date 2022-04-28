package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.product.domain.Brand;
import eapli.base.ordersmanagement.product.domain.Product;
import eapli.base.ordersmanagement.product.domain.ShortDescription;
import eapli.base.ordersmanagement.product.repositories.ProductRepository;
import eapli.base.warehousemanagement.domain.UploadFile;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Optional;

@UseCaseController
public class UploadFileController {
   UploadFile read = new UploadFile();

   private final AuthorizationService authz = AuthzRegistry.authorizationService();
   private final WarehouseRepository warehouseRepository= PersistenceContext.repositories().warehouse();


   public Iterable<Warehouse> getJSONFile() {
      return warehouseRepository.findAll();
   }

   /*public boolean uploadFile(String file_name){
      boolean result = read.uploadFile(file_name);
      return result;
   }*/


}
