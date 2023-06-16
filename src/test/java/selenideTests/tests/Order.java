package selenideTests.tests;


import io.qameta.allure.Feature;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import selenideTests.common.TestBase;
import selenideTests.pages.CartPage;
import selenideTests.pages.OrderPage;

@Feature("Оформление заказа")
@Tag("Order")
@Tag("All")
public class Order extends TestBase {

    //Тест оформления заказа самовывозом
    @Disabled
    @Test
    public void orderSelfDelivery() {
        OrderPage orderPage = new OrderPage();
        CartPage cartPage = new CartPage();

        openBase();

        loginUserByRest();
        addProductByRest();

        cartPage.gotoBasket();
        orderPage.makeOrder();

        orderPage
                .chooseSelfDeliveryMethod()
                .setSelfDeliveryPoint()
                .selectRecipientSelf()
                .choosePaymentMethodByCard()
                .makeOrder()
                .cancelPayment();
    }
}
