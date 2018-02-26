package kioli.koro.presentation

class DataFactory {
    companion object Factory {
        fun randomString(): String = java.util.UUID.randomUUID().toString()
    }
}