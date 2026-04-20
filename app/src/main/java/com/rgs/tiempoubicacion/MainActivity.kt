package com.rgs.tiempoubicacion

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.rgs.tiempoubicacion.ui.theme.*
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private val _isTtsReady = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Inicialización centralizada a través de AdManager
        AdManager.initialize(this)
        
        tts = TextToSpeech(this, this)
        enableEdgeToEdge()
        setContent {
            TiempoUbicacionTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text("Mi Tiempo y Calendario", fontWeight = FontWeight.Bold) },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = BluePrimary,
                                titleContentColor = Color.White
                            )
                        )
                    }
                ) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        onSpeak = { text -> speak(text) }
                    )
                }
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.forLanguageTag("es"))
            if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                _isTtsReady.value = true
            }
        }
    }

    private fun speak(text: String) {
        if (_isTtsReady.value) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroy()
    }
}

@Composable
fun AdBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.BANNER)
                    // Uso del ID dinámico desde AdManager
                    adUnitId = AdManager.getBannerAdUnitId()
                    loadAd(AdManager.createAdRequest())
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier, onSpeak: (String) -> Unit) {
    var currentTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Días", "Meses", "Estaciones", "Año")

    Column(modifier = modifier.fillMaxSize().background(LightBackground)) {
        AdBanner()
        SecondaryTabRow(
            selectedTabIndex = currentTab,
            containerColor = BlueSecondary,
            contentColor = BluePrimary
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = currentTab == index,
                    onClick = { 
                        currentTab = index
                        val speechText = when (index) {
                            0 -> "Los dias de la semana son 7"
                            1 -> "Los meses del año son 12"
                            2 -> "Cada año tiene 4 estaciones: Verano, Otoño, Invierno y Primavera"
                            3 -> "Un año tiene 4 estaciones, 12 meses, y 365 dias. Si el año es bisiesto Febrero tendrá 29 días y el año tendrá 366 días."
                            else -> title
                        }
                        onSpeak(speechText)
                    },
                    text = { 
                        Text(
                            text = title, 
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            maxLines = 1,
                            softWrap = false
                        ) 
                    }
                )
            }
        }

        Box(modifier = Modifier.weight(1f)) {
            when (currentTab) {
                0 -> DiasSeccion(onSpeak)
                1 -> MesesSeccion(onSpeak)
                2 -> EstacionesSeccion(onSpeak)
                3 -> AnoSeccion(onSpeak)
            }
        }
    }
}

@Composable
fun StatRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.White.copy(alpha = 0.8f), fontSize = 18.sp)
        Text(value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}

@Composable
fun InfoCard(titulo: String, contenido: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = BlueSecondary.copy(alpha = 0.1f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(titulo, fontWeight = FontWeight.Bold, color = BluePrimary, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(contenido, color = TextMain, fontSize = 14.sp)
        }
    }
}

