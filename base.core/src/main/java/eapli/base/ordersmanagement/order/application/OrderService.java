package eapli.base.ordersmanagement.order.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;

@ApplicationService
public class OrderService {
        private final UserManagementService userService = AuthzRegistry.userService();
        private final OrderRepository customerRepository = PersistenceContext.repositories().orders();

}
