package com.lego.admin.formydaddy.presenterimport com.arellomobile.mvp.InjectViewStateimport com.arellomobile.mvp.MvpPresenterimport com.lego.admin.formydaddy.DaddyApplicationimport com.lego.admin.formydaddy.logic.Gameimport javax.inject.Inject@InjectViewStateclass MainPresenter : MvpPresenter<MainView>() {    @Inject    lateinit var mGame: Game    init {        DaddyApplication.graph.inject(this)    }}