package eapli.base.ordersmanagement.product.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.category.domain.Category;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.product.domain.*;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.domain.*;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;

/**
 *
 * @author João Tiago Araújo <<1200584@isep.ipp.pt>>
 */

public class SpecifyNewProductController {

    private final ProductService productService = new ProductService();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    //private final ProductRepository productRepository = PersistenceContext.repositories().products();

    public Product specifyNewProduct(UniqueInternalCode uniqueInternalCode, ShortDescription shortDescription, ExtendedDescription extendedDescription, TechnicalDescription technicalDescription, Brand brand, Reference reference, ProductionCode productionCode, Weight weight , Barcode barcode, ProductPriceDetail priceDetail, CategoryCode categoryCode, Dimension dimension, WarehouseID warehouseID, Aisle aisle, Row row, Shelf shelf) throws IllegalAccessException{
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK_USER);
        //begin transaction
        txCtx.beginTransaction();
        final Product product = productService.createProduct(uniqueInternalCode,shortDescription,extendedDescription,technicalDescription,brand,reference,productionCode,weight,barcode,priceDetail,categoryCode,dimension,warehouseID,aisle,row,shelf);
        txCtx.commit();

        return product;
    }
    public Product specifyNewProductMandatory(UniqueInternalCode uniqueInternalCode, ShortDescription shortDescription, ExtendedDescription extendedDescription) throws IllegalAccessException{
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK_USER);
        //begin transaction
        txCtx.beginTransaction();
        final Product product = productService.createProductMandatory(uniqueInternalCode,shortDescription,extendedDescription);
        txCtx.commit();
        return product;
    }

}
