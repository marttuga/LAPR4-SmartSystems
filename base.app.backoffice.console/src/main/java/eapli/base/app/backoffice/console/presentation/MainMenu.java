/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.backoffice.console.presentation;


import eapli.base.app.backoffice.console.presentation.UI.*;
import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.Application;
import eapli.base.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.base.app.backoffice.console.presentation.UI.RegisterCustomerUI;
import eapli.base.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.base.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.base.app.backoffice.console.presentation.clientuser.AcceptRefuseSignupRequestAction;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;
    private static final int VIEW_CATALOG = 3;
    private static final int NEW_ORDER = 4;
    private static final int CUSTOMER_REGISTER = 1;
    private static final int DEFINE_CATEGORY = 2;
    private static final int SPECIFY_NEW_PRODUCT = 5;
    private static final int CREATE_NEW_QUESTIONNAIRE = 6;
    private static final int ORDERS_THAT_HAD_BEEN_DISPATCHED = 7;
    private static final int CONFIGURE_AGV = 1;
    private static final int UPLOAD_FILE = 2;
    private static final int AGV_TO_PREP_ORDER = 3;

    private static final int DISPATCH_ORDER = 4;
    private static final int REQUEST = 5;

    private static final int SEE_REPORT = 1;

    private static final int ADD_PRODUCT_SHOPCART = 1;
    private static final int ANSWER_QUESTIONNAIRE= 2;
    private static final int CHECK_OPEN_ORDERS= 3;
    // SETTINGS
    private static final int SET_KITCHEN_ALERT_LIMIT_OPTION = 1;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int SETTINGS_OPTION = 3;
    private static final int SALES_OPTION = 4;
    private static final int WAREHOUSE_MANAGEMENT_OPTION = 5;
    private static final int CUSTOMER_OPTION = 6;

    private static final int SALES_MANAGER_OPTION = 7;


    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK_USER)) {
            final Menu clerkMenu = buildSalesClerkMenu();
            mainMenu.addSubMenu(SALES_OPTION,clerkMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.WAREHOUSE_EMPLOYEE)) {
            final Menu warehouseMenu = buildWarehouseEmployeeMenu();
            mainMenu.addSubMenu(WAREHOUSE_MANAGEMENT_OPTION,warehouseMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.SALES_MANAGER)) {
            final Menu salesMenu = buildSalesManagerMenu();
            mainMenu.addSubMenu(SALES_MANAGER_OPTION,salesMenu);
        }
        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.CLIENT_USER)) {
            final Menu customerMenu = buildCustomerMenu();
            mainMenu.addSubMenu(CUSTOMER_OPTION,customerMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }


    private Menu buildCustomerMenu() {
        final Menu menu = new Menu("Customer Menu >");

        menu.addItem(ADD_PRODUCT_SHOPCART, "Add products to Shopping Cart ",new AddProductShopCartUI()::show);
        menu.addItem(ANSWER_QUESTIONNAIRE, "Answer questionnaire ",new QuestionnaireAnswerUI()::show);
        menu.addItem(CHECK_OPEN_ORDERS, "Check your open orders ",new CheckOpenOrderUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(SET_KITCHEN_ALERT_LIMIT_OPTION, "Set kitchen alert limit",
                new ShowMessageAction("Not implemented yet"));
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    //////////SALES CLERCK
    private Menu buildSalesClerkMenu() {
        final Menu menusMenu = new Menu("Sales Clerk Menu >");

        menusMenu.addItem(CUSTOMER_REGISTER, "Register New Customer", new RegisterCustomerUI()::show);
        menusMenu.addItem(DEFINE_CATEGORY, "Define New Category", new DefineCategoryUI()::show);
        menusMenu.addItem(VIEW_CATALOG, "View Catalog", new ViewCatalogUI()::show);
        menusMenu.addItem(NEW_ORDER, "New Product Order", new NewProductOrderUI()::show);
        menusMenu.addItem(SPECIFY_NEW_PRODUCT, "Specify New Product", new SpecifyNewProductUI()::show);
        menusMenu.addItem(CREATE_NEW_QUESTIONNAIRE, "Create New Questionnaire", new CreateNewQuestionnaireUI()::show);
        menusMenu.addItem(ORDERS_THAT_HAD_BEEN_DISPATCHED, "List of orders that had been dispatched", new OrdersDispatchedUI()::show);
        menusMenu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menusMenu;
    }

   private Menu buildWarehouseEmployeeMenu() {
        final Menu menusMenu = new Menu("Warehouse Management >");
        menusMenu.addItem(CONFIGURE_AGV, "Configure AGV", new ConfigureAGVUI()::show);
        //menusMenu.addItem(UPLOAD_FILE, "Upload File", new UploadFileUI()::show);
       menusMenu.addItem(AGV_TO_PREP_ORDER, "Get an order to an AGV", new AGVToPrepOrderUI()::doShow);
        menusMenu.addItem(DISPATCH_ORDER, "Dispatch orders for customer delivery", new DispatchOrdersUI()::doShow);
        menusMenu.addItem(REQUEST, "Requests", new GetAGVInfoUI()::doShow);
        menusMenu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menusMenu;
    }

    private Menu buildSalesManagerMenu() {
        final Menu menusMenu = new Menu("Sales Manager Menu >");
        menusMenu.addItem(SEE_REPORT, "Surveys statistical report", new StatisticalReportUI()::show);

        menusMenu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menusMenu;
    }


    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
                new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);


        return menu;
    }

}
