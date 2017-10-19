package com.lego.admin.formydaddy.logicimport android.util.Pairimport android.view.Viewimport com.lego.admin.formydaddy.Rimport java.util.*class Game {    var tree: MutableList<Node> = ArrayList()    fun Game() {        initGameDeck()        shuffle()        initGameTree()    }    private fun initGameDeck() {        tree.clear()        var tempType = 0        var tempValue = 0        loop@ for (i in 0 until Utils.DECK_SIZE) {            val newNode = Node()            when (i) {                7 -> {                    tempType++                    tempValue = tempType                }                13 -> {                    tempType++                    tempValue = tempType                }                18 -> {                    tempType++                    tempValue = tempType                }                22 -> {                    tempType++                    tempValue = tempType                }                25 -> {                    tempType++                    tempValue = tempType                }                27 -> {                    tempType++                    tempValue = tempType                }            }            newNode.value = Pair(tempType, tempValue)            tempValue++            tree.add(newNode)        }    }    private fun shuffle() {        tree.sort()    }    private fun initGameTree() {        var j = 7        var res = 13        tree.reverse()        loop@ for (i in 0 until Utils.DECK_SIZE) {            when (i) {                in 0 until 7 -> {                }                else -> {                    if (i == res) {                        j--                        res += j - 1                    }                    tree[i].mother = tree[i - j]                    tree[i].father = tree[i - (j - 1)]                    tree[i - j].children.add(tree[i])                    tree[i - (j - 1)].children.add(tree[i])                }            }        }        tree.reverse()    }    fun checkPick(firstPick: Node, secondPick: Node): Boolean {        val firstValue = firstPick.value!!.first + firstPick.value!!.second        val secondValue = secondPick.value!!.first + secondPick.value!!.second        if (firstValue + secondValue == 12) {            firstPick.dominoView.visibility = View.INVISIBLE            firstPick.removeRelation()            secondPick.dominoView.visibility = View.INVISIBLE            secondPick.removeRelation()            return true        }        return false    }    fun checkWin(): Boolean {        tree.forEach {            if (it.isVisible()) {                return false            }        }        return true    }    fun stop() {        tree.forEach { v -> run { v.dominoView.visibility = View.VISIBLE }.also { v.dominoView.setImageResource(R.drawable.title) } }    }}