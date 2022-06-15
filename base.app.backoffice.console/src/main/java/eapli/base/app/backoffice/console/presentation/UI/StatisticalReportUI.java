package eapli.base.app.backoffice.console.presentation.UI;

import eapli.base.ordersmanagement.customer.applicaion.RegisterCustomerController;
import eapli.base.ordersmanagement.survey.application.StatisticalReportController;
import eapli.base.ordersmanagement.survey.dto.SurveyDTO;
import eapli.base.utilitarianClasses.Utils;
import eapli.framework.presentation.console.AbstractUI;

public class StatisticalReportUI extends AbstractUI {

    private final StatisticalReportController controller = new StatisticalReportController();

    @Override
    protected boolean doShow() {
        try {
            System.out.println("============================================================");
            System.out.println("==== Here are all the surveys available in the System! =====");
            System.out.println("============================================================");
            for (SurveyDTO dto: controller.showSurveys()) {
                System.out.println(dto);
            }
            //controller.showSurveys();
            String choice = Utils.readLine("== Please select the SurveyID to see a statistical report ==");
            System.out.println("====================== UNIVERSE ============================");
            System.out.println("For this report there were considered a total of  customers.");
            controller.calculateUniverse(choice);
            System.out.println("============================================================");

            return true;
        }catch (Exception e) {
            System.out.println("Something went wrong");
            return false;
        }
    }

    @Override
    public String headline() {
        return "=======  Statistical Report  =======";
    }
}
