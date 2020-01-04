import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.openqa.selenium.By.cssSelector;

public class ActionDepositPage {
    By dashboardLocator = cssSelector("[data-test-id=dashboard]");
    By topUpCard1Locator = cssSelector("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] [data-test-id=action-deposit]");
    By topUpCard2Locator = cssSelector("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] [data-test-id=action-deposit]");
    By card1BalanceLocator = cssSelector("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    By card2BalanceLocator = cssSelector("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    SelenideElement cardFirstBalance = $(card1BalanceLocator);
    SelenideElement cardSecondBalance = $(card2BalanceLocator);

    static int balanceCardFirstBefore = 0;
    static int balanceCardFirstAfter = 0;
    static int balanceCardSecondBefore = 0;
    static int balanceCardSecondAfter = 0;


    public ActionDepositPage() {
        $(dashboardLocator).waitUntil(Condition.visible, 15000);
//        $(CssLocatorsVariables.dashboardLocator).find("h1").should(Condition.visible, Condition.text("Ваши карты"));
    }

    public AmountPage topUpCard1() {
        SelenideElement topUpCard = $(topUpCard1Locator);
        topUpCard.click();
        return new AmountPage();
    }

    public AmountPage topUpCard2() {
        SelenideElement topUpCard = $(topUpCard2Locator);
        topUpCard.click();
        return new AmountPage();
    }

    public void  balanceCardBefore() {
        balanceCardFirstBefore = balanceCard(cardFirstBalance);
        balanceCardSecondBefore = balanceCard(cardSecondBalance);
    }

    public void verificationBalance1(String amountTransfer) {
        balanceCardFirstAfter = balanceCard(cardFirstBalance);
        balanceCardSecondAfter = balanceCard(cardSecondBalance);
        assertEquals(balanceCardFirstBefore + Integer.parseInt(amountTransfer), balanceCardFirstAfter);
        assertFalse((balanceCardSecondAfter | balanceCardFirstAfter) < 0);

    }

    public void verificationBalance2(String amountTransfer) {
        balanceCardFirstAfter = balanceCard(cardFirstBalance);
        balanceCardSecondAfter = balanceCard(cardSecondBalance);
        assertEquals(balanceCardSecondBefore + Integer.parseInt(amountTransfer), balanceCardSecondAfter);
        assertFalse((balanceCardSecondAfter | balanceCardFirstAfter) < 0);
    }

    public int balanceCard (SelenideElement cardBalance) {
        String cardBalanceText = cardBalance.getText();
        int begin = cardBalanceText.indexOf("баланс: ");
        int end = cardBalanceText.indexOf(" р.");
        String balance = cardBalanceText.substring(begin + 8, end);
        return Integer.parseInt(balance);
    }
}
