package com.example.tiempoubicacion

import android.speech.tts.TextToSpeech

// 1. Definimos el modelo de datos
data class FechaHistorica(
    val nombre: String,
    val fecha: String, // Ejemplo: "21 de mayo"
    val descripcion: String,
    val region: String // Continental, Rapa Nui, Juan Fernández
)

// 2. Lista de efemérides optimizada para niños (6 a 12 años)
val efemeridesChile = listOf(
    FechaHistorica(
        "Combate Naval de Iquique",
        "21 de mayo",
        "¡Celebramos a los héroes del mar! Recordamos al valiente Arturo Prat que siempre cumplió con su deber.",
        "Continental"
    ),
    FechaHistorica(
        "Acuerdo de Voluntades",
        "9 de septiembre",
        "¡Día de fiesta en Rapa Nui! Celebramos cuando la isla se unió a nuestro Chile.",
        "Rapa Nui"
    ),
    FechaHistorica(
        "Primera Junta de Gobierno",
        "18 de septiembre",
        "¡Tiki tiki ti! Es el cumpleaños de Chile y lo celebramos con cuecas y volantines.",
        "Continental"
    ),
    FechaHistorica(
        "Descubrimiento de las Islas",
        "22 de noviembre",
        "¡Tierra a la vista! Recordamos el descubrimiento de las hermosas Islas Juan Fernández en medio del mar.",
        "Juan Fernández"
    )
)

// 3. Función para que el TTS lo lea con entonación amigable
fun leerFechaHistorica(efemeride: FechaHistorica, tts: TextToSpeech?) {
    val textoALeer = "El ${efemeride.fecha} es el ${efemeride.nombre}. ${efemeride.descripcion}"
    tts?.speak(textoALeer, TextToSpeech.QUEUE_FLUSH, null, null)
}
