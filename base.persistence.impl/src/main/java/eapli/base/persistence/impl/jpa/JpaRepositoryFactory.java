/*
 * Copyright (c) 2013-2021 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.ordersmanagement.answer.repository.AnswerRepository;
import eapli.base.ordersmanagement.category.repository.CategoryRepository;
import eapli.base.ordersmanagement.customer.repositories.CustomerRepository;
import eapli.base.ordersmanagement.order.repositories.LineOrderRepository;
import eapli.base.ordersmanagement.order.repositories.OrderRepository;
import eapli.base.ordersmanagement.product.repositories.ProductRepository;
import eapli.base.ordersmanagement.shoppingCart.repositories.ProductItemRepository;
import eapli.base.ordersmanagement.shoppingCart.repositories.ShoppingCartRepository;
import eapli.base.ordersmanagement.survey.repositories.SurveyRepository;
import eapli.base.warehousemanagement.repositories.AGVDockRepository;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.base.warehousemanagement.repositories.AisleRepository;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public JpaClientUserRepository clientUsers(final TransactionalContext autoTx) {
        return new JpaClientUserRepository(autoTx);
    }

    @Override
    public JpaClientUserRepository clientUsers() {
        return new JpaClientUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public OrderRepository orders(TransactionalContext autoTx) {
        return new JpaOrderRepository(autoTx);
    }

    @Override
    public OrderRepository orders() {
        return new JpaOrderRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ShoppingCartRepository shoppingCarts(TransactionalContext autoTx) {
        return new JpaShoppingCartRepository(autoTx);
    }

    @Override
    public ShoppingCartRepository shoppingCarts() {
        return new JpaShoppingCartRepository(Application.settings().getPersistenceUnitName());
    }


    @Override
    public ProductRepository products(TransactionalContext autoTx) {
        return new JpaProductRepository(autoTx);
    }

    @Override
    public ProductRepository products() {
        return new JpaProductRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public CustomerRepository customers(TransactionalContext autoTx) {
        return new JpaCustomerRepository(autoTx);
    }

    @Override
    public CustomerRepository customers() {
        return new JpaCustomerRepository(Application.settings().getPersistenceUnitName());
    }
    @Override
    public ProductItemRepository productItems(TransactionalContext autoTx) {
        return new JpaProductItemRepository(autoTx);
    }

    @Override
    public ProductItemRepository productItems() {
        return new JpaProductItemRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public LineOrderRepository lineOrders(TransactionalContext autoTx) {
        return new JpaLineOrderRepository(autoTx);
    }

    @Override
    public LineOrderRepository lineOrders() {
        return new JpaLineOrderRepository(Application.settings().getPersistenceUnitName());
    }


    @Override
    public WarehouseRepository warehouse(TransactionalContext autoTx) {
        return new JpaWarehouseRepository(autoTx);
    }

   @Override
    public WarehouseRepository warehouse() {
        return new JpaWarehouseRepository(Application.settings().getPersistenceUnitName());
    }

    public AGVDockRepository agvDock(TransactionalContext autoTx) {
        return new JpaAGVDockRepository(autoTx);
    }

    @Override
    public AGVDockRepository agvDock() {
        return new JpaAGVDockRepository(Application.settings().getPersistenceUnitName());
    }

    public AisleRepository aisle(TransactionalContext autoTx) {
        return new JpaAisleRepository(autoTx);
    }


    public AisleRepository aisle() {
        return new JpaAisleRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public CategoryRepository category(TransactionalContext autoTx) {
        return new JpaCategoryRepository(autoTx);
    }

    @Override
    public CategoryRepository category() {
        return new JpaCategoryRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public AGVRepository agv(TransactionalContext autoTx) {
        return new JpaAGVRepository(autoTx);
    }

    @Override
    public AGVRepository agv() {
        return new JpaAGVRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SurveyRepository survey(TransactionalContext autoTx) {
        return new JpaSurveyRepository(autoTx);
    }

    @Override
    public SurveyRepository survey() {
        return new JpaSurveyRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public AnswerRepository answer(TransactionalContext autoTx) {
        return new JpaAnswerRepository(autoTx);
    }

    @Override
    public AnswerRepository answer() {
        return new JpaAnswerRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

}
