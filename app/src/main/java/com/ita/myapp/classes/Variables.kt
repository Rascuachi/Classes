package com.ita.myapplication.classes

//class Variables {
fun main(){

    //number variables
    val age: Int = 26
    val long_number: Long = 9223372036854775807
    val temperature: Float = 27.123f
    val weight: Double = 60.4

    //String
    val gender: Char = 'I'
    val name: String = "Ivan Jimenez"

    //Boolean
    var isGreater: Boolean = false

    //Array
    val names = arrayOf("Ivan", "Max","Rascuache")

    println(age)
    println("Welcome $name, to your first Kotlin")
    println(add())
    println(product(10, 92))
    println(printArray(names))
    println(names.joinToString())

    val numbers = arrayOf(1,2,3,4,5,6,7,8,9)
    println(isEven(numbers))
    println(getDay(4))

    val person = Person("rascuache",26)
    println(person.name)
    println(person.age)
    person.displayInformation()



}

fun add(): Int {//poner el tipo de dato que va a regresar
    val x = 5
    val y = 10
    return 5
    //Return x+y//error
}

fun product(x: Int, y: Int): Int {
    return x + y
}
fun printArray(names:Array<String>){//specify the type of data 'String'
    for(name in names){
        print("Hello $name")
    }
}

fun isEven(numbers:Array<Int>){
    for (number in numbers){
        if(number % 2 == 0) {
            println("The number $number is Even")
        }else{
            println("The number $number is Odd")
        }
    }

}
fun getDay(day:Int):String{//if type
    var name = " "

    when(day){
        1 -> name = "Monday"//1 igual a name, name = monday
        2 -> name = "Tuesday"//1 igual a name, name = monday
        3 -> name = "Wednesday"//1 igual a name, name = monday
        4 -> name = "Thursday"//1 igual a name, name = monday
        5 -> name = "Friday"//1 igual a name, name = monday
        6 -> name = "Saturday"//1 igual a name, name = monday
        7 -> name = "Sunday"//1 igual a name, name = monday
        else -> name = "Incorrect Value"
    }

    return name
}
class Person(val name: String, val age:Int){

    fun displayInformation(){
        println("name: $name, Age: $age")

    }

}
//}
