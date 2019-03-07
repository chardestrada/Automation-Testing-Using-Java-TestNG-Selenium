package page.locator;

public interface TerminalLocators {

    String TITLE = "//*[@id=\"main\"]/section/can-import/home-terminals/div[1]/div/div[1]/h4";
    String ACTIONS_BUTTON_ADD_TERMINAL = "//*[@id=\"add-terminal\"]";
    String ACTIONS_BUTTON_UPLOAD_TERMINAL = "//*[@title=\"Upload CSV\"]";

    String DIALOG_CONTAINER = ".bootstrap-dialog";
    String ADD_EDIT_DIALOG_FIELD_NAME = "//*[@id=\"name\"]";
    String ADD_EDIT_DIALOG_FIELD_CODE = "//*[@id=\"terminal-code\"]";
    String ADD_EDIT_DIALOG_FIELD_STREET = "//*[@id=\"street\"]";
    String ADD_EDIT_DIALOG_FIELD_CITY = "//*[@id=\"city\"]";
    String ADD_EDIT_DIALOG_FIELD_STATE_OR_PROVINCE = "//*[@id=\"state-province\"]";
    String ADD_EDIT_DIALOG_FIELD_POSTAL_CODE = "//*[@id=\"postal-code\"]";
    String ADD_EDIT_DIALOG_FIELD_COUNTRY = "//*[@id=\"country\"]";
    String ADD_EDIT_DIALOG_FIELD_PHONE_NUMBER = "//*[@id=\"phoneNumber\"]";
    String ADD_EDIT_DIALOG_SELECT_SEARCH_FIELD_TIMEZONE = "//*[@id=\"timezone-filter-group\"]";
    String ADD_EDIT_DIALOG_SELECT_SEARCH_FIELD_START_TIME_OF_DAY = "//*[@id=\"start-time-filter-group\"]";
    String ADD_EDIT_DIALOG_CHECKBOX_MAIN_OFFICE = "//*[@id=\"mainOffice\"]/following-sibling::label";
    String ADD_EDIT_DIALOG_SLIMSCROLL_AREA = ".slimScrollDiv";
    String ADD_EDIT_DIALOG_SLIMSCROLL_BAR = ".modal-content .slimScrollDiv .slimScrollBar";
    String ADD_EDIT_DIALOG_BUTTON_SAVE = "//*[@id=\"save\"]";

    String DELETE_DIALOG_BUTTON_DELETE = "//*[@id=\"delete\"]";

    String UPLOAD_CSV_DIALOG_LINK = "csv-link";
    String UPLOAD_CSV_DIALOG_LINK_IMAGE = "Terminal CSV Sample";
    String UPLOAD_CSV_DIALOG_IFRAME = "uploadCSV";
    String UPLOAD_CSV_DIALOG_IFRAME_BUTTON_FILE = "csvFile";
    String UPLOAD_CSV_DIALOG_IFRAME_RADIO_BUTTONS = "action";
    String UPLOAD_CSV_DIALOG_IFRAME_ALERT_MESSAGE = ".alert strong";
    String UPLOAD_CSV_DIALOG_IFRAME_BUTTON_UPLOAD = "upload-terminals-btn";
    String UPLOAD_CSV_DIALOG_IFRAME_POPUP_YES = ".bootstrap-dialog.type-warning .bootstrap-dialog-footer-buttons .btn.btn-link";

    String UPLOAD_CSV_DIALOG_BUTTON_CLOSE = ".bootstrap-dialog-footer-buttons .btn.btn-link";

    String TABLE_ROWS = "//*[@id=\"terminals-tbl-body\"]/tr";
    String TABLE_ROW_BUTTON_EDIT = "td[3]//*[@id=\"edit-terminal\"]";
    String TABLE_ROW_BUTTON_DELETE = "td[3]//*[@id=\"delete-terminal\"]";

}
