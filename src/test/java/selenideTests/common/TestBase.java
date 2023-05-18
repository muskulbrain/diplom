package selenideTests.common;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    public static void beforeAllMethod() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://kz.siberianwellness.com/kz-ru/";
    }

}
