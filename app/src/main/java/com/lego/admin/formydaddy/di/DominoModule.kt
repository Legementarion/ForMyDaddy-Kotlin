package com.lego.admin.formydaddy.diimport android.content.Contextimport com.google.firebase.analytics.FirebaseAnalyticsimport com.lego.admin.formydaddy.logic.Gameimport dagger.Moduleimport dagger.Providesimport javax.inject.Singleton@Moduleclass DominoModule(val context: Context) {    @Provides    @Singleton    fun context(): Context {        return context    }    @Provides    @Singleton    fun provideGame(): Game = Game()    @Provides    @Singleton    fun provideFireBase(): FirebaseAnalytics = FirebaseAnalytics.getInstance(context)}