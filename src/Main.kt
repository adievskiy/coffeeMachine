import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    val coffee = selectMenu()
    selectDrink(coffee)
}

fun selectMenu(): Coffee {
    println("Выберите кофе:\n1. Американо\n2. Капучино\n3. Латте")
    val choice = readlnOrNull()

    return when (choice) {
        "1" -> Coffee.Americano()
        "2" -> {
            val milkAmount = selectMilk()
            Coffee.Cappuccino(milkAmount)
        }
        "3" -> Coffee.Latte()
        else -> {
            println("Неверный выбор, попробуйте еще раз.")
            selectMenu()
        }
    }
}

fun selectMilk(): Double {
    println("Выберите содержание молока:\n1. 10%\n2. 20%\n3. 30%")
    val milkChoice = readlnOrNull()

    return when (milkChoice) {
        "1" -> 0.1
        "2" -> 0.2
        "3" -> 0.3
        else -> {
            println("Неверный выбор, попробуйте снова.")
            selectMilk()
        }
    }
}

fun selectDrink(menu: Coffee) {
    val sugar = selectSugar()
    val volume = selectVolume()

    println("Приготовление кофе...")

    runBlocking {
        for (progress in 10..100 step 10) {
            delay(500L)
            println("$progress%")
        }
    }

    val milkInfo = if (menu is Coffee.Cappuccino) ", молоко: ${menu.milkAmount * 100}%." else "."
    println("Ваш кофе ${menu.name} готов с $sugar ложками сахара, объем: $volume литров$milkInfo")
}

fun selectSugar(): Int? {
    println("Количество сахара (от 0 до 5):")
    val sugar = readlnOrNull()?.toIntOrNull()

    return if (sugar in 0..5) {
        sugar
    } else {
        println("Некорректный ввод. Пожалуйста, введите число от 0 до 5.")
        selectSugar()
    }
}

fun selectVolume(): Double {
    println("Выберите объем кофе:\n1. 0.2 литра\n2. 0.3 литра\n3. 0.4 литра")
    val volumeChoice = readlnOrNull()

    return when (volumeChoice) {
        "1" -> 0.2
        "2" -> 0.3
        "3" -> 0.4
        else -> {
            println("Неверный выбор, попробуйте снова.")
            selectVolume()
        }
    }
}