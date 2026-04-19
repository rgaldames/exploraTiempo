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
        "La estación más calurosa, ideal para salir a la playa, piscinas o al campo",
        "Diciembre, Enero y Febrero"
    ),
    OTONO(
        "Otoño",
        OrangeSeason,
        "🍂",
        "Transición al frío",
        "Marzo, Abril y Mayo"
    ),
    INVIERNO(
        "Invierno",
        BluePrimary,
        "❄️",
        "La estación más fría y lluviosa",
        "Junio, Julio y Agosto"
    ),
    PRIMAVERA(
        "Primavera",
        GreenSuccess,
        "🌸",
        "Florecimiento y temperaturas agradables",
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
        fechasHistoricas = "El 1 de enero celebramos el Año Nuevo."
    ),
    MesInfo(
        "Febrero", 28, Estacion.VERANO, 2,
        fechasHistoricas = "En Rapa Nui se celebra la gran fiesta de la Tapati durante este mes. " +
                "Haka Pei: Es la prueba más extrema. Los hombres se lanzan desde la cima del cerro Maunga sobre troncos de plátanos unidos entre sí, alcanzando velocidades de hasta 80 kilómetros por hora. " +
                "Taua Rapa Nui la Triatlón: Se realiza en el volcán Rano Raraku. Consiste en remar en canoas de totora, correr alrededor del lago cargando pesados racimos de plátanos al hombro y nadar sobre un flotador de totora. " +
                "Takona: Competencia de pintura corporal. Usan pigmentos naturales, kie a, y cada diseño tiene un significado histórico o familiar. " +
                "Canto y Danza: Por las noches, en el escenario principal frente al mar, Hanga Roa, los clanes presentan coreografías masivas y cantos tradicionales ante un jurado."
    ),
    MesInfo(
        "Marzo", 31, Estacion.OTONO, 3,
        fechasHistoricas = "Es el mes del regreso a clases y el inicio del otoño."+
                "El 11 de Marzo de 1990 Augusto Pinochet entregó el poder a Patricio Aylwin, el primer presidente elegido democráticamente tras 17 años "+
                "de dictadura militar.  Desde entonces, cada 4 años, el 11 de marzo es la fecha oficial en la que se realiza el Cambio de Mando Presidencial "+
                "en el Congreso Nacional en Valparaíso.  El 3 de Marzo de 1985 A las 19:47 horas, un violento terremoto (magnitud 8.0) afectó a la zona central "+
                "de Chile, con epicentro cerca de San Antonio. Hubo graves daños en Santiago, Valparaíso y comunas rurales."
    ),
    MesInfo(
        "Abril", 30, Estacion.OTONO, 4,
        fechasHistoricas = "El 5 de abril se conmemora la Batalla de Maipú."+
                "Aunque la independencia se había firmado simbólicamente meses antes, el 12 de febrero, el ejército español (Realista) todavía "+
                "representaba una amenaza real y estaba intentando reconquistar Santiago. Con la victoria en los llanos de Maipú, el ejército "+
                "patriota logró destruir al grueso de las fuerzas españolas, asegurando que Chile no volvería a ser una colonia de España." +
                "El Abrazo de Maipú: Este es el momento más icónico de la jornada. El General argentino José de San Martín lideró la batalla. "+
                "El General chileno Bernardo O'Higgins, que estaba herido tras una batalla anterior (Cancha Rayada), llegó al campo de batalla "+
                "cuando la victoria ya era segura."
    ),
    MesInfo(
        "Mayo", 31, Estacion.OTONO, 5,
        fechasHistoricas = "El 21 de mayo celebramos el Combate Naval de Iquique en todo Chile."+
                "Es una de las fechas patrias más importantes del país, ya que se conmemoran dos "+
                "combates navales ocurridos el 21 de mayo de 1879 durante la Guerra del Pacífico "+
                "(el conflicto de Chile contra Perú y Bolivia)."+
                "El 21 de mayo es el día en que Chile rinde homenaje al valor, "+
                "el sentido del deber y el sacrificio de Arturo Prat y sus hombres."
    ),
    MesInfo(
        "Junio", 30, Estacion.INVIERNO, 6,
        fechasHistoricas = "El 21 de junio es el Día Nacional de los Pueblos Indígenas, el solsticio de invierno y el Asalto y Toma del Morro de Arica."+
                "Las principales celebraciones: "+
                "We Tripantu, Pueblo Mapuche: Significa \"Nueva salida del Sol\". Durante la noche más larga, las familias se reúnen, se cuentan historias, epew, y antes del amanecer, se bañan en ríos o esteros para purificar el cuerpo y el espíritu. Reciben al sol con rogativas, nguillatún, para pedir buenas cosechas y salud.\n" +
                "Machaq Mara, Pueblo Aymara: Es el Año Nuevo Andino. Se celebra principalmente en el norte de Chile. La gente sube a lugares altos antes del amanecer para recibir los primeros rayos del sol con las manos extendidas, realizando ofrendas a la Pachamama, Madre Tierra, y al Inti, Sol.\n" +
                "Inti Raymi, Pueblo Quechua: La \"Fiesta del Sol\". Es una ceremonia de agradecimiento por las cosechas anteriores y una petición de protección para las que vienen.\n" +
                "Aringa Ora o Koro, Pueblo Rapa Nui: Una festividad que celebra el ciclo de la vida, relacionada con la fertilidad y la productividad de los recursos."+
                "El 7 de junio de 1880, las fuerzas chilenas asaltaron el Morro de Arica, que era el último baluarte de defensa de las tropas peruanas en esa zona."+
                "La toma y salto del morro de Arica es una de las gestas militares más recordadas de la Guerra del Pacífico."+
                "Para el Ejército de Chile, el 7 de junio es el Día de las Glorias de la Infantería, en honor al valor demostrado por los soldados que subieron el Morro a pie bajo fuego enemigo."
    ),
    MesInfo(
        "Julio", 31, Estacion.INVIERNO, 7,
        fechasHistoricas = "El 16 de julio es el día de la Virgen del Carmen, Reina y Patrona de Chile. " +
                "El Ejército de los Andes: En 1817 el General José de San Martín y el General Bernardo O'Higgins "+
                "la nombraron oficialmente Patrona y Generalísima de las Armas de Chile. Antes de cruzar la "+
                "cordillera, San Martín le entregó su bastón de mando a la Virgen, pidiendo su protección "+
                "para la travesía y la liberación de los pueblos.  "+
                "La Batalla de Maipú (1818): En un momento crítico, antes de la batalla definitiva por la "+
                "independencia, el pueblo de Santiago y las autoridades se reunieron en la Catedral para hacer "+
                "un voto solemne: si se ganaba la batalla y se consolidaba la libertad, se construiría un templo "+
                "en su honor en el lugar donde se lograra la victoria."
    ),
    MesInfo(
        "Agosto", 31, Estacion.INVIERNO, 8,
        fechasHistoricas = "El 20 de agosto se celebra el Natalicio de Bernardo O'Higgins."+"Aunque nació en Chillán,"+
                " hoy el lugar exacto de su nacimiento se encuentra en la comuna de Chillán Viejo. Esto se debe a que, "+
                "tras el terremoto de 1835, la ciudad de Chillán fue refundada en una nueva ubicación cercana, y el "+
                "lugar original pasó a llamarse Chillán Viejo."
    ),
    MesInfo(
        "Septiembre", 30, Estacion.PRIMAVERA, 9,
        fechasHistoricas = "¡Mes de la Patria! El 18 celebramos la Primera Junta de Gobierno. También, el 9 es el aniversario de la anexión de Rapa Nui a Chile."
    ),
    MesInfo(
        "Octubre", 31, Estacion.PRIMAVERA, 10,
        fechasHistoricas = "El 12 de octubre de 1492, la expedición española liderada por Colón llegó a la isla "+
                "de Guanahani (en las Bahamas). Esto marcó el primer contacto documentado entre Europa y América. "+
                "A partir de ese momento, dos civilizaciones que habían crecido de forma totalmente aislada "+
                "la una de la otra se encontraron, cambiando la historia de la humanidad para siempre"
    ),
    MesInfo(
        "Noviembre", 30, Estacion.PRIMAVERA, 11,
        fechasHistoricas = "El 22 de noviembre se recuerda el descubrimiento de las Islas Juan Fernández."
    ),
    MesInfo(
        "Diciembre", 31, Estacion.VERANO, 12,
        fechasHistoricas = "El 25 de diciembre celebramos el nacimiento de nuestro señor y salvador Jesucristo de Nazaret."
    )
)

val DIAS_SEMANA = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
