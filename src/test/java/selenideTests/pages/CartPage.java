package selenideTests.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import selenideTests.common.TestBase;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class CartPage extends TestBase {
    @Step("Переход на страницу товара")
    public CartPage goToProduct() {
        open(baseUrl + "/kz-ru/pr/novomin-500020/");
        return this;
    }

    @Step("Добавление товара в корзину")
    public CartPage addProductToCart() {
        $(".cart-button__cart").click();
        sleep(5000);
        return this;
    }

    @Step("Проверка отображения каунтера товара")
    public CartPage checkCounterProduct() {
        $(".cart-bar__counter");
        return this;
    }

    @Step("Переход в корзину")
    public CartPage gotoBasket() {
        sleep(3000);
        $(".header__cart").click();
        $(".sw-workSpace-backdrop__modal a[href*='/cart']").click();
        return this;
    }

    @Step("Удаление товара из корзины")
    public CartPage deleteProduct() {
        $(".cart-product__buttons-desktop .cart-product__options-item-delete-bug").click();
        return this;
    }

    @Step("Проверка удаления товара из корзины")
    public CartPage checkDeleteProduct() {
        $(".cart-product").shouldNotBe(Condition.visible);
        $(".cart-sheet-content__empty-cart-header").shouldHave(Condition.text("Ваша корзина пуста"));
        return this;
    }


}
