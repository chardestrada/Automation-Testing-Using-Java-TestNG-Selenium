package test.data;

public interface TerminalData{

    String TERMINAL_HEADER_TEXT = "terminals";
    String TERMINAL_MAIN_OFFICE_DEFAULT = "Main Office";

    // Terminal information when creating a terminal
    String TERMINAL_NAME = "Terminal A";
    String TERMINAL_CODE = "T-001";
    String TERMINAL_STREET = "Central Avenue. Lake Station";
    String TERMINAL_CITY = "Lake County";
    String TERMINAL_STATE = "Indiana";
    String TERMINAL_POSTAL_CODE = "46405";
    String TERMINAL_COUNTRY = "United States of America";
    String TERMINAL_TIME_ZONE = "CST6CDT";
    String TERMINAL_START_TIME_OF_DAY = "12 am";
    String TERMINAL_PHONE_NUMBER = "1223334444";

    // Terminal information when editing a terminal
    String TERMINAL_EDIT_NAME = "Terminal B";
    String TERMINAL_EDIT_CODE = "T-002";
    String TERMINAL_EDIT_STREET = "505 N. Michigan Ave.";
    String TERMINAL_EDIT_CITY = "Chicago";
    String TERMINAL_EDIT_STATE = "IL";
    String TERMINAL_EDIT_POSTAL_CODE = "60611";

    // French terminal information
    String TERMINAL_NAME_FRENCH = "French Çéâêîôûàèùëïü";
    String TERMINAL_CODE_FRENCH = "T-003";
    String TERMINAL_STREET_FRENCH = "98 Rue Marie De Médicis";
    String TERMINAL_CITY_FRENCH = "CALUIRE-ET-CUIRE";
    String TERMINAL_STATE_FRENCH = "Rhône-Alpes";
    String TERMINAL_POSTAL_CODE_FRENCH = "69300";
    String TERMINAL_COUNTRY_FRENCH = "France";
    String TERMINAL_TIME_ZONE_FRENCH = "EST5EDT";

    String TERMINAL_NAME_SPANISH = "Spanish áéíóúüñ";
    String TERMINAL_CODE_SPANISH = "T-004";
    String TERMINAL_STREET_SPANISH = "Ctra. Bailén-Motril 90";
    String TERMINAL_CITY_SPANISH = "Arquillos";
    String TERMINAL_STATE_SPANISH = "Jaén";
    String TERMINAL_POSTAL_CODE_SPANISH = "23230";
    String TERMINAL_COUNTRY_SPANISH = "Spain";
    String TERMINAL_TIME_ZONE_SPANISH = "PST8PDT";

    String TERMINAL_CSV_NAME = "Terminal C";

    String TERMINAL_CSV_CREATE_PATH = "src\\main\\resources\\terminals-create.csv";
    String TERMINAL_CSV_UPDATE_PATH = "src\\main\\resources\\terminals-update.csv";

}
