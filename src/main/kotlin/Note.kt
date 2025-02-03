class Note(val name: String, val text: String) {
    override fun toString(): String {
        return "{name}:\n{text}"
    }
}