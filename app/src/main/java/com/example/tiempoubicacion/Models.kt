package com.example.tiempoubicacion

import androidx.compose.ui.graphics.Color
import com.example.tiempoubicacion.ui.theme.*

enum class Estacion(
    val nombre: String,
    val color: Color,
    val icono: String,
    val descripcion: String,
    val mesesCompletos: String
) {
    VERANO(
        "Verano",
        YellowSeason,
        "☀️",
        "¡Es tiempo de vacaciones, playa y mucho sol! La estación más divertida para jugar afuera.",
        "Diciembre, Enero y Febrero"
    ),
    OTONO(
        "Otoño",
        OrangeSeason,
        "🍂",
        "¡Las hojas de los árboles cambian a colores mágicos y caen al suelo! El aire se pone fresquito.",
        "Marzo, Abril y Mayo"
    ),
    INVIERNO(
        "Invierno",
        BluePrimary,
        "❄️",
        "¡Abrígate bien! Es tiempo de lluvia, bufandas y un rico chocolate caliente.",
        "Junio, Julio y Agosto"
    ),
    PRIMAVERA(
        "Primavera",
        GreenSuccess,
        "🌸",
        "¡Todo florece y las mariposas vuelven a volar! El clima es perfecto para jugar en el parque.",
        "Septiembre, Octubre y Noviembre"
    )
}

data class MesInfo(
    val nombre: String,
    val dias: Int,
    val estacion: Estacion,
    val orden: Int,
    val transicion: String = "",
    val fechasHistoricas: String = ""
)

val MESES = listOf(
    MesInfo(
        "Enero", 31, Estacion.VERANO, 1,
        fechasHistoricas = "¡Feliz Año Nuevo! Empezamos un calendario nuevecito lleno de aventuras y vacaciones de verano."
    ),
    MesInfo(
        "Febrero", 28, Estacion.VERANO, 2,
        fechasHistoricas = "¡En Rapa Nui celebran la gran fiesta de la Tapati! Los valientes se lanzan en troncos de plátanos y todos bailan felices."
    ),
    MesInfo(
        "Marzo", 31, Estacion.OTONO, 3,
        fechasHistoricas = "¡Es hora de volver al colegio y ver a todos tus amigos y amigas! También elegimos a quienes cuidan nuestro país."
    ),
    MesInfo(
        "Abril", 30, Estacion.OTONO, 4,
        fechasHistoricas = "¡Celebramos el gran Abrazo de Maipú! O'Higgins y San Martín se unieron para que Chile fuera un país libre."
    ),
    MesInfo(
        "Mayo", 31, Estacion.OTONO, 5,
        fechasHistoricas = "¡Celebramos a los héroes del mar! Recordamos al valiente Arturo Prat que siempre cumplió con su deber."
    ),
    MesInfo(
        "Junio", 30, Estacion.INVIERNO, 6,
        fechasHistoricas = "¡Es el Año Nuevo de nuestros pueblos indígenas! Celebran que el sol vuelve a nacer en la noche más larga del año."
    ),
    MesInfo(
        "Julio", 31, Estacion.INVIERNO, 7,
        fechasHistoricas = "¡Es el mes de las vacaciones de invierno! También celebramos a la Virgen del Carmen, que protege a todo nuestro Chile."
    ),
    MesInfo(
        "Agosto", 31, Estacion.INVIERNO, 8,
        fechasHistoricas = "¡Feliz cumpleaños a Bernardo O'Higgins! Es el padre de nuestra patria y nació en la histórica ciudad de Chillán."
    ),
    MesInfo(
        "Septiembre", 30, Estacion.PRIMAVERA, 9,
        fechasHistoricas = "¡Tiki tiki ti! Celebramos el cumpleaños de Chile con cuecas, volantines y mucha alegría en familia."
    ),
    MesInfo(
        "Octubre", 31, Estacion.PRIMAVERA, 10,
        fechasHistoricas = "¡Dos mundos se conocieron! Hace mucho tiempo, Colón junto a otros exploradores, llegaron a América y la historia cambió para siempre."
    ),
    MesInfo(
        "Noviembre", 30, Estacion.PRIMAVERA, 11,
        fechasHistoricas = "¡Tierra a la vista! Recordamos el descubrimiento de las hermosas Islas Juan Fernández en medio del mar."
    ),
    MesInfo(
        "Diciembre", 31, Estacion.VERANO, 12,
        fechasHistoricas = "¡Llegó la Navidad! Es tiempo de compartir amor, regalos y celebrar el nacimiento de nuestro salvador Jesucristo de Nazaret"
    )
)

val DIAS_SEMANA = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
