package com.lego.admin.formydaddyimport android.app.Applicationimport com.lego.admin.formydaddy.di.AppComponentimport com.lego.admin.formydaddy.di.DaggerAppComponentimport com.lego.admin.formydaddy.di.DominoModuleopen class DaddyApplication : Application() {    companion object {        lateinit var graph: AppComponent    }    override fun onCreate() {        super.onCreate()        graph = DaggerAppComponent.builder().dominoModule(DominoModule()).build()    }}