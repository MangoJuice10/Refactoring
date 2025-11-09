package refactoring;

public class ModernSensor implements EcoSensor {
    private final double temperatureCelcius;
    private final double humidityFraction;
    private final double CO2Fraction;
    private final double pressureHpa;

    public ModernSensor(double temperatureCelcius, double humidityFraction, double CO2Fraction, double pressureHpa) {
        this.temperatureCelcius = temperatureCelcius;
        this.humidityFraction = humidityFraction;
        this.CO2Fraction = CO2Fraction;
        this.pressureHpa = pressureHpa;
    }

    @Override
    public double getTemperatureCelsius() {
        return temperatureCelcius;
    }

    @Override
    public double getHumidityFraction() {
        return humidityFraction;
    }

    @Override
    public double getCO2Fraction() {
        return CO2Fraction;
    }

    @Override
    public double getPressureHpa() {
        return pressureHpa;
    }
}