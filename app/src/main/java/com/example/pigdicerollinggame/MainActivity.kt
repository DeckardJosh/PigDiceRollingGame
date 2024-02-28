package com.example.pigdicerollinggame

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

val PLAYERGAMESTEXT = "PLAYERGAMESTEXT"
val PLAYERTOTALSCORE = "PLAYERTOTALSCORE"
val PLAYERTURNTOTAL = "PLAYERTURNTOTAL"
val COMPUTERGAMESTEXT = "COMPUTERGAMESTEXT"
val COMPUTERTOTALSCORE = "COMPUTERTOTALSCORE"
val COMPUTERTURNTOTAL = "COMPUTERTURNTOTAL"
val CURRENTROLL = "CURRENTROLL"
val LEFTDIEIMG = "LEFTDIEIMAGE"
val RIGHTDIEIMG = "RIGHTDIEIMG"
val PLAYERWIN = "PLAYERWIN"
val COMPUTERWIN = "COMPUTERWIN"
val HOLD = "HOLD"
val WINNER = "WINNER"
val WINNINGSCORE = "WINNINGSCORE"

class MainActivity : AppCompatActivity() {
    //Late Inits
    lateinit var txtPlayerHeader: TextView
    lateinit var txtComputerHeader: TextView

    lateinit var txtPlayerGames: TextView
    lateinit var txtPlayerTotalScore: TextView
    lateinit var txtPlayerTurnTotal: TextView
    lateinit var txtComputerGames: TextView
    lateinit var txtComputerTotal: TextView
    lateinit var txtComputerTurnTotal: TextView

    lateinit var imgLeftDice: ImageView
    lateinit var imgRightDice: ImageView
    lateinit var imgTrophy: ImageView
    lateinit var imgLoser: ImageView

    lateinit var txtCurrentRoll: TextView

    lateinit var btnRoll: Button
    lateinit var btnHold: Button

    lateinit var btnShowLeaderboard: Button

    val diceImageOne = R.drawable.dice_1
    val diceImageTwo = R.drawable.dice_2
    val diceImageThree = R.drawable.dice_3
    val diceImageFour = R.drawable.dice_4
    val diceImageFive = R.drawable.dice_5
    val diceImageSix = R.drawable.dice_6

    var globalRight= 0
    var globalLeft = 0
    var globalPlayWin = 0
    var globalCompWin = 0
    var globalHoldEnabler = false

    var playerTotalHolder = 0
    var computerTotalHolder = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initApplication()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        txtCurrentRoll.text = savedInstanceState.getString(CURRENTROLL)
        txtPlayerGames.text = savedInstanceState.getString(PLAYERGAMESTEXT)
        txtPlayerTotalScore.text = savedInstanceState.getString(PLAYERTOTALSCORE)
        txtPlayerTurnTotal.text = savedInstanceState.getString(PLAYERTURNTOTAL)
        txtComputerGames.text = savedInstanceState.getString(COMPUTERGAMESTEXT)
        txtComputerTotal.text = savedInstanceState.getString(COMPUTERTOTALSCORE)
        txtComputerTurnTotal.text = savedInstanceState.getString(COMPUTERTURNTOTAL)

        globalLeft = savedInstanceState.getInt(LEFTDIEIMG)
        globalRight = savedInstanceState.getInt(RIGHTDIEIMG)
        imageSet(globalLeft, globalRight)

        globalPlayWin = savedInstanceState.getInt(PLAYERWIN)
        if (globalPlayWin > 0) {
            imgTrophy.visibility = View.VISIBLE
            globalPlayWin = 0
        }
        globalCompWin = savedInstanceState.getInt(COMPUTERWIN)
        if (globalCompWin > 0) {
            imgLoser.visibility = View.VISIBLE
            globalCompWin = 0
        }

