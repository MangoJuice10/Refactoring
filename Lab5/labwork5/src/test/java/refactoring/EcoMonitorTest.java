package refactoring;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EcoMonitorTest {

    @Test
    void testModernSensor() {
        double temperatureCelcius = 25.0;
        double humidityFraction = 0.602;
        double CO2Fraction = 0.205;
        double pressureHpa = 1013.2534;
        EcoSensor modernSensor = new ModernSensor(temperatureCelcius, humidityFraction, CO2Fraction, pressureHpa);

        assertEquals(modernSensor.getTemperatureCelsius(), temperatureCelcius);
        assertEquals(modernSensor.getHumidityFraction(), humidityFraction);
        assertEquals(modernSensor.getCO2Fraction(), CO2Fraction);
        assertEquals(modernSensor.getPressureHpa(), pressureHpa);
    }

    @Test
    void testEcoMonitorModernSensor() {
        EcoSensor modernSensor = new ModernSensor(25.0, 0.602, 0.205, 1013.2534);
        EcoMonitor ecoMonitor = new EcoMonitor(modernSensor);
        String data = ecoMonitor.displayData();
        assertTrue(data.contains("25,0°C"));
        assertTrue(data.contains("0,60"));
        assertTrue(data.contains("0,21"));
        assertTrue(data.contains("1013,25 hPa"));
    }
}