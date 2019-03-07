package page.locator;

public interface UserRoleLocators {

    String TITLE = "//*[@id=\"main\"]/section/can-import/user-roles/div[1]/div/div[1]/h4";
    String ACTIONS_BUTTON_ADD_USERROLE = "//*[@id=\"add-user-role\"]";

    String DIALOG_CONTAINER = ".bootstrap-dialog";
    String ADD_EDIT_DIALOG_FIELD_NAME = "//*[@id=\"user-role\"]";
    String ADD_EDIT_DIALOG_CHECKBOXES = "//input[@type='checkbox']";
    String ADD_EDIT_DIALOG_CHECKBOX_CATEGORY_USERS = "//*[@id=\"Users\"]/following-sibling::label";
    String ADD_EDIT_DIALOG_CHECKBOX_PERM_ACCOUNT_ADMIN = "//*[contains(text(), 'PERM_IS_ACCOUNT_ADMIN')]";
    String ADD_EDIT_DIALOG_CHECKBOX_PERM_VIEW_ALL_USERS = "//*[contains(text(), 'PERM_VIEW_ALL_USERS')]";
    String ADD_EDIT_DIALOG_SLIMSCROLL_AREA = ".slimScrollDiv";
    String ADD_EDIT_DIALOG_SLIMSCROLL_BAR = ".modal-content .slimScrollDiv .slimScrollBar";
    String ADD_EDIT_DIALOG_BUTTON_SAVE = "//*[@id=\"save\"]";

    String DELETE_DIALOG_BUTTON_DELETE = "//*[@id=\"delete\"]";
    String DIALOG_BUTTON_CANCEL = ".btn-cancel";

    String TABLE_ROWS = "//*[@id=\"user-roles-tbl-body\"]/tr";
    String TABLE_ROW_BUTTON_EDIT = "td[2]/i[1]";
    String TABLE_ROW_BUTTON_DELETE = "td[2]/i[2]";

}
