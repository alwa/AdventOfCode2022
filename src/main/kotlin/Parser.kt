internal interface Parser<T, V> {

    fun parse(input: T): V

}