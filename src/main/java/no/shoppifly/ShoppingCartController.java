package no.shoppifly;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.micrometer.core.instrument.MeterRegistry;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController()
public class ShoppingCartController{

    private final Timer checkoutTimer;
    private final MeterRegistry meterRegistry;
    private final AtomicInteger cartsValue;
    private final AtomicInteger amountOfCarts;

    private final CartService cartService;
    @Autowired
    public ShoppingCartController(MeterRegistry meterRegistry, CartService cartService){
        this.meterRegistry = meterRegistry;
        checkoutTimer = meterRegistry.timer("checkout_latency");
        cartsValue = meterRegistry.gauge("cartsvalue", new AtomicInteger(0));
        amountOfCarts = meterRegistry.gauge("carts", new AtomicInteger(0));
        this.cartService = cartService;
    }

    @GetMapping(path = "/cart/{id}")
    public Cart getCart(@PathVariable String id) {
        return cartService.getCart(id);
    }

    /**
     * Checks out a shopping cart. Removes the cart, and returns an order ID
     *
     * @return an order ID
     */
    @PostMapping(path = "/cart/checkout")
    public String checkout(@RequestBody Cart cart) {
        long startTime = System.currentTimeMillis();
        String checkout = cartService.checkout(cart);
        meterRegistry.counter("checkouts").increment();
        cartsValue.set((int) cartService.total());
        amountOfCarts.set(cartService.getAllCarts().size());
        checkoutTimer.record(Duration
                .ofMillis(System.currentTimeMillis()
                        - startTime));
        return checkout;
    }

    /**
     * Updates a shopping cart, replacing it's contents if it already exists. If no cart exists (id is null)
     * a new cart is created.
     *
     * @return the updated cart
     */
    @PostMapping(path = "/cart")
    public Cart updateCart(@RequestBody Cart cart) {
        cartsValue.set((int) cartService.total());
        amountOfCarts.set(cartService.getAllCarts().size());
        return cartService.update(cart);
    }

    /**
     * return all cart IDs
     *
     * @return
     */
    @GetMapping(path = "/carts")
    public List<String> getAllCarts() {
        return cartService.getAllCarts();
    }
}