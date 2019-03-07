package page.locator;

public interface UserManagementLocators {

    String TITLE = "//*[@id=\"main\"]/section/can-import/u-sers/div[1]/div/div[1]/h4";
    String ACTIONS_BUTTON_ADD_USER = "add-user";
    String ACTIONS_BUTTON_EXPORT_USERS = "export";
    String FIELD_SEARCH_BOX = "search-table";

    String DIALOG_CONTAINER = ".bootstrap-dialog";
    String ADD_EDIT_DIALOG_CHECKBOX_STATUS = "status";
    String ADD_EDIT_DIALOG_CHECKBOX_STATUS_LABEL = "//label[contains(text(), 'Active')]";
    String ADD_EDIT_DIALOG_FIELD_FIRSTNAME = "fname";
    String ADD_EDIT_DIALOG_FIELD_LASTNAME = "lname";
    String ADD_EDIT_DIALOG_FIELD_SUFFIX = "suffix";
    String ADD_EDIT_DIALOG_FIELD_EMAIL = "email";
    String ADD_EDIT_DIALOG_FIELD_CONFIRM_EMAIL = "confirm-email";
    String ADD_EDIT_DIALOG_FIELD_PASSWORD = "password";
    String ADD_EDIT_DIALOG_FIELD_CONFIRM_PASSWORD = "confirm-password";
    String ADD_EDIT_DIALOG_CHECKBOX_VERIFY_EMAIL = "verify-email";
    String ADD_EDIT_DIALOG_CHECKBOX_VERIFY_EMAIL_LABEL = "//*[contains(text(), 'Verify E-mail')]";
    String ADD_EDIT_DIALOG_CHECKBOX_VISIBILITY_SET1 = "//*[contains(text(), 'Visibility Set1')]/preceding-sibling::input[@type='checkbox']";
    String ADD_EDIT_DIALOG_CHECKBOX_VISIBILITY_SET1_LABEL = "//*[contains(text(), 'Visibility Set1')]";
    String ADD_EDIT_DIALOG_CHECKBOX_AUTHORIZED_MAIN_OFFICE = "Main Office";
    String ADD_EDIT_DIALOG_CHECKBOX_AUTHORIZED_MAIN_OFFICE_LABEL = "//*[@id=\"Main Office\"]/following-sibling::label";
    String ADD_EDIT_DIALOG_CHECKBOX_USER_ROLES = "//*[@id=\"account-roles\"]/input[@type='checkbox']";
    String ADD_EDIT_DIALOG_CHECKBOX_USER_ROLE_ACCOUNTADMIN_LABEL = "//*[contains(text(), 'USER_ROLE_ACCOUNTADMIN')]";
    String ADD_EDIT_DIALOG_CHECKBOX_USER_ROLE_FLEETMANAGER_LABEL = "//*[contains(text(), 'USER_ROLE_FLEETMANAGER')]";
    String ADD_EDIT_DIALOG_CHECKBOX_USER_ROLE_USERADMIN_LABEL = "//*[contains(text(), 'USER_ROLE_USERADMIN')]";
    String ADD_EDIT_DIALOG_CHECKBOX_USER_ROLE_ASSETADMIN_LABEL = "//*[contains(text(), 'USER_ROLE_ASSETADMIN')]";
    String ADD_EDIT_DIALOG_CHECKBOX_USER_ROLE_WORKORDERASSIGNEE_LABEL = "//*[contains(text(), 'USER_ROLE_WORKORDERASSIGNEE')]";
    String ADD_EDIT_DIALOG_CHECKBOX_USER_ROLE_USER_VIEW_ONLY_LABEL = "//*[contains(text(), 'USER_VIEW_ONLY')]";
    String ADD_EDIT_DIALOG_SLIMSCROLL_AREA = ".slimScrollDiv";
    String ADD_EDIT_DIALOG_SLIMSCROLL_BAR = ".modal-content > .slimScrollDiv > .slimScrollBar";
    String ADD_EDIT_DIALOG_BUTTON_SAVE = "//*[@id=\"save\"]";

    String EXPORT_DIALOG_OPTION_LABEL = "//*[@id=\"csv\"]/following-sibling::label";

    String EXPORT_DIALOG_BUTTON_GENERATE = "generate";
    String DELETE_DIALOG_BUTTON_DELETE = "//*[@id=\"delete\"]";

    String DIALOG_BUTTON_CANCEL = ".btn-cancel";

    String TABLE_ROWS = "//*[@id=\"users-tbl-body\"]/tr";
    String TABLE_ROW_BUTTON_EDIT = "td[4]/i[1]";
    String TABLE_ROW_BUTTON_DELETE = "td[4]/i[2]";

}
