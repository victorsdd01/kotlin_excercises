package com.victorsdd.androidmaster.my_app.playgrounds

/*
first excercise---

val morningNotification = 51
val eveningNotification = 135

printNotificationSummary(morningNotification)
printNotificationSummary(eveningNotification)

second excercise
val child = 5
val adult = 28
val senior = 87

val isMonday = true

println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")

third excercise :
Celsius to Fahrenheit: ° F = 9/5 (° C) + 32
Kelvin to Celsius: ° C = K - 273.15
Fahrenheit to Kelvin: K = 5/9 (° F - 32) + 273.15

*/



fun main(){
    val playgrounds : Playgrounds = Playgrounds()

    val song: Song = Song("Escape from LA", "The weeknd", 2020,)

    /*
    val initialMeasurement: Double = 100.50
    playgrounds.temperatureConverter(
        initialMeasurement,
        "Fahrenheit",
        "Kelvin",
        playgrounds::FahrenheitToKelvin
    )
    */
}

class Playgrounds {

    fun mobileNotifications(numberOfMessages: Int) : String = when(numberOfMessages){
        in 1..99 -> println("you have $numberOfMessages").toString()
        else -> println("Your phone is blowing up! You have 99+ notifications.").toString()
    }

    fun ticketPrice(age: Int, isMonday: Boolean): Int {
        return when(age){
            in 1..12 -> 15
            in 13..60 -> if (isMonday) 5 else 30
            in 61 .. 100 -> 20
            else -> -1
        }
    }

    fun temperatureConverter(
        initialMeasurement: Double, // 60
        initialUnit: String, // celcius
        finalUnit: String, //farenheit
        conversionFormula: (Double) -> Double
    )  {
        val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement))
        println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
    }

    fun celciusToFarenheit (celcius: Double): Double {
        return (celcius * 9/5) + 32
    }

    fun kelvinToCelcius(kelvin: Double) : Double {
        return kelvin - 273.15
    }

    fun FahrenheitToKelvin(f:Double) : Double {
        return ( f - 32 ) * 5/9 + 273.15
    }
}

/*
 Imagine that you need to create a music-player app.

Create a class that can represent the structure of a song. The Song class must include these code elements:

Properties for the title, artist, year published, and play count
A property that indicates whether the song is popular. If the play count is less than 1,000, consider it unpopular.
A method that prints a song description in this format:
"[Title], performed by [artist], was released in [year published]."
 */

class Song(
    private val title: String,
    private val artist: String,
    private val yearPublished: Int
) {

    private var playCount : Int = 0

    fun showSongInfo() = println("$title, performed by $artist, was released in $yearPublished.")

    fun increasePlayCount() = println(playCount++)

}
