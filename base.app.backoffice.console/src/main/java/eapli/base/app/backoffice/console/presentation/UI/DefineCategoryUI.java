package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.ordersmanagement.category.application.DefineCategoryController;
import eapli.base.ordersmanagement.category.domain.CategoryCode;
import eapli.base.ordersmanagement.category.domain.CategoryDescription;

import eapli.framework.presentation.console.AbstractUI;
import java.util.Scanner;

public class DefineCategoryUI extends AbstractUI {

    DefineCategoryController categoryController = new DefineCategoryController();

    Scanner in = new Scanner(System.in);

    @Override
    protected boolean doShow() {
        try {
            System.out.println("==================================================");
            System.out.println("=======Welcome to category's defining menu!=======");
            System.out.println("==================================================");
            System.out.println("Please insert a Category code: (Code must be have a maximum of 10 characters)");
            String code = in.nextLine();
            CategoryCode categoryCode = CategoryCode.valueOf(code);
            System.out.println("==================================================");
            System.out.println("Please insert a Category description:(Description must be between 20 and 50 characters)");
            String description = in.nextLine();
            CategoryDescription categoryDescription = CategoryDescription.valueOf(description);
            System.out.println("==================================================");
            categoryController.defineCategory(categoryCode,categoryDescription);
            return true;
        } catch (Exception e) {
            System.out.println("Something went wrong");
            return false;
        }
    }

    @Override
    public String headline() {
        return "Define new category";
    }
}
