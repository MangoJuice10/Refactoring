package refactoring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleServiceTest {

    @Test
    public void computeMaintenanceCost_variousTypes() {
        Car compact = new Car("c1", VehicleType.COMPACT, 30.0, true);
        Car suv = new Car("c2", VehicleType.SUV, 80.0, true);

        VehicleService s = new VehicleService();
        double v1 = s.computeMaintenanceCost(compact);
        double v2 = s.computeMaintenanceCost(suv);

        assertEquals(30.0 * 0.05 + 10, v1);
        assertEquals(80.0 * 0.12 + 25, v2);
    }
}