@Composable
fun DiasSeccion(onSpeak: (String) -> Unit) {
    val dias = listOf(
        "Lunes" to LunesColor,
        "Martes" to MartesColor,
        "Miércoles" to MiercolesColor,
        "Jueves" to JuevesColor,
        "Viernes" to ViernesColor,
        "Sábado" to SabadoColor,
        "Domingo" to DomingoColor
    )

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text("Días de la Semana", style = MaterialTheme.typography.headlineMedium, color = TextMain)
            Text("¡Aprendamos los días! Cada uno tiene su propio color.", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(dias) { (nombre, color) ->
            BotonEducativo3D(
                onClick = { onSpeak(nombre) },
                baseColor = color,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(64.dp)
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = nombre,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
            InfoCard(
                titulo = "¿Sabías que?",
                contenido = "El Lunes es el primer día de la escuela, y el Domingo es el último día de la semana."
            )
        }
    }
}

@Composable
fun MesesSeccion(onSpeak: (String) -> Unit) {
    LazyColumn(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Text("Meses del Año", style = MaterialTheme.typography.headlineMedium, color = TextMain)
            Text("Un año tiene 12 meses en total.", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(MESES) { mes ->
            BotonEducativo3D(
                onClick = { 
                    val text = "${mes.nombre}. Tiene ${mes.dias} días y es parte de la estación ${mes.estacion.nombre}. ${mes.fechasHistoricas}"
                    onSpeak(text)
                },
                baseColor = mes.estacion.color,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = Color.White.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = mes.orden.toString(),
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(mes.nombre, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        Text("${mes.dias} días • ${mes.estacion.nombre}", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                        if (mes.transicion.isNotEmpty()) {
                            Text(mes.transicion, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.ExtraBold)
                        }
                        if (mes.fechasHistoricas.isNotEmpty()) {
                            Text(
                                text = mes.fechasHistoricas,
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 11.sp,
                                lineHeight = 14.sp,
                                modifier = Modifier.padding(top = 4.dp),
                                maxLines = 6,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    Text(mes.estacion.icono, fontSize = 28.sp)
                }
            }
        }
    }
}

@Composable
fun EstacionesSeccion(onSpeak: (String) -> Unit) {
    LazyColumn(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Text("Las 4 Estaciones", style = MaterialTheme.typography.headlineMedium, color = TextMain)
            Text("El clima cambia según la época del año.", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(Estacion.entries.toList()) { estacion ->
            BotonEducativo3D(
                onClick = {
                    val text = " ${estacion.nombre}. ${estacion.descripcion}. Los meses de esta estación son ${estacion.mesesCompletos}."
                    onSpeak(text)
                },
                baseColor = estacion.color,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(estacion.icono, fontSize = 44.sp)
                        Spacer(modifier = Modifier.width(20.dp))
                        Column {
                            Text(estacion.nombre, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            Text(estacion.descripcion, color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider(color = Color.White.copy(alpha = 0.3f))
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "Meses completos:",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        estacion.mesesCompletos,
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnoSeccion(onSpeak: (String) -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Todo un Año", style = MaterialTheme.typography.headlineMedium, color = TextMain)
        Spacer(modifier = Modifier.height(20.dp))
        
        BotonEducativo3D(
            onClick = { 
                onSpeak("Un año completo tiene doce meses, cincuenta y dos semanas y trescientos sesenta y cinco días.")
            },
            baseColor = BluePrimary,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("1 AÑO COMPLETO", fontSize = 24.sp, fontWeight = FontWeight.Black, color = Color.White)
                HorizontalDivider(
                    color = Color.White.copy(alpha = 0.3f),
                    modifier = Modifier.padding(vertical = 16.dp),
                    thickness = 1.dp
                )
                StatRow("Estaciones", "4")
                StatRow("Meses", "12")
                StatRow("Semanas", "52")
                StatRow("Días", "365")
            }
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        
        Text(
            "Mapa de las 52 Semanas",
            style = MaterialTheme.typography.titleLarge,
            color = TextMain,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            maxItemsInEachRow = 7
        ) {
            repeat(52) { i ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(34.dp)
                        .background(BlueSecondary.copy(alpha = 0.2f), RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text((i + 1).toString(), fontSize = 10.sp, color = BluePrimary)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        InfoCard(
            titulo = "¿Año Bisiesto?",
            contenido = "Cada 4 años, el año tiene 366 días. ¡Febrero gana un día más (el 29)!"
        )
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun BotonEducativo3D(
    onClick: () -> Unit,
    baseColor: Color,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val sweepProgress by animateFloatAsState(
        targetValue = if (isPressed) 1f else 0f,
        animationSpec = tween(durationMillis = 250),
        label = "sweepProgress"
    )

    val verticalOffset = (sweepProgress * 4).dp
    val pressedColor = baseColor.copy(alpha = 0.7f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(y = 4.dp)
                .background(Color.Black.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = verticalOffset),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            0.0f to pressedColor,
                            sweepProgress to pressedColor,
                            sweepProgress to baseColor,
                            1.0f to baseColor
                        )
                    ),
                content = content
            )
        }
    }
}
