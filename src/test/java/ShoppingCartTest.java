import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ShoppingCartTest {
    @Test
    void emptyCart_hasZeroItemItems() {
        ShoppingCart cart = new ShoppingCart();
        assertThat(cart.getItemCount(), is(0));
    }
}