        globalHoldEnabler = savedInstanceState.getBoolean(HOLD)
        if (globalHoldEnabler){
            btnHold.isEnabled = true
        }

    }

    override fun onSaveInstanceState(savedState: Bundle) {
        super.onSaveInstanceState(savedState)

        savedState.putString(CURRENTROLL, txtCurrentRoll.text.toString())
        savedState.putString(PLAYERGAMESTEXT, txtPlayerGames.text.toString())
        savedState.putString(PLAYERTOTALSCORE, txtPlayerTotalScore.text.toString())
        savedState.putString(PLAYERTURNTOTAL, txtPlayerTurnTotal.text.toString())
        savedState.putString(COMPUTERGAMESTEXT, txtComputerGames.text.toString())
        savedState.putString(COMPUTERTOTALSCORE, txtComputerTotal.text.toString())
        savedState.putString(COMPUTERTURNTOTAL  , txtComputerTurnTotal.text.toString())

        savedState.putInt(LEFTDIEIMG, globalLeft)
        savedState.putInt(RIGHTDIEIMG, globalRight)
        savedState.putInt(PLAYERWIN, globalPlayWin)
        savedState.putInt(COMPUTERWIN, globalCompWin)

        savedState.putBoolean(HOLD, globalHoldEnabler)

    }


    private fun initApplication() {
        txtPlayerHeader = findViewById(R.id.txtPlayerHeader)
        txtComputerHeader = findViewById(R.id.txtComputerHeader)

        txtPlayerGames = findViewById(R.id.txtPlayerGames)
        txtPlayerTotalScore = findViewById(R.id.txtPlayerTotalScore)
        txtPlayerTurnTotal = findViewById(R.id.txtPlayerTurnTotal)
        txtComputerGames = findViewById(R.id.txtComputerGames)
        txtComputerTotal = findViewById(R.id.txtComputerTotal)
        txtComputerTurnTotal = findViewById(R.id.txtComputerTurnTotal)

        imgLeftDice = findViewById(R.id.imgLeftDice)
        imgRightDice = findViewById(R.id.imgRightDice)

        imgTrophy = findViewById(R.id.imgTrophy)
        imgLoser = findViewById(R.id.imgLoser)

        txtCurrentRoll = findViewById(R.id.txtCurrentRoll)

        btnRoll = findViewById(R.id.btnRoll)
        btnHold = findViewById(R.id.btnHold)

        btnHold.isEnabled = false
        txtPlayerHeader.setBackgroundColor(Color.CYAN)

        btnShowLeaderboard = findViewById(R.id.btnShowLeaderboard)

    }

    fun randomRoll(): Int{
        val localRoll: Int
        localRoll = Random.nextInt(1,7)
        return localRoll
    }

    fun onRollButtonClick (v: View) {
        btnHold.isEnabled = true
        globalHoldEnabler = true
        val totalTurnScore: Int
        totalTurnScore = txtPlayerTurnTotal.text.toString().toInt()

        val localLeft = randomRoll()
        val localRight = randomRoll()
        //GLOBAL LEFT AND RIGHT DIE VALUES FOR IMAGE SETTING
        globalLeft = localLeft
        globalRight = localRight

        if (localLeft == 1 && localRight == 1){
            txtCurrentRoll.text = applicationContext.getString(R.string.current_roll_value)
            txtPlayerTurnTotal.text = applicationContext.getString(R.string.player_turn_total_value)
            txtPlayerTotalScore.text = applicationContext.getString(R.string.player_total_score_value)
                //image handling
            imageSet(localLeft, localRight)

            computerTurn()
        }else if(localLeft == 1 || localRight == 1){
            txtCurrentRoll.text = applicationContext.getString(R.string.current_roll_value)
            txtPlayerTurnTotal.text = applicationContext.getString(R.string.player_turn_total_value)
                //image handling
            imageSet(localLeft, localRight)

            computerTurn()
        }else{
            val totalRoll: Int
            totalRoll = localLeft + localRight
            txtCurrentRoll.text = ("$totalRoll")
            txtPlayerTurnTotal.text = "${totalTurnScore + totalRoll}"
                //image handling
            imageSet(localLeft, localRight)
        }
    }

    fun onHoldButtonClick (v: View) {
        val localTotal: Int
        //resetting player total holder
        playerTotalHolder = 0
        localTotal = (txtPlayerTotalScore.text.toString().toInt()) + (txtPlayerTurnTotal.text.toString().toInt())
        txtPlayerTotalScore.text = localTotal.toString()
        if (txtPlayerTotalScore.text.toString().toInt() >= 100){
            imgTrophy.visibility = View.VISIBLE
            globalPlayWin = 1
            //setting plyaer total holder
            playerTotalHolder = txtPlayerTotalScore.text.toString().toInt()
            val playerTally: Int
            playerTally = (txtPlayerGames.text.toString().toInt() + 1)
            txtPlayerGames.text = "$playerTally"
                //FULL RESET SCOREBOARD
            txtPlayerTurnTotal.text = applicationContext.getString(R.string.player_turn_total_value)
            txtCurrentRoll.text = applicationContext.getString(R.string.current_roll_value)
            txtPlayerTotalScore.text = applicationContext.getString(R.string.player_total_score_value)
            txtComputerTotal.text = applicationContext.getString(R.string.computer_total_score_value)
            txtComputerTurnTotal.text = applicationContext.getString(R.string.computer_turn_total_value)
        }else {
            txtPlayerTurnTotal.text = applicationContext.getString(R.string.player_turn_total_value)
            txtCurrentRoll.text = applicationContext.getString(R.string.current_roll_value)
            computerTurn()
        }
    }

    private fun computerTurn () {
        var localCompLeft: Int
        var localCompRight: Int
        var totalRoll: Int
        txtPlayerHeader.setBackgroundColor(Color.WHITE)
        txtComputerHeader.setBackgroundColor(Color.CYAN)
            object : CountDownTimer(3000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    localCompLeft = randomRoll()
                    localCompRight = randomRoll()
                    //GLOBAL LEFT AND RIGHT DIE VALUES FOR IMAGE SETTING
                    globalLeft = localCompLeft
                    globalRight = localCompRight

                    val totalCompTurnScore: Int
                    totalCompTurnScore = txtComputerTurnTotal.text.toString().toInt()
                    if (localCompLeft == 1 && localCompRight == 1) {
                        totalRoll = 0
                        txtCurrentRoll.text = applicationContext.getString(R.string.current_roll_value)
                        txtComputerTurnTotal.text = applicationContext.getString(R.string.computer_turn_total_value)
                        txtComputerTotal.text = applicationContext.getString(R.string.computer_total_score_value)
                            //image handling
                        imageSet(localCompLeft, localCompRight)
                        txtPlayerHeader.setBackgroundColor(Color.CYAN)
                        txtComputerHeader.setBackgroundColor(Color.WHITE)
                        cancel()
                    } else if (localCompLeft == 1 || localCompRight == 1) {
                        totalRoll = 0
                        txtCurrentRoll.text = applicationContext.getString(R.string.current_roll_value)
                        txtComputerTurnTotal.text = applicationContext.getString(R.string.computer_turn_total_value)
                            //image handling
                        imageSet(localCompLeft, localCompRight)
                        txtPlayerHeader.setBackgroundColor(Color.CYAN)
                        txtComputerHeader.setBackgroundColor(Color.WHITE)
                        cancel()
                    } else {
                        totalRoll = localCompLeft + localCompRight
                        txtCurrentRoll.text = ("$totalRoll")
                        txtComputerTurnTotal.text = "${totalCompTurnScore + totalRoll}"
                            //image handling
                        imageSet(localCompLeft, localCompRight)
                    }
                }
                override fun onFinish() {
                    val localTotal: Int
                    localTotal = (txtComputerTotal.text.toString()
                        .toInt()) + (txtComputerTurnTotal.text.toString().toInt())
                    txtComputerTotal.text = localTotal.toString()
                    compWinCheck()
                    txtComputerTurnTotal.text = applicationContext.getString(R.string.computer_turn_total_value)
                    txtCurrentRoll.text = applicationContext.getString(R.string.current_roll_value)
                    txtPlayerHeader.setBackgroundColor(Color.CYAN)
                    txtComputerHeader.setBackgroundColor(Color.WHITE)
                }
            }.start()
    }

    fun compWinCheck(){
        //resetting total placeholder
        computerTotalHolder = 0
        if ((txtComputerTotal.text.toString().toInt()) >= 100){
            imgLoser.visibility = View.VISIBLE
            globalCompWin = 1
            //getting total placeholder
            computerTotalHolder = txtComputerTotal.text.toString().toInt()
            val compTally: Int
            compTally = (txtComputerGames.text.toString().toInt() + 1)
            txtComputerGames.text = "$compTally"
                //FULL RESET SCOREBOARD
            txtComputerTurnTotal.text = applicationContext.getString(R.string.computer_turn_total_value)
            txtCurrentRoll.text = applicationContext.getString(R.string.current_roll_value)
            txtComputerTotal.text = applicationContext.getString(R.string.computer_total_score_value)
            txtPlayerTurnTotal.text = applicationContext.getString(R.string.player_turn_total_value)
            txtPlayerTotalScore.text = applicationContext.getString(R.string.player_total_score_value)
        }
    }

    fun onLoserClick (v: View) {
        imgLoser.visibility = View.INVISIBLE
        onComputerWinLeaderboard()
    }

    fun onTrophyClick (v: View) {
        imgTrophy.visibility = View.INVISIBLE
        onPlayerWinLeaderboard()
    }

    fun imageSet(leftDie: Int, rightDie: Int){
        when (leftDie){
            1-> {
                imgLeftDice.setImageResource(diceImageOne)
            }
            2 -> {
                imgLeftDice.setImageResource(diceImageTwo)
            }
            3 -> {
                imgLeftDice.setImageResource(diceImageThree)
            }
            4 -> {
                imgLeftDice.setImageResource(diceImageFour)
            }
            5 -> {
                imgLeftDice.setImageResource(diceImageFive)
            }
            6 -> {
                imgLeftDice.setImageResource(diceImageSix)
            }
        }
        when (rightDie){
            1 -> {
                imgRightDice.setImageResource(diceImageOne)
            }
            2 -> {
                imgRightDice.setImageResource(diceImageTwo)
            }
            3 -> {
                imgRightDice.setImageResource(diceImageThree)
            }
            4 -> {
                imgRightDice.setImageResource(diceImageFour)
            }
            5 -> {
                imgRightDice.setImageResource(diceImageFive)
            }
            6 -> {
                imgRightDice.setImageResource(diceImageSix)
            }
        }
    }

    fun onShowLeaderboardClick( v: View ){
        val intent = Intent(this, Leaderboard::class.java)

        startActivity(intent)
    }

    fun onPlayerWinLeaderboard(){
        val intent = Intent(this, Leaderboard::class.java)
        intent.putExtra(WINNER, getString(R.string.you))
        intent.putExtra(WINNINGSCORE, playerTotalHolder.toString())

        startActivity(intent)
    }

    fun onComputerWinLeaderboard(){
        val intent = Intent(this, Leaderboard::class.java)
        intent.putExtra(WINNER, getString(R.string.computer))
        intent.putExtra(WINNINGSCORE, computerTotalHolder.toString())

        startActivity(intent)
    }
}
