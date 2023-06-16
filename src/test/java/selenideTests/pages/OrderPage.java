package selenideTests.pages;

import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class OrderPage {

    @Step("Переход к оформлению заказа")
    public OrderPage makeOrder() {
        sleep(1000);
        $(".order-summary__button").click();
        sleep(2000);
        $(".sw-snackbar").shouldNot(exist.because("Снэкбар на странице оформления заказа: Не удалось оформить заказ"));
        return this;
    }

    @Step("Выбор службы доставки Самовывоз")
    public OrderPage chooseSelfDeliveryMethod() {
        sleep(3000);
        if ($(".order-step-title__number.order-step-title__number_success").is(visible)) {
            $(".delivery-selector__items>.delivery-selector__link").click();
        }
        sleep(3000);
        $(".delivery-selector__items>.delivery-selector__link").click();

        $("[data-qa='pickup']").shouldBe(visible.because("Самовывоз не подгрузился"), Duration.ofSeconds(3));
        $("[data-qa='pickup']").click();
        $("[data-qa='pickup']").shouldNotBe(visible.because("Не выбран Самовывоз"));
        return this;
    }

    @Step("Выбор пункта самовывоза")
    public OrderPage setSelfDeliveryPoint() {
        $(".map-selector").shouldBe(visible);
        $(".map-selector__select-btn").shouldBe(visible.because("Нет кнопки выбора пункта самовывоза"), Duration.ofSeconds(3));
        $(".map-selector__select-btn").click();
        $(".map-selector__address-item").shouldHave(text("Выбрать")).click();
        return this;
    }

    @Step("Выбор получателя Сам")
    public OrderPage selectRecipientSelf() {
        $("[data-qa='DELIVERY_SELECTOR_TAKE_THE_ORDER']").click();
        $(".delivery-recipient-data__button").click();
        $(".delivery-selector .order-step-title__number_success").shouldBe(visible);
        return this;
    }

    @Step("Выбор оплаты Картой онлайн")
    public OrderPage choosePaymentMethodByCard() {
        sleep(2000);
        $(".payment-type__block").shouldBe(visible);
        $("[data-qa='ORDER_CARD_TITLE_EPAY']").click();
        return this;
    }

    @Step("Отменить оплату заказа на странице платежной системы")
    public OrderPage cancelPayment() {
        $(".jsBtnReset").shouldBe(visible.because("Возникла ошибка при переходе к системе оплаты"), Duration.ofSeconds(8));
        $(".jsBtnReset").click();
        return this;
    }

}
