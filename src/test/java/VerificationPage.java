import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.cssSelector;

public class VerificationPage {
    By codeLocator = cssSelector("[data-test-id=code] input");
    By verifyButtonLocator = cssSelector("[data-test-id=action-verify]");

    private SelenideElement codeField = $(codeLocator);
    private SelenideElement verifyButton = $(verifyButtonLocator);

    public VerificationPage() {
        codeField.waitUntil(Condition.visible, 15000);
    }

    public ActionDepositPage validVerity(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new ActionDepositPage();
    }
}
