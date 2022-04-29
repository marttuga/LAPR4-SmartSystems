package eapli.base.utilitarianClasses;
import eapli.framework.io.util.Console;
import eapli.framework.time.util.Calendars;
import org.apache.logging.log4j.LogManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Utils {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Console.class);

    public static String readLineFromConsole(String prompt) {
        try {
            Utils.printToConsole("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int readIntegerFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                int value = Integer.parseInt(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    public static double readDoubleFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                double value = Double.parseDouble(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    public static Date readDateFromConsole(String prompt) {
        do {
            try {
                String strDate = readLineFromConsole(prompt);

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    public static void printToConsole(String message){
        System.out.printf("%s%n",message);
    }

    public static boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("y");
    }

    public static Object showAndSelectOne(List list, String header) {
        showList(list, header);
        return selectsObject(list);
    }

    public static int showAndSelectIndex(List list, String header) {
        showList(list, header);
        return selectsIndex(list);
    }

    public static void showList(List list, String header) {
        Utils.printToConsole(header);

        int index = 0;
        for (Object o : list) {
            index++;

            Utils.printToConsole(index + ". " + o.toString());
        }
        Utils.printToConsole("");
        Utils.printToConsole("0 - Cancel");
    }

    public static Object selectsObject(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value == 0) {
            return null;
        } else {
            return list.get(value - 1);
        }
    }

    public static int selectsIndex(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    public static Calendar readCalendar(final String prompt) {
        return readCalendar(prompt, "yyyy-MM-dd");
    }

    public static Calendar readCalendar(final String prompt, final String dateFormat) {
        while(true) {
            try {
                String strDate = readLine(prompt);
                SimpleDateFormat df = new SimpleDateFormat(dateFormat);
                return Calendars.fromDate(df.parse(strDate));
            } catch (ParseException var4) {
            }
        }
    }

    public static String readLine(final String prompt) {
        try {
            System.out.println(prompt);
            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);
            return in.readLine();
        } catch (IOException var3) {
            LOGGER.warn("Ignoring but this is really strange that it even happened.", var3);
            return "";
        }
    }

}