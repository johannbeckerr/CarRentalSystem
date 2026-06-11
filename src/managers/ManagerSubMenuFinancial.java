package managers;

import SQLs.SelectFinancial;
import java.sql.SQLException;

/**
 * THIS CLASS MANAGES THE FINANCIAL SUB-MENU OPTIONS.
 * IT CALLS THE METHOD TO SHOW THE FINANCIAL REPORT.
 */
public class ManagerSubMenuFinancial {

    // THIS METHOD TRIGGERS THE DISPLAY OF THE TOTAL FINANCIAL SUMMARY
    public static void case1() throws SQLException, ClassNotFoundException {
        SelectFinancial.showTotalFinancial();
    }

}