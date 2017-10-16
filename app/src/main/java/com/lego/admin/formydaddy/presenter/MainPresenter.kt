package com.lego.admin.formydaddy.presenterimport android.support.v7.app.AlertDialogimport com.arellomobile.mvp.InjectViewStateimport com.arellomobile.mvp.MvpPresenterimport com.lego.admin.formydaddy.DaddyApplicationimport com.lego.admin.formydaddy.Rimport com.lego.admin.formydaddy.activity.PlayActivityimport com.lego.admin.formydaddy.logic.Gameimport javax.inject.Injectimport android.content.Context.MODE_PRIVATEimport android.content.SharedPreferencesimport android.os.Handlerimport android.widget.ImageButtonimport com.lego.admin.formydaddy.logic.Nodeimport com.lego.admin.formydaddy.logic.Utils@InjectViewStateclass MainPresenter : MvpPresenter<MainView>() {    @Inject    lateinit var mGame: Game    private lateinit var alert: AlertDialog    private lateinit var sPref: SharedPreferences    private var firstPick: Node? = null    private var secondPick: Node? = null    private var newGameStarted: Boolean = true    init {        DaddyApplication.graph.inject(this)    }    fun startGame(playActivity: PlayActivity) {        if (newGameStarted) {            increaseStats(playActivity)        }        mGame.Game()        drawDeck(playActivity, true)    }    private fun drawDeck(playActivity: PlayActivity, startOfGame: Boolean) {        loop@ for (i in 0 until mGame.tree.size) {            mGame.tree[i].dominoView = playActivity.mDominoArray[i]            if (mGame.tree[i].isAlive()) {                if (mGame.tree[i].isVisible()) {                    val buf = "c" + "_" + mGame.tree[i].value?.first + "_" + mGame.tree[i].value?.second                    playActivity.mDominoArray[i].setImageResource(playActivity.resources.getIdentifier(buf, "drawable", playActivity.packageName))                    playActivity.mDominoArray[i].isEnabled = true                } else {                    playActivity.mDominoArray[i].isEnabled = false                }            }            if (startOfGame) {                playActivity.mDominoArray[i].setOnClickListener {                    if (mGame.tree[i].isAlive()) {                        doPick(playActivity, playActivity.mDominoArray[i], mGame.tree[i])                    }                }            }        }    }    private fun doPick(playActivity: PlayActivity, imageButton: ImageButton, node: Node) {        viewState.showPick(imageButton.id)        if (firstPick == null) {            firstPick = node        } else if (secondPick == null) {            secondPick = node            if (firstPick != secondPick) {                if (mGame.checkPick(firstPick!!, secondPick!!)) {                    if (mGame.checkWin()) {                        increaseWin(playActivity)                        showWinnerPopup(playActivity)                    }                }                drawDeck(playActivity, false)            }            Handler().postDelayed({                firstPick = null                secondPick = null                viewState.cancelPick()            }, 400)        } else {            viewState.cancelPick()        }    }    private fun showWinnerPopup(playActivity: PlayActivity) {        val message = playActivity.resources.getString(R.string.game_win)        val builder = AlertDialog.Builder(playActivity)        builder.setTitle(R.string.win)                .setMessage(message)                .setCancelable(false)                .setPositiveButton(R.string.more, { _, _ -> startGame(playActivity) })        val alert = builder.create()        alert.show()        startGame(playActivity)    }    fun stopGame() {        mGame.stop()    }    fun showStats(playActivity: PlayActivity) {        sPref = playActivity.getPreferences(MODE_PRIVATE)        val stats = sPref.getString(Utils.DOMINO_STATS, Utils.ZERO)        val wins = sPref.getString(Utils.DOMINO_WINS, Utils.ZERO)        val message = playActivity.resources.getString(R.string.played) +                " " + stats + " " + playActivity.resources.getString(R.string.games) +                "\n" + playActivity.resources.getString(R.string.wins) + " " + wins +                " " + playActivity.resources.getString(R.string.games)        val builder = AlertDialog.Builder(playActivity)        builder.setTitle(R.string.game_stats_title)                .setMessage(message)                .setCancelable(false)                .setNegativeButton(R.string.ok,                        { dialog, _ -> dialog.cancel() })                .setPositiveButton(R.string.clear, { _, _ -> clearData(playActivity) })        val alert = builder.create()        alert.show()    }    private fun clearData(activity: PlayActivity) {        sPref = activity.getPreferences(MODE_PRIVATE)        val ed = sPref.edit()        ed.putString(Utils.DOMINO_STATS, Utils.ZERO)        ed.putString(Utils.DOMINO_WINS, Utils.ZERO)        ed.apply()    }    private fun increaseStats(activity: PlayActivity) {        sPref = activity.getPreferences(MODE_PRIVATE)        var stats = sPref.getString(Utils.DOMINO_STATS, Utils.ZERO)        val i: Int = Integer.parseInt(stats)        stats = Integer.toString(i + 1)        val ed = sPref.edit()        ed.putString(Utils.DOMINO_STATS, stats)        ed.apply()    }    private fun increaseWin(activity: PlayActivity) {        sPref = activity.getPreferences(MODE_PRIVATE)        var wins = sPref.getString(Utils.DOMINO_WINS, Utils.ZERO)        val i: Int = Integer.parseInt(wins)        wins = Integer.toString(i + 1)        val ed = sPref.edit()        ed.putString(Utils.DOMINO_WINS, wins)        ed.apply()    }    fun showRules(playActivity: PlayActivity) {        val builder = AlertDialog.Builder(playActivity)        builder.setTitle(R.string.game_rules_title)                .setMessage(R.string.game_rules)                .setCancelable(false)                .setNegativeButton(R.string.ok,                        { dialog, _ -> dialog.cancel() })        alert = builder.create()        alert.show()    }}