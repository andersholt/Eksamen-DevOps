package no.shoppifly;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.micrometer.core.instrument.MeterRegistry;

import java.time.Duration;
import java.util.List;

@RestController()
public class ShoppingCartController{

    private Timer checkoutTimer;
    private MeterRegistry meterRegistry;
    private final CartService cartService;
    @Autowired
    public ShoppingCartController(MeterRegistry meterRegistry, CartService cartService){
        this.meterRegistry = meterRegistry;
        checkoutTimer = meterRegistry.timer("checkout_latency");
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
        meterRegistry.counter("checkouts").increment(1);
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
        Gauge.builder("carts", cartService.getAllCarts(), List::size)
                .strongReference(true)
                .register(meterRegistry);
        Gauge.builder("cartsvalue", cartService.total(), Float::doubleValue)
                .strongReference(true)
                .register(meterRegistry);
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