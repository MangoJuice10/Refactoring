package refactoring;

// REFACTORING: Inline Class (Класс TelephoneNumber имеет слишком мало
// обязанностей, и в будущем не предполагается добавлять для него новые
// обязанности, поэтому следует произвести встраивание класса TelephoneNumber в
// класс User)
public class TelephoneNumber {
    private String number;

    public TelephoneNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}