sealed class Coffee(val name: String) {
    class Americano : Coffee("Американо")
    class Cappuccino(val milkAmount: Double) : Coffee("Капучино")
    class Latte : Coffee("Латте")
}