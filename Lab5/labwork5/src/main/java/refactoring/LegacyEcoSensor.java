package refactoring;

public class LegacyEcoSensor {
    private final double temperatureFahrenheit;
    private final int humidityPercent;
    private final int CO2Percent;
    private final double pressureMmHg;

    public LegacyEcoSensor(double temperatureFahrenheit, int humidityPercent, int CO2Percent, double pressureMmHg) {
        this.temperatureFahrenheit = temperatureFahrenheit;
        this.humidityPercent = humidityPercent;
        this.CO2Percent = CO2Percent;
        this.pressureMmHg = pressureMmHg;
    }

    public double fetchTemperatureFahrenheit() {
        return temperatureFahrenheit;
    }

    public int fetchHumidityPercent() {
        return humidityPercent;
    }

    public int fetchCO2Percent() {
        return CO2Percent;
    } 

    public double fetchPressureMmHg() {
        return pressureMmHg;
    }
}
