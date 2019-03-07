package page.locator;

public interface DashBoardLocators {

    String HEADER_TEXT_USERNAME = "//*[@id=\"navbar-collapse\"]/ul/li[1]/a/span";
    String HEADER_BUTTON_REFRESH = "//div[@id=\"navbar-collapse\"]/ul/li[3]/a";
    String HEADER_BUTTON_SIGN_OUT = "//div[@id=\"navbar-collapse\"]/ul/li[5]/a";

    String DEFAULT_TAB_TITLE = "//*[@id=\"main\"]/section/can-import/driver-status/div[1]/div/div[1]/h4";

    String TOAST_MESSAGE = "//*[@id=\"toast-container\"]/div/div";

    String MENU_SETTINGS = "//*[@id=\"leftsidebar\"]/div[1]/div/ul/li[4]";

    String MENU_SETTINGS_USERS_TAB = "//*[@id=\"leftsidebar\"]/div[1]/div/ul/li[4]/ul/li[6]/a";
    String MENU_SETTINGS_USER_ROLES_TAB = "//*[@id=\"leftsidebar\"]/div[1]/div/ul/li[4]/ul/li[7]/a";
    String MENU_SETTINGS_TERMINALS_TAB = "//*[@id=\"leftsidebar\"]/div[1]/div/ul/li[4]/ul/li[11]/a";

    String TABLE_LOADING_ROWS = ".data-table tbody tr td div .preloader";

}
