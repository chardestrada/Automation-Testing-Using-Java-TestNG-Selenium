package util.extentreport;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;

public class CustomLabel implements Markup {
    private String text = "";
    private ExtentColor color;

    CustomLabel() {
        this.color = ExtentColor.TRANSPARENT;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setColor(ExtentColor color) {
        this.color = color;
    }

    public ExtentColor getColor() {
        return this.color;
    }

    public String getMarkup() {
        String lhs;
        if (this.color == ExtentColor.WHITE) {
            lhs = "<span class='blue-text " + String.valueOf(this.color).toLowerCase() + "'>";
        } else {
            lhs = "<span class='label white-text " + String.valueOf(this.color).toLowerCase() + "'>";
        }
        String rhs = "</span>";
        return lhs + this.text + rhs;
    }
}
