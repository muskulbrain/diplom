package selenideTests.tests;

import org.junit.jupiter.api.Test;
import selenideTests.common.TestBase;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class Auth extends TestBase {

    @Test
    public void authByContract() {

        open(baseUrl); //Потом добавить метод закрытия попапов
        $("[data-qa='HEADER_PROFILE']").click();
        $("[data-qa='VLOGIN_REGISTRATION_BAR_LOGIN_LINK']").click();
        $("[data-qa='SIGN_IN_BY_CONTRACT_OR_EMAIL']").click();
        $$(".sw-input__value_placeholder").first().setValue("2599649622");
        $$(".sw-input__value_placeholder").get(1).setValue("76773");
        $(".sign-in__button").click();
        sleep(1000);
        $("[data-qa='VUSERBAR_NAME']").shouldBe(visible);
        ;


    }


}
