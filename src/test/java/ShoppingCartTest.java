import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ShoppingCartTest {
    @Test
    void emptyCart_hasZeroItemItems() {
        ShoppingCart cart = new ShoppingCart();
        assertThat(cart.getItemCount(), is(0));
    }

    @Test
    void addingItem_increasesItemCount() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Apple", 35.9);
        assertThat(cart.getItemCount(), is(1));
    }

    @Test
    void addingMultipleItems_increasesItemCount() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Apple", 35.9);
        cart.addItem("Banana", 20.5);
        assertThat(cart.getItemCount(), is(2));
    }

    @Test
    void emptyCart_totalZero() {
        ShoppingCart cart = new ShoppingCart();
        assertThat(cart.getTotal(), is(0.0));
    }

    @Test
    void oneItemCart_totalIsItemPrice() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Apple", 35.9);
        assertThat(cart.getTotal(), is(35.9));
    }

    @Test
    void multipleItemsCart_totalIsSumOfPrices() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Apple", 35.9);
        cart.addItem("Banana", 20.5);
        assertThat(cart.getTotal(), is(56.4));
    }

    @Test
    void emptyCart_removeItem_doesNothing() {
        ShoppingCart cart = new ShoppingCart();
        int removedItem = cart.removeItem("Apple");
        assertThat(removedItem, is(0));
    }

    @Test
    void nonExistentItem_removeItem_returnsZero() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Apple", 35.9);
        int removedItem = cart.removeItem("Orange");
        assertThat(removedItem, is(0));
    }

    @Test
    void existingItem_removeItem_returnsOne() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Apple", 35.9);
        int removedItem = cart.removeItem("Apple");
        assertThat(removedItem, is(1));
    }

    @Test
    void multipleItems_removeItem_returnsCorrectCount() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Apple", 35.9);
        cart.addItem("Banana", 20.5);
        cart.addItem("Apple", 10.0);
        int removedItem = cart.removeItem("Apple");
        assertThat(removedItem, is(2));
    }

}

