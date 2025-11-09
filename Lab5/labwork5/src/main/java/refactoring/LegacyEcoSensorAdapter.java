package refactoring;

public class LegacyEcoSensorAdapter implements EcoSensor {
    private final LegacyEcoSensor legacySensor;

    public LegacyEcoSensorAdapter(LegacyEcoSensor legacySensor) {
        this.legacySensor = legacySensor;
    }

    @Override
    public double getTemperatureCelsius() {
        double temperatureFahrenheit = legacySensor.fetchTemperatureFahrenheit();
        double temperatureCelsius = (temperatureFahrenheit - 32) * 5.0 / 9.0;
        return temperatureCelsius;
    }

    @Override
    public double getHumidityFraction() {
        int humidityPercent = legacySensor.fetchHumidityPercent();
        double humidityFraction = humidityPercent / 100.0;
        return humidityFraction;
    }

    @Override
    public double getCO2Fraction() {
        int CO2Percent = legacySensor.fetchCO2Percent();
        double CO2Fraction = CO2Percent / 100.0;
        return CO2Fraction;
    }

    @Override
    public double getPressureHpa() {
        double pressureMmHg = legacySensor.fetchPressureMmHg();
        double pressureHpa = pressureMmHg * 1.33322;
        return pressureHpa;
    }
}
