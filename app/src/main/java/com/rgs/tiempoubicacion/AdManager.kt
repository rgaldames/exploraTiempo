package com.rgs.tiempoubicacion

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration

object AdManager {
    // IDs de Prueba oficiales de Google
    private const val BANNER_TEST_ID = "ca-app-pub-3940256099942544/6300978111"
    
    // Reemplaza con tu ID de producción real cuando lo tengas
    private const val BANNER_PROD_ID = "ca-app-pub-3940256099942544/6300978111" 

    /**
     * Retorna el ID del banner dependiendo de si la app está en modo Debug o Release.
     */
    fun getBannerAdUnitId(): String {
        return if (BuildConfig.DEBUG) {
            BANNER_TEST_ID
        } else {
            BANNER_PROD_ID
        }
    }

    /**
     * Configura AdMob con las políticas de privacidad para niños (COPPA).
     */
    fun configureAdsForChildren() {
        val requestConfiguration = MobileAds.getRequestConfiguration().toBuilder()
            .setTagForChildDirectedTreatment(RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
            .setMaxAdContentRating(RequestConfiguration.MAX_AD_CONTENT_RATING_G)
            .build()
        MobileAds.setRequestConfiguration(requestConfiguration)
    }

    /**
     * Inicializa AdMob.
     */
    fun initialize(context: android.content.Context) {
        configureAdsForChildren()
        MobileAds.initialize(context) {}
    }

    /**
     * Crea un AdRequest estándar.
     */
    fun createAdRequest(): AdRequest {
        return AdRequest.Builder().build()
    }
}
