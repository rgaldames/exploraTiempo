package com.rgs.tiempoubicacion

data class FechaHistorica(
    val dia: Int,
    val mes: Int,
    val evento: String,
    val descripcionEducativa: String
)

val efemeridesChile = listOf(
    FechaHistorica(12, 2, "Fundación de Santiago", "En 1541, Pedro de Valdivia fundó la capital de nuestro país."),
    FechaHistorica(21, 5, "Combate Naval de Iquique", "El Capitán Arturo Prat luchó con mucha valentía en el mar."),
    FechaHistorica(18, 9, "Primera Junta de Gobierno", "¡Es el cumpleaños de Chile! Celebramos que somos un país libre."),
    FechaHistorica(19, 9, "Glorias del Ejército", "Un gran desfile para honrar a quienes cuidan nuestra patria.")
)

fun leerFechaHistorica(fecha: FechaHistorica): String {
    return "El ${fecha.dia} del mes ${fecha.mes} recordamos: ${fecha.evento}. ${fecha.descripcionEducativa}"
}
