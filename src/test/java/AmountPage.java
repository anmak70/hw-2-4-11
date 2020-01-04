import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.cssSelector;

public class AmountPage {
    By amountLocator = cssSelector("[data-test-id=amount] input");
    By fromLocator = cssSelector("[data-test-id=from] input");
    By toLocator = cssSelector("[data-test-id=to] input");
    By actionTransferLocator = cssSelector("[data-test-id=action-transfer]");
    By dashboardLocator = cssSelector("[data-test-id=dashboard]");

    private SelenideElement amount = $(amountLocator);
    private SelenideElement from = $(fromLocator);
    private SelenideElement to = $(toLocator);
    private SelenideElement transferButton = $(actionTransferLocator);


    public AmountPage() {
        $(dashboardLocator).waitUntil(Condition.visible, 15000);
    }

    public ActionDepositPage afterAmountFirst(String amountTransfer) {
        amount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        amount.setValue(amountTransfer);
        from.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        from.setValue("5559 0000 0000 0002");
        to.shouldHave(Condition.visible, Condition.value("**** **** **** 0001"));
        transferButton.click();
        return new ActionDepositPage();
    }

    public ActionDepositPage afterAmountSecond(String amountTransfer) {
        amount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        amount.setValue(amountTransfer);
        from.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        from.setValue("5559 0000 0000 0001");
        to.shouldHave(Condition.visible, Condition.value("**** **** **** 0002"));
        transferButton.click();
        return new ActionDepositPage();
    }

}
