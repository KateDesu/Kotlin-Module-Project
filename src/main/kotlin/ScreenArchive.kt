import java.util.Scanner

class ScreenArchive(private val archives: MutableList<Archive>) : Screen() {
    override fun display() {
        while (true) {
            println("Список архивов:")
            println("0. Создать архив")

            archives.forEachIndexed { index, archive ->
                println("${index + 1}. ${archive.name}")
            }
            println("${archives.size + 1}. Выход")

            println("Выберите пункт:")
            val choice: Int = readUserInput(archives.size + 1)

            when {
                choice < 0 -> continue
                choice == 0 -> createArchive()
                choice in 1..archives.size -> {
                    val screenNote = ScreenNote(archives[choice - 1])
                    screenNote.display()
                }
                choice == archives.size + 1 -> {
                    println("Выход из программы.")
                    return
                }
            }

            if (archives.isEmpty()) {
                println("Нет доступных архивов.")
                break
            }
        }
    }

    private fun createArchive() {
        while (true) {
            println("Введите название архива:")
            val name = readLine()

            if (name.isNullOrBlank()) {
                println("Ошибка: имя архива не может быть пустым. Пожалуйста, введите корректное имя.")
            } else {
                val newArchive = Archive(name)
                archives.add(newArchive)
                println("Архив '$name' успешно создан.")
                break
            }
        }
    }

    private fun readUserInput(max: Int): Int {
        while (true) {
            val input: String = Scanner(System.`in`).nextLine()
            val digit: Int? = input.toIntOrNull()

            if (digit == null) {
                println("Ошибка: введена не цифра. Введите цифру для выбора пункта меню")
                return -1
            }
            if (digit<0 || digit > max || digit > 9) {
                println("Ошибка: пункта с таким номером не существует. Введите цифру для выбора пункта меню")
                return -1
            }

            return digit
        }
    }
}