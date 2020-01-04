
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Selenide.open;

public class TestTransferMoneyBetweenMyCards {

    @ParameterizedTest
    @CsvFileSource(resources = "/AmountData.csv", numLinesToSkip = 1)
    void shouldTransferMoneyBetweenMyCards(String amountTransfer1, String amountTransfer2) {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCode();
        ActionDepositPage actionDepositPage = verificationPage.validVerity(verificationCode);
        actionDepositPage.balanceCardBefore();
        AmountPage amountPage = actionDepositPage.topUpCard1();
        actionDepositPage = amountPage.afterAmountFirst(amountTransfer1);
        actionDepositPage.verificationBalance1(amountTransfer1);
        actionDepositPage.balanceCardBefore();
        amountPage = actionDepositPage.topUpCard2();
        actionDepositPage = amountPage.afterAmountSecond(amountTransfer2);
        actionDepositPage.verificationBalance2(amountTransfer2);
    }



    @Test
    void shouldTransferMoneyBetweenMyCardsMinusBalance() {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCode();
        ActionDepositPage actionDepositPage = verificationPage.validVerity(verificationCode);
        actionDepositPage.balanceCardBefore();
        AmountPage amountPage = actionDepositPage.topUpCard1();
        actionDepositPage = amountPage.afterAmountFirst("12000");
        actionDepositPage.verificationBalance1("12000");
  }

}
