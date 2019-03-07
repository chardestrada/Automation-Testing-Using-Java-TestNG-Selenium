package util.extentreport;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class CustomMarkupHelper extends MarkupHelper {

    public CustomMarkupHelper() {
    }

    public static Markup createLabel(String text, ExtentColor color) {
        CustomLabel l = new CustomLabel();
        l.setText(text);
        l.setColor(color);
        return l;
    }

}
