package refactoring;

public class EcoMonitor {
    private EcoSensor sensor;

    public EcoMonitor(EcoSensor sensor) {
        this.sensor = sensor;
    }

    public void setSensor(EcoSensor sensor) {
        this.sensor = sensor;
    }

    public String displayData() {
        return String.format(
                "Temperature: %.1f°C | Humidity: %.2f | CO2: %.2f | Pressure: %.2f hPa",
                sensor.getTemperatureCelsius(),
                sensor.getHumidityFraction(),
                sensor.getCO2Fraction(),
                sensor.getPressureHpa());
    };

    public static void main(String[] args) {
        // ModernEcoSensor имплементирует интерфейс EcoSensor
        EcoSensor modernSensor = new ModernEcoSensor(25.0, 0.6, 0.2, 1013.25);
        EcoMonitor ecoMonitor = new EcoMonitor(modernSensor);
        System.out.printf("Показания нового эко-сенсора: %s\n", ecoMonitor.displayData());

        // LegacyEcoSensor не имплементирует интерфейс EcoSensor
    }
}
