package selenideTests.tests;


import org.junit.jupiter.api.Test;
import selenideTests.common.TestBase;
import selenideTests.pages.API_Step;
import selenideTests.pages.AuthPage;
import selenideTests.pages.CartPage;
import selenideTests.pages.OrderPage;

public class Order extends TestBase {

    //Тест оформления заказа самовывозом
    @Test
    public void orderSelfDelivery() {
        OrderPage orderPage = new OrderPage();
        CartPage cartPage = new CartPage();
        AuthPage authPage = new AuthPage();
        API_Step api = new API_Step();

        openBase();

        //api.loginUserByRest();

        cartPage.goToProduct()
                .addProductToCart()
                .gotoBasket();

        orderPage.makeOrder();
        getAuthPage(authPage);

        orderPage
                .chooseSelfDeliveryMethod()
                .setSelfDeliveryPoint()
                .selectRecipientSelf()
                .choosePaymentMethodByCard()
                .makeOrder()
                .cancelPayment();
    }
}
