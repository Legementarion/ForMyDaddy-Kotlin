package com.lego.admin.formydaddy.logicimport android.util.Pairimport android.view.Viewimport android.widget.ImageButtonimport java.util.*import kotlin.collections.ArrayListdata class Node(var mother: Node? = null, var father: Node? = null) : Comparable<Node> {    var id: String = ""    var value: Pair<Int, Int>? = null    lateinit var dominoView: ImageButton    var children: MutableList<Node> = ArrayList()    fun isAlive(): Boolean {        return (father == null && mother == null) || children.isEmpty()    }    fun isVisible(): Boolean {        return dominoView.visibility == View.VISIBLE    }    fun removeRelation(){        children.forEach {            if (it.father == this)                father = null            if (it.mother == this)                father = null        }        mother?.children?.remove(this)        mother = null        father?.children?.remove(this)        father = null        children.clear()    }    override fun compareTo(other: Node): Int {        val random = Random()        return random.nextInt() / Utils.DECK_SIZE    }}