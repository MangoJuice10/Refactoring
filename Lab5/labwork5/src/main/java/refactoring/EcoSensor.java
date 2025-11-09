package refactoring;

public interface EcoSensor {
    // Измеряем температуру в градусах Цельсия
    double getTemperatureCelsius();
    // Измеряем влажность как долю
    double getHumidityFraction();
    // Измеряем содержание в воздухе углекислого газа с помощью единицы измерения PPM - Parts Per Million
    double getCO2Fraction();
    // Измеряем давление в гектапаскалях
    double getPressureHpa();
}
