import java.util.Scanner

class ScreenNote(private val archive: Archive) : Screen() {
    override fun display() {
        var exit = false
        while (!exit) {
            println("Заметки в архиве '${archive.name}':")
            println("0. Создать заметку")
            archive.getNotes().forEachIndexed { index, note ->
                println("${index + 1}. ${note.name}")
            }
            println("${archive.getNotes().size + 1}. Назад в список архивов")
            println("Выберите пункт:")
            val choice = readUserInput(archive.getNotes().size + 1)

            when {
                choice < 0 -> continue
                choice == 0 -> createNote()
                choice in 1..archive.getNotes().size -> viewNote(archive.getNotes()[choice - 1])
                choice == archive.getNotes().size + 1 -> exit = true
                else -> println("Неверный выбор. Попробуйте снова.")
            }
        }
    }

    private fun createNote() {
        var name: String?
        var text: String?

        // Запрашиваем название заметки до тех пор, пока не будет введено корректное значение
        do {
            println("Введите название заметки:")
            name = readLine() ?: ""
            if (name.isBlank()) {
                println("Ошибка: имя заметки не может быть пустым. Попробуйте снова.")
            }
        } while (name!!.isBlank())

        // Запрашиваем текст заметки до тех пор, пока не будет введено корректное значение
        do {
            println("Введите текст заметки:")
            text = readLine() ?: ""
            if (text.isBlank()) {
                println("Ошибка: содержание заметки не может быть пустым. Попробуйте снова.")
            }
        } while (text!!.isBlank())

        val newNote = Note(name, text)
        archive.addNote(newNote)
        println("Заметка '$name' добавлена.")
    }

    private fun viewNote(note: Note) {
        println("Заметка '${note.name}':")
        println(note.text)
        println("Нажмите Enter для возврата...")
        readLine()
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