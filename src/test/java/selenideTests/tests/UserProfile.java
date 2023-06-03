package selenideTests.tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import selenideTests.common.TestBase;
import selenideTests.pages.UserPage;

@Feature("Личный кабинет")
@Tag("User")
public class UserProfile extends TestBase {

    //Добавление адреса доставки в ЛК

    @Test
    public void addNewAddress() {
        UserPage userPage = new UserPage();

        openBase();

        loginUserByRest();

        clickUserBar();

        userPage
                .clickUserAddressMenu()
                .clickCreateNewAddress()
                .fillingAddressFields()
                .checkAddAddress()
                .deleteAddress()
                .checkDeleteAddress();
    }

    //Выход из аккаунта в ЛК
    @Test
    public void logoutFromUserMenu() {
        UserPage userPage = new UserPage();

        openBase();
        loginUserByRest();
        clickUserBar();

        userPage
                .clickLogout();

    }
}
