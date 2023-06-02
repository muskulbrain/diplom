package selenideTests.pages;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import selenideTests.common.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;

public class UserPage extends TestBase {

    @Step("Клик в меню пользователя на вкладку Адреса Доставки")
    public UserPage clickUserAddressMenu() {
        $(".header-user-menu-items__item a[href*='/user_address/']").click();
        return this;
    }

    @Step("Добавления адреса")
    public UserPage clickCreateNewAddress() {
        $("[data-qa='PROFILE_DELIVERY_ADDRESS_ADD_ADDRESS_BUTTON']").click();
        return this;
    }

    @Step("Заполнение полей адреса")
    public UserPage fillingAddressFields() {
        $(".address-adding-form__modal-title").should(visible, text("Добавить адрес доставки"));

        $(".sw-input__value_placeholder[data-address-form-field=index]").click();
        getFocusedElement().sendKeys("010000");

        SelenideElement city = $(".sw-autocomplete-select .sw-input__value");
        city.clear();
        city.click();
        city.sendKeys("Астана");
        sleep(1000);
        $(".sw-autocomplete-select__option").click();

        $(".sw-input__value_placeholder[data-address-form-field=street]").click();
        getFocusedElement().sendKeys("Шалкыма");

        $(".sw-input__value_placeholder[data-address-form-field=house]").click();
        getFocusedElement().sendKeys("45");

        $(".sw-input__value_placeholder[data-address-form-field=apartment]").click();
        getFocusedElement().sendKeys("1");

        $("div.sw-modal__content .sw-link").click();
        $(".sw-input__value_placeholder[data-address-form-field=comment]").click();
        getFocusedElement().sendKeys("Комментарий для курьера");

        $(".sw-modal .sw-button").click();

        return this;
    }

    @Step("Проверка добавленного адреса")
    public UserPage checkAddAddress() {
        $$(".profile-delivery-address-card").findBy(text("Шалкыма")).shouldBe(visible);
        return this;
    }

    @Step("Удаление адреса")
    public UserPage deleteAddress() {
        $$(".sw-tooltip").get(2).click();
        $(".profile-delivery-address-card__tooltip").click();
        return this;
    }

    @Step("Проверка удаление адреса")
    public UserPage checkDeleteAddress() {
        $("#sw-snackbar-delete-address").shouldBe(visible);
        $(".profile-delivery-address__no-addresses").shouldHave(text("У вас пока нет сохраненных адресов"));
        return this;
    }

    @Step("Клик на Выход")
    public void clickLogout() {
        $(".header-user-menu-items__item [data-frontend-logout-reload]").click();
        $("[data-qa='VUSERBAR_NAME']").shouldNot(visible, ofSeconds(7));
    }


}
