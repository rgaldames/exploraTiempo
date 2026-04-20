package com.rgs.tiempoubicacion

import androidx.compose.ui.graphics.Color

// Paleta de Colores Educativos
val BluePrimary = Color(0xFF1976D2)
val BlueSecondary = Color(0xFFBBDEFB)
val LightBackground = Color(0xFFF5F5F5)
val TextMain = Color(0xFF212121)
val TextSecondary = Color(0xFF757575)

// Colores para los días
val LunesColor = Color(0xFFE57373)      // Rojo suave
val MartesColor = Color(0xFFFFB74D)     // Naranja
val MiercolesColor = Color(0xFFFFF176)  // Amarillo
val JuevesColor = Color(0xFF81C784)     // Verde
val ViernesColor = Color(0xFF64B5F6)     // Azul
val SabadoColor = Color(0xFF9575CD)     // Morado
val DomingoColor = Color(0xFFF06292)    // Rosa

enum class Estacion(
    val nombre: String,
    val icono: String,
    val color: Color,
    val descripcion: String,
    val mesesCompletos: String
) {
    VERANO(
        "Verano", "☀️", Color(0xFFFFD54F),
        "¡Es tiempo de calor y vacaciones! El sol brilla fuerte y los días son muy largos.",
        "Enero y Febrero"
    ),
    OTONO(
        "Otoño", "🍂", Color(0xFFFF8A65),
        "Las hojas de los árboles cambian a colores café y amarillo y comienzan a caer.",
        "Abril y Mayo"
    ),
    INVIERNO(
        "Invierno", "❄️", Color(0xFF4FC3F7),
        "Hace más frío, llueve y en algunos lugares puede caer nieve. ¡A abrigarse!",
        "Julio y Agosto"
    ),
    PRIMAVERA(
        "Primavera", "🌸", Color(0xFF81C784),
        "¡Todo florece! Nacen las flores, los pajaritos cantan y los árboles se ponen verdes.",
        "Octubre y Noviembre"
    )
}

data class Mes(
    val orden: Int,
    val nombre: String,
    val dias: Int,
    val estacion: Estacion,
    val transicion: String = "",
    val fechasHistoricas: String = ""
)

val MESES = listOf(
    Mes(1, "Enero", 31, Estacion.VERANO, "", "1 de Enero: ¡Año Nuevo! Celebramos el comienzo de un nuevo ciclo con alegría."),
    Mes(2, "Febrero", 28, Estacion.VERANO, "", "14 de Febrero: Día del Amor y la Amistad. 12 de Febrero: Fundación de Santiago de Chile."),
    Mes(3, "Marzo", 31, Estacion.OTONO, "Comienza el Otoño (21)", "Es el mes de volver a clases para ver a nuestros amigos y aprender cosas nuevas."),
    Mes(4, "Abril", 30, Estacion.OTONO, "", "En este mes celebramos la Pascua de Resurrección con huevitos de chocolate."),
    Mes(5, "Mayo", 31, Estacion.OTONO, "", "21 de Mayo: Combate Naval de Iquique. Celebramos a las mamás en su día especial."),
    Mes(6, "Junio", 30, Estacion.INVIERNO, "Comienza el Invierno (21)", "¡Llega el invierno! Es el mes con el día más cortito del año."),
    Mes(7, "Julio", 31, Estacion.INVIERNO, "", "Es tiempo de vacaciones de invierno para descansar y jugar en casa."),
    Mes(8, "Agosto", 31, Estacion.INVIERNO, "", "Celebramos el Día del Niño. 20 de Agosto: Nacimiento de Bernardo O'Higgins."),
    Mes(9, "Septiembre", 30, Estacion.PRIMAVERA, "Comienza la Primavera (21)", "18 de Septiembre: ¡Cumpleaños de Chile! Bailamos cueca y elevamos volantines."),
    Mes(10, "Octubre", 31, Estacion.PRIMAVERA, "", "12 de Octubre: Encuentro de Dos Mundos. Las flores llenan de colores los jardines."),
    Mes(11, "Noviembre", 30, Estacion.PRIMAVERA, "", "1 de Noviembre: Día de Todos los Santos. El clima se vuelve más cálido."),
    Mes(12, "Diciembre", 31, Estacion.VERANO, "Comienza el Verano (21)", "25 de Diciembre: ¡Navidad! Tiempo de unión familiar y de esperar al Viejito Pascuero.")
)
