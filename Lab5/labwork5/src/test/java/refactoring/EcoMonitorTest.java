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
        EcoSensor modernSensor = new ModernEcoSensor(temperatureCelcius, humidityFraction, CO2Fraction, pressureHpa);

        assertEquals(modernSensor.getTemperatureCelsius(), temperatureCelcius);
        assertEquals(modernSensor.getHumidityFraction(), humidityFraction);
        assertEquals(modernSensor.getCO2Fraction(), CO2Fraction);
        assertEquals(modernSensor.getPressureHpa(), pressureHpa);
    }

    @Test
    void testLegacySensor() {
        double temperatureFahrenheit = 76.0;
        int humidityPercent = 53;
        int CO2Percent = 12;
        double pressureMmHg = 850.2534;
        LegacyEcoSensor legacySensor = new LegacyEcoSensor(temperatureFahrenheit, humidityPercent, CO2Percent, pressureMmHg);

        assertEquals(legacySensor.fetchTemperatureFahrenheit(), temperatureFahrenheit);
        assertEquals(legacySensor.fetchHumidityPercent(), humidityPercent);
        assertEquals(legacySensor.fetchCO2Percent(), CO2Percent);
        assertEquals(legacySensor.fetchPressureMmHg(), pressureMmHg);
    }

    @Test
    void testEcoMonitorModernEcoSensor() {
        EcoSensor modernSensor = new ModernEcoSensor(25.0, 0.602, 0.205, 1013.2534);
        EcoMonitor ecoMonitor = new EcoMonitor(modernSensor);
        String data = ecoMonitor.displayData();
        assertTrue(data.contains("25,0°C"));
        assertTrue(data.contains("0,60"));
        assertTrue(data.contains("0,21"));
        assertTrue(data.contains("1013,25 hPa"));
    }

    @Test
    void testEcoMonitorLegacyEcoSensorAdapter() {
        LegacyEcoSensor legacySensor = new LegacyEcoSensor(76.0, 53, 12, 850.2534);
        EcoSensor legacySensorAdapter = new LegacyEcoSensorAdapter(legacySensor);
        EcoMonitor ecoMonitor = new EcoMonitor(legacySensorAdapter);
        String data = ecoMonitor.displayData();
        assertTrue(data.contains("24,4°C"));
        assertTrue(data.contains("0,53"));
        assertTrue(data.contains("0,12"));
        assertTrue(data.contains("1133,57 hPa"));
    }
}