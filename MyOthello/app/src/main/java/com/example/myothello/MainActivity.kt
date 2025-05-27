package com.example.myothello

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    //private var game1 = GameManager()
    private var whiteDisk = 0
    private var blackDisk = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        updateDisplay()
    }

    // update the text that displays the game's data (disk count, who's turn it is, and is the game being played)
    fun updateDisplay() {
        val turnOrWin = findViewById<TextView>(R.id.turnAndWin)
        val playingOrNo = findViewById<TextView>(R.id.gameStatus)
        val whiteCountDisplay = findViewById<TextView>(R.id.whiteCount)
        val blackCountDisplay = findViewById<TextView>(R.id.blackCount)

        var game = findViewById<GameManager>(R.id.gameManager2)

        //check if the game is currently being played or not.
        if (game.gameStarted) {
            //if game is being played, display so that there is game currently going on
            playingOrNo.text = "Currently playing"

            //now, check who's turn is it. If 'whosTurn' is true, it is white's turn
            if (game.whosTurn) {
                turnOrWin.text = "Current turn: White"
            }

            //if 'whosTurn' is false, it is black's turn
            else {
                turnOrWin.text = "Current turn: Black"
            }
        }

        //if there's no game currently going on, display no text on top and say no game is being played
        else {
            turnOrWin.text = ""
            playingOrNo.text = "No game is on session"
        }

        //count how many white and black disks are there in the board
        whiteDisk = game.countDisk(true)
        blackDisk = game.countDisk(false)

        //then display the current amount of white and black disks
        whiteCountDisplay.text = "White: " + whiteDisk.toString()
        blackCountDisplay.text = "Black: " + blackDisk.toString()
    }


    /*
    Things left to do:
    1.) debug status display update (disk counting and player's turn isn't updating)
    One idea is to call main's update function at GameManager class but doing so will crash the app.
    Also, restarting the game resets the board data but does not graphically refresh the board.
    Appending 'onclick' to GameManager2 doesn't do anything at all either.
    Got to find a way to somehow give return signal from GameManager class to Main class so that texts are properly updated.
    */

    //get UI by ID
    //something similar to this:
    //val outputView = findViewById<TextView>(R.id.numberDisplay)

    /*
    reset everything when new game button is pressed
    1.) white and black disk count
    2.) reset board to default
    3.) reset 'validMoveMade' variable just to be safe
    */
    fun newGame(view: View) {
        //grab the entity of the entire game itself
        var gameReset = findViewById<GameManager>(R.id.gameManager2)

        //set 'gameStarted' variable to true to indicate that the new game has commenced
        gameReset.gameStarted = true

        //set 'validMoveMade' variable back to false to indicate that
        //no valid move has made yet to change player turn
        gameReset.validMoveMade = false

        //set both 'whiteDone' and 'blackDone' variable back to false
        //to indicate that both of them have valid moves available
        //since new game has started
        gameReset.whiteDone = false
        gameReset.blackDone = false

        //reset every grid of the board
        for (i in gameReset.grid) {
            for (j in i) {
                i[j] = 0
            }
        }

        //after resetting, set the default disks
        gameReset.grid[3][3] = 1
        gameReset.grid[3][4] = 2
        gameReset.grid[4][3] = 2
        gameReset.grid[4][4] = 1

        updateDisplay()
    }
}