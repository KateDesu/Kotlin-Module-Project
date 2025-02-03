import java.util.Scanner

fun main(args: Array<String>) {

    val archives = mutableListOf<Archive>()
    val screenArchive = ScreenArchive(archives)

    // Запуск приложения
    screenArchive.display()
}