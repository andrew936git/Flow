import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.random.nextInt

suspend fun main(): Unit = coroutineScope {
    val persons = mutableListOf<Person>()
    var phones = mutableListOf<String>()
    val personInfo = mutableListOf<String>()

    getPersonsFlow().collect{
        persons.add(it)
    }

    getPhoneFlow(persons.size).collect{
        phones.add(it.toString())
        phones = phones[0].split(',','[',']').toMutableList()
        phones.removeFirst()
        phones.removeLast()
    }

    for (i in phones.indices){
        personInfo.add("${persons[i]}, ${phones[i]}")
    }
    personInfo.forEach { println(it) }


}
val personsList = listOf<Person>(
    Person("Иван", "инженер"),
    Person("Петр", "программист"),
    Person("Анастасия", "бухгалтер"),
    Person("Алексей", "менеджер")
)

fun getPhoneFlow(num: Int) = flow{
    val phones = mutableListOf<String>()
    val random = Random
    val number = random.nextInt(1000000..9999999)
    for (i in 0..<num){
        val phone = "+7917$number"
        phones.add(phone)

    }
    emit(phones)
}

fun getPersonsFlow() = personsList.asFlow()