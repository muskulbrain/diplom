package selenideTests.tests;

import org.junit.jupiter.api.Test;
import selenideTests.common.TestBase;
import selenideTests.pages.API_Step;
import selenideTests.pages.CartPage;

public class Cart extends TestBase {


    //Тест добавления товара в корзину и проверка его наличия
    @Test
    public void addProductInBasket() {
        CartPage cartPage = new CartPage();

        openBase();

        cartPage.goToProduct()
                .addProductToCart()
                .checkCounterProduct();
    }

    //Тест удаления товара и проверка, что корзина пуста
    @Test
    public void IncreaseProductInCart() {
        CartPage cartPage = new CartPage();

        openBase();
        API_Step.addProductAPI();
        setToken();

        cartPage
                .gotoBasket()
                .deleteProduct()
                .checkDeleteProduct();
    }


}
