package refactoring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceCalculatorTest {

    @Test
    public void calculateFinalPrice_vipTruckLongTerm() {
        Car car = new Car("C1", VehicleType.TRUCK, 80.0, true);
        Customer cust = new Customer("U1", "VIP_Alex", 200, "a@b.com", null);
        Reservation r = new Reservation("R1", car, cust, 40, false, 0L, 0L);

        PriceCalculator calc = new PriceCalculator();
        double price = calc.calculateFinalPrice(r);
        // manual calc: base 80*40=3200; days>30 and TRUCK => 20% discount => 2560;
        // VIP branch would not apply because days>30 handled first; tax 15% -> 2944; loyalty >=100 so +10 -> 2954
        assertEquals(2954.0, price);
    }
}
