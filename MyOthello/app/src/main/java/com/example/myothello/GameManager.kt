package com.example.myothello

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat

class GameManager(context: Context?, attrs: AttributeSet?) : View(context, attrs), GestureDetector.OnGestureListener {
    /*
    game tracker
    0 = empty grid(green)
    1 = white disk
    2 = black disk

    background color of board(grid) will be green
    */
    var grid = arrayOf(arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 1, 2, 0, 0, 0),
                       arrayOf(0, 0, 0, 2, 1, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0),
                       arrayOf(0, 0, 0, 0, 0, 0, 0, 0),)

    var gridDisk = arrayOf(Color.GREEN, Color.WHITE, Color.BLACK)

    // points used to indicate specific points in vertical side
    private var vertPoint1 : Float = 0.0f
    private var vertPoint2 : Float = 0.0f
    private var vertPoint3 : Float = 0.0f
    private var vertPoint4 : Float = 0.0f
    private var vertPoint5 : Float = 0.0f
    private var vertPoint6 : Float = 0.0f
    private var vertPoint7 : Float = 0.0f
    private var vertPoint8 : Float = 0.0f
    //private var vertPoint9 : Float = 0.0f

    // points used to indicate specific points in horizontal side
    private var horiPoint1 : Float = 0.0f
    private var horiPoint2 : Float = 0.0f
    private var horiPoint3 : Float = 0.0f
    private var horiPoint4 : Float = 0.0f
    private var horiPoint5 : Float = 0.0f
    private var horiPoint6 : Float = 0.0f
    private var horiPoint7 : Float = 0.0f
    private var horiPoint8 : Float = 0.0f
    //private var horiPoint9 : Float = 0.0f

    //true = White
    //false = Black
    var whosTurn : Boolean = true

    //used to check if valid move was made so that player's turn can be changed
    var validMoveMade : Boolean = false

    //check to make sure if game is commenced or not
    var gameStarted : Boolean = false

    //check to see if both white and black disk don't have anymore valid moves
    var whiteDone : Boolean = false
    var blackDone : Boolean = false

    private val paint = Paint()

    private var mDetector = GestureDetectorCompat(this.context, this)


    // function for counting amount for specific color of disk
    fun countDisk(player: Boolean): Int {
        var disk : Int = 0

        //count how many white disks are there
        if (player) {
            for (i in grid) {
                for (j in i) {
                    if (i[j] == 1) {
                        disk++
                    }
                }
            }
        }

        //count how many black disks are there
        else {
            for (i in grid) {
                for (j in i) {
                    if (i[j] == 2) {
                        disk++
                    }
                }
            }
        }

        return disk
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            if (mDetector.onTouchEvent(event)) {
                return true
            }
        }

        return super.onTouchEvent(event)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        /* specific points of square used from demo
        mWidth = w.toFloat() //entire width
        mHeight = h.toFloat() //entire height
        mMidWidth = mWidth / 2 //half of the width
        mMidHeight = mHeight / 2 //half of the height
        */

        //these 4 variables are used to indicate specific point of the whole square
        // so that each color square can be shaped in specific size

        // now to setup 10 specific points for both horizontal and vertical sides
        //horiPoint9 = w.toFloat()
        //horiPoint8 = horiPoint9 - (horiPoint9 / 9)
        horiPoint8 = w.toFloat()
        horiPoint7 = horiPoint8 - (horiPoint8 / 8)
        horiPoint6 = horiPoint7 - (horiPoint8 / 8)
        horiPoint5 = horiPoint6 - (horiPoint8 / 8)
        horiPoint4 = horiPoint5 - (horiPoint8 / 8)
        horiPoint3 = horiPoint4 - (horiPoint8 / 8)
        horiPoint2 = horiPoint3 - (horiPoint8 / 8)
        horiPoint1 = horiPoint2 - (horiPoint8 / 8)

        //vertPoint9 = h.toFloat()
        //vertPoint8 = vertPoint9 - (vertPoint9 / 9)
        vertPoint8 = h.toFloat()
        vertPoint7 = vertPoint8 - (vertPoint8 / 8)
        vertPoint6 = vertPoint7 - (vertPoint8 / 8)
        vertPoint5 = vertPoint6 - (vertPoint8 / 8)
        vertPoint4 = vertPoint5 - (vertPoint8 / 8)
        vertPoint3 = vertPoint4 - (vertPoint8 / 8)
        vertPoint2 = vertPoint3 - (vertPoint8 / 8)
        vertPoint1 = vertPoint2 - (vertPoint8 / 8)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //canvas.drawRect(left, top, right, bottom, paint)

        /*
        starting from top left grid to bottom right grid
        from left to right, top to bottom
        */

        // 1st grid lane
        // Grid[0][0]
        paint.color = Color.GREEN
        canvas.drawRect(0.0f, 0.0f, horiPoint1, vertPoint1, paint)

        // Grid[0][1]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint1, 0.0f, horiPoint2, vertPoint1, paint)

        // Grid[0][2]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint2, 0.0f, horiPoint3, vertPoint1, paint)

        // Grid[0][3]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint3, 0.0f, horiPoint4, vertPoint1, paint)

        // Grid[0][4]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint4, 0.0f, horiPoint5, vertPoint1, paint)

        // Grid[0][5]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint5, 0.0f, horiPoint6, vertPoint1, paint)

        // Grid[0][6]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint6, 0.0f, horiPoint7, vertPoint1, paint)

        // Grid[0][7]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint7, 0.0f, horiPoint8, vertPoint1, paint)

        // Grid[0][8]
        //paint.color = Color.WHITE
        //canvas.drawRect(horiPoint8, 0.0f, horiPoint9, vertPoint1, paint)



        //2nd grid lane
        // Grid[1][0]
        paint.color = Color.GREEN
        canvas.drawRect(0.0f, vertPoint1, horiPoint1, vertPoint2, paint)

        // Grid[1][1]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint1, vertPoint1, horiPoint2, vertPoint2, paint)

        // Grid[1][2]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint2, vertPoint1, horiPoint3, vertPoint2, paint)

        // Grid[1][3]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint3, vertPoint1, horiPoint4, vertPoint2, paint)

        // Grid[1][4]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint4, vertPoint1, horiPoint5, vertPoint2, paint)

        // Grid[1][5]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint5, vertPoint1, horiPoint6, vertPoint2, paint)

        // Grid[1][6]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint6, vertPoint1, horiPoint7, vertPoint2, paint)

        // Grid[1][7]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint7, vertPoint1, horiPoint8, vertPoint2, paint)

        // Grid[1][8]
        //paint.color = Color.WHITE
        //canvas.drawRect(horiPoint8, vertPoint1, horiPoint9, vertPoint2, paint)


        //3rd grid lane
        // Grid[2][0]
        paint.color = Color.GREEN
        canvas.drawRect(0.0f, vertPoint2, horiPoint1, vertPoint3, paint)

        // Grid[2][1]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint1, vertPoint2, horiPoint2, vertPoint3, paint)

        // Grid[2][2]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint2, vertPoint2, horiPoint3, vertPoint3, paint)

        // Grid[2][3]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint3, vertPoint2, horiPoint4, vertPoint3, paint)

        // Grid[2][4]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint4, vertPoint2, horiPoint5, vertPoint3, paint)

        // Grid[2][5]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint5, vertPoint2, horiPoint6, vertPoint3, paint)

        // Grid[2][6]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint6, vertPoint2, horiPoint7, vertPoint3, paint)

        // Grid[2][7]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint7, vertPoint2, horiPoint8, vertPoint3, paint)

        // Grid[2][8]
        //paint.color = Color.WHITE
        //canvas.drawRect(horiPoint8, vertPoint2, horiPoint9, vertPoint3, paint)



        //4th grid lane
        // Grid[3][0]
        paint.color = Color.GREEN
        canvas.drawRect(0.0f, vertPoint3, horiPoint1, vertPoint4, paint)

        // Grid[3][1]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint1, vertPoint3, horiPoint2, vertPoint4, paint)

        // Grid[3][2]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint2, vertPoint3, horiPoint3, vertPoint4, paint)

        // Grid[3][3]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint3, vertPoint3, horiPoint4, vertPoint4, paint)

        // Grid[3][4]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint4, vertPoint3, horiPoint5, vertPoint4, paint)

        // Grid[3][5]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint5, vertPoint3, horiPoint6, vertPoint4, paint)

        // Grid[3][6]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint6, vertPoint3, horiPoint7, vertPoint4, paint)

        // Grid[3][7]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint7, vertPoint3, horiPoint8, vertPoint4, paint)

        // Grid[3][8]
        //paint.color = Color.WHITE
        //canvas.drawRect(horiPoint8, vertPoint3, horiPoint9, vertPoint4, paint)



        //5th grid lane
        // Grid[4][0]
        paint.color = Color.GREEN
        canvas.drawRect(0.0f, vertPoint4, horiPoint1, vertPoint5, paint)

        // Grid[4][1]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint1, vertPoint4, horiPoint2, vertPoint5, paint)

        // Grid[4][2]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint2, vertPoint4, horiPoint3, vertPoint5, paint)

        // Grid[4][3]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint3, vertPoint4, horiPoint4, vertPoint5, paint)

        // Grid[4][4]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint4, vertPoint4, horiPoint5, vertPoint5, paint)

        // Grid[4][5]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint5, vertPoint4, horiPoint6, vertPoint5, paint)

        // Grid[4][6]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint6, vertPoint4, horiPoint7, vertPoint5, paint)

        // Grid[4][7]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint7, vertPoint4, horiPoint8, vertPoint5, paint)

        // Grid[4][8]
        //paint.color = Color.WHITE
        //canvas.drawRect(horiPoint8, vertPoint4, horiPoint9, vertPoint5, paint)


        //6th grid lane
        // Grid[5][0]
        paint.color = Color.GREEN
        canvas.drawRect(0.0f, vertPoint5, horiPoint1, vertPoint6, paint)

        // Grid[5][1]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint1, vertPoint5, horiPoint2, vertPoint6, paint)

        // Grid[5][2]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint2, vertPoint5, horiPoint3, vertPoint6, paint)

        // Grid[5][3]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint3, vertPoint5, horiPoint4, vertPoint6, paint)

        // Grid[5][4]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint4, vertPoint5, horiPoint5, vertPoint6, paint)

        // Grid[5][5]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint5, vertPoint5, horiPoint6, vertPoint6, paint)

        // Grid[5][6]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint6, vertPoint5, horiPoint7, vertPoint6, paint)

        // Grid[5][7]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint7, vertPoint5, horiPoint8, vertPoint6, paint)

        // Grid[5][8]
        //paint.color = Color.WHITE
        //canvas.drawRect(horiPoint8, vertPoint5, horiPoint9, vertPoint6, paint)



        //7th grid lane
        // Grid[6][0]
        paint.color = Color.GREEN
        canvas.drawRect(0.0f, vertPoint6, horiPoint1, vertPoint7, paint)

        // Grid[6][1]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint1, vertPoint6, horiPoint2, vertPoint7, paint)

        // Grid[6][2]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint2, vertPoint6, horiPoint3, vertPoint7, paint)

        // Grid[6][3]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint3, vertPoint6, horiPoint4, vertPoint7, paint)

        // Grid[6][4]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint4, vertPoint6, horiPoint5, vertPoint7, paint)

        // Grid[6][5]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint5, vertPoint6, horiPoint6, vertPoint7, paint)

        // Grid[6][6]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint6, vertPoint6, horiPoint7, vertPoint7, paint)

        // Grid[6][7]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint7, vertPoint6, horiPoint8, vertPoint7, paint)

        // Grid[6][8]
        //paint.color = Color.WHITE
        //canvas.drawRect(horiPoint8, vertPoint6, horiPoint9, vertPoint7, paint)


        //8th grid lane
        // Grid[7][0]
        paint.color = Color.GREEN
        canvas.drawRect(0.0f, vertPoint7, horiPoint1, vertPoint8, paint)

        // Grid[7][1]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint1, vertPoint7, horiPoint2, vertPoint8, paint)

        // Grid[7][2]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint2, vertPoint7, horiPoint3, vertPoint8, paint)

        // Grid[7][3]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint3, vertPoint7, horiPoint4, vertPoint8, paint)

        // Grid[7][4]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint4, vertPoint7, horiPoint5, vertPoint8, paint)

        // Grid[7][5]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint5, vertPoint7, horiPoint6, vertPoint8, paint)

        // Grid[7][6]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint6, vertPoint7, horiPoint7, vertPoint8, paint)

        // Grid[7][7]
        paint.color = Color.GREEN
        canvas.drawRect(horiPoint7, vertPoint7, horiPoint8, vertPoint8, paint)

        // Grid[7][8]
        //paint.color = Color.WHITE
        //canvas.drawRect(horiPoint8, vertPoint7, horiPoint9, vertPoint8, paint)


        /*
        //9th grid lane
        // Grid[8][0]
        paint.color = Color.WHITE
        canvas.drawRect(0.0f, vertPoint8, horiPoint1, vertPoint9, paint)

        // Grid[8][1]
        paint.color = Color.WHITE
        canvas.drawRect(horiPoint1, vertPoint8, horiPoint2, vertPoint9, paint)

        // Grid[8][2]
        paint.color = Color.WHITE
        canvas.drawRect(horiPoint2, vertPoint8, horiPoint3, vertPoint9, paint)

        // Grid[8][3]
        paint.color = Color.WHITE
        canvas.drawRect(horiPoint3, vertPoint8, horiPoint4, vertPoint9, paint)

        // Grid[8][4]
        paint.color = Color.WHITE
        canvas.drawRect(horiPoint4, vertPoint8, horiPoint5, vertPoint9, paint)

        // Grid[8][5]
        paint.color = Color.WHITE
        canvas.drawRect(horiPoint5, vertPoint8, horiPoint6, vertPoint9, paint)

        // Grid[8][6]
        paint.color = Color.WHITE
        canvas.drawRect(horiPoint6, vertPoint8, horiPoint7, vertPoint9, paint)

        // Grid[8][7]
        paint.color = Color.WHITE
        canvas.drawRect(horiPoint7, vertPoint8, horiPoint8, vertPoint9, paint)

        // Grid[8][8]
        paint.color = Color.WHITE
        canvas.drawRect(horiPoint8, vertPoint8, horiPoint9, vertPoint9, paint)
        */


        //lines between grids (total of 10 lines for each sides)
        paint.color = Color.BLACK
        paint.strokeWidth = 5.0f
        //canvas.drawLine(startX, startY, stopX, stopY)

        //horizontal lines
        canvas.drawLine(0.0f, 0.0f, horiPoint8, 0.0f, paint)
        canvas.drawLine(0.0f, vertPoint1, horiPoint8, vertPoint1, paint)
        canvas.drawLine(0.0f, vertPoint2, horiPoint8, vertPoint2, paint)
        canvas.drawLine(0.0f, vertPoint3, horiPoint8, vertPoint3, paint)
        canvas.drawLine(0.0f, vertPoint4, horiPoint8, vertPoint4, paint)
        canvas.drawLine(0.0f, vertPoint5, horiPoint8, vertPoint5, paint)
        canvas.drawLine(0.0f, vertPoint6, horiPoint8, vertPoint6, paint)
        canvas.drawLine(0.0f, vertPoint7, horiPoint8, vertPoint7, paint)
        canvas.drawLine(0.0f, vertPoint8, horiPoint8, vertPoint8, paint)
        //canvas.drawLine(0.0f, vertPoint9, horiPoint9, vertPoint9, paint)

        //vertical lines
        canvas.drawLine(0.0f, 0.0f, 0.0f, vertPoint8, paint)
        canvas.drawLine(horiPoint1, 0.0f, horiPoint1, vertPoint8, paint)
        canvas.drawLine(horiPoint2, 0.0f, horiPoint2, vertPoint8, paint)
        canvas.drawLine(horiPoint3, 0.0f, horiPoint3, vertPoint8, paint)
        canvas.drawLine(horiPoint4, 0.0f, horiPoint4, vertPoint8, paint)
        canvas.drawLine(horiPoint5, 0.0f, horiPoint5, vertPoint8, paint)
        canvas.drawLine(horiPoint6, 0.0f, horiPoint6, vertPoint8, paint)
        canvas.drawLine(horiPoint7, 0.0f, horiPoint7, vertPoint8, paint)
        canvas.drawLine(horiPoint8, 0.0f, horiPoint8, vertPoint8, paint)
        //canvas.drawLine(horiPoint9, 0.0f, horiPoint9, vertPoint9, paint)

        //canvas.drawLine(0.0f, mMidHeight, mWidth, mMidHeight, paint) //horizontal line
        //canvas.drawLine(mMidWidth, 0.0f, mMidWidth, mHeight, paint) //vertical line


        /*
        //numbers for 1st grid lane
        canvas.drawText(grid[0][0].toString(), (horiPoint1 / 2), (vertPoint1 / 1.5f), paint)
        canvas.drawText(grid[0][1].toString(), (horiPoint2 - (horiPoint1 / 2)), (vertPoint1 / 1.5f), paint)
        canvas.drawText(grid[0][2].toString(), (horiPoint3 - (horiPoint1 / 2)), (vertPoint1 / 1.5f), paint)
        canvas.drawText(grid[0][3].toString(), (horiPoint4 - (horiPoint1 / 2)), (vertPoint1 / 1.5f), paint)
        canvas.drawText(grid[0][4].toString(), (horiPoint5 - (horiPoint1 / 2)), (vertPoint1 / 1.5f), paint)
        canvas.drawText(grid[0][5].toString(), (horiPoint6 - (horiPoint1 / 2)), (vertPoint1 / 1.5f), paint)
        canvas.drawText(grid[0][6].toString(), (horiPoint7 - (horiPoint1 / 2)), (vertPoint1 / 1.5f), paint)
        canvas.drawText(grid[0][7].toString(), (horiPoint8 - (horiPoint1 / 2)), (vertPoint1 / 1.5f), paint)
        canvas.drawText(grid[0][8].toString(), (horiPoint9 - (horiPoint1 / 2)), (vertPoint1 / 1.5f), paint)

        //numbers for 2nd grid lane
        canvas.drawText(grid[1][0].toString(), (horiPoint1 / 2), (vertPoint2 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[1][1].toString(), (horiPoint2 - (horiPoint1 / 2)), (vertPoint2 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[1][2].toString(), (horiPoint3 - (horiPoint1 / 2)), (vertPoint2 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[1][3].toString(), (horiPoint4 - (horiPoint1 / 2)), (vertPoint2 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[1][4].toString(), (horiPoint5 - (horiPoint1 / 2)), (vertPoint2 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[1][5].toString(), (horiPoint6 - (horiPoint1 / 2)), (vertPoint2 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[1][6].toString(), (horiPoint7 - (horiPoint1 / 2)), (vertPoint2 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[1][7].toString(), (horiPoint8 - (horiPoint1 / 2)), (vertPoint2 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[1][8].toString(), (horiPoint9 - (horiPoint1 / 2)), (vertPoint2 - (vertPoint1 / 3)), paint)

        //numbers for 3rd grid lane
        canvas.drawText(grid[2][0].toString(), (horiPoint1 / 2), (vertPoint3 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[2][1].toString(), (horiPoint2 - (horiPoint1 / 2)), (vertPoint3 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[2][2].toString(), (horiPoint3 - (horiPoint1 / 2)), (vertPoint3 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[2][3].toString(), (horiPoint4 - (horiPoint1 / 2)), (vertPoint3 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[2][4].toString(), (horiPoint5 - (horiPoint1 / 2)), (vertPoint3 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[2][5].toString(), (horiPoint6 - (horiPoint1 / 2)), (vertPoint3 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[2][6].toString(), (horiPoint7 - (horiPoint1 / 2)), (vertPoint3 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[2][7].toString(), (horiPoint8 - (horiPoint1 / 2)), (vertPoint3 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[2][8].toString(), (horiPoint9 - (horiPoint1 / 2)), (vertPoint3 - (vertPoint1 / 3)), paint)

        //numbers for 4th grid lane
        canvas.drawText(grid[3][0].toString(), (horiPoint1 / 2), (vertPoint4 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[3][1].toString(), (horiPoint2 - (horiPoint1 / 2)), (vertPoint4 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[3][2].toString(), (horiPoint3 - (horiPoint1 / 2)), (vertPoint4 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[3][3].toString(), (horiPoint4 - (horiPoint1 / 2)), (vertPoint4 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[3][4].toString(), (horiPoint5 - (horiPoint1 / 2)), (vertPoint4 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[3][5].toString(), (horiPoint6 - (horiPoint1 / 2)), (vertPoint4 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[3][6].toString(), (horiPoint7 - (horiPoint1 / 2)), (vertPoint4 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[3][7].toString(), (horiPoint8 - (horiPoint1 / 2)), (vertPoint4 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[3][8].toString(), (horiPoint9 - (horiPoint1 / 2)), (vertPoint4 - (vertPoint1 / 3)), paint)

        //numbers for 5th grid lane
        canvas.drawText(grid[4][0].toString(), (horiPoint1 / 2), (vertPoint5 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[4][1].toString(), (horiPoint2 - (horiPoint1 / 2)), (vertPoint5 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[4][2].toString(), (horiPoint3 - (horiPoint1 / 2)), (vertPoint5 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[4][3].toString(), (horiPoint4 - (horiPoint1 / 2)), (vertPoint5 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[4][4].toString(), (horiPoint5 - (horiPoint1 / 2)), (vertPoint5 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[4][5].toString(), (horiPoint6 - (horiPoint1 / 2)), (vertPoint5 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[4][6].toString(), (horiPoint7 - (horiPoint1 / 2)), (vertPoint5 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[4][7].toString(), (horiPoint8 - (horiPoint1 / 2)), (vertPoint5 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[4][8].toString(), (horiPoint9 - (horiPoint1 / 2)), (vertPoint5 - (vertPoint1 / 3)), paint)

        //numbers for 6th grid lane
        canvas.drawText(grid[5][0].toString(), (horiPoint1 / 2), (vertPoint6 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[5][1].toString(), (horiPoint2 - (horiPoint1 / 2)), (vertPoint6 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[5][2].toString(), (horiPoint3 - (horiPoint1 / 2)), (vertPoint6 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[5][3].toString(), (horiPoint4 - (horiPoint1 / 2)), (vertPoint6 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[5][4].toString(), (horiPoint5 - (horiPoint1 / 2)), (vertPoint6 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[5][5].toString(), (horiPoint6 - (horiPoint1 / 2)), (vertPoint6 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[5][6].toString(), (horiPoint7 - (horiPoint1 / 2)), (vertPoint6 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[5][7].toString(), (horiPoint8 - (horiPoint1 / 2)), (vertPoint6 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[5][8].toString(), (horiPoint9 - (horiPoint1 / 2)), (vertPoint6 - (vertPoint1 / 3)), paint)

        //numbers for 7th grid lane
        canvas.drawText(grid[6][0].toString(), (horiPoint1 / 2), (vertPoint7 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[6][1].toString(), (horiPoint2 - (horiPoint1 / 2)), (vertPoint7 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[6][2].toString(), (horiPoint3 - (horiPoint1 / 2)), (vertPoint7 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[6][3].toString(), (horiPoint4 - (horiPoint1 / 2)), (vertPoint7 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[6][4].toString(), (horiPoint5 - (horiPoint1 / 2)), (vertPoint7 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[6][5].toString(), (horiPoint6 - (horiPoint1 / 2)), (vertPoint7 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[6][6].toString(), (horiPoint7 - (horiPoint1 / 2)), (vertPoint7 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[6][7].toString(), (horiPoint8 - (horiPoint1 / 2)), (vertPoint7 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[6][8].toString(), (horiPoint9 - (horiPoint1 / 2)), (vertPoint7 - (vertPoint1 / 3)), paint)

        //numbers for 8th grid lane
        canvas.drawText(grid[7][0].toString(), (horiPoint1 / 2), (vertPoint8 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[7][1].toString(), (horiPoint2 - (horiPoint1 / 2)), (vertPoint8 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[7][2].toString(), (horiPoint3 - (horiPoint1 / 2)), (vertPoint8 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[7][3].toString(), (horiPoint4 - (horiPoint1 / 2)), (vertPoint8 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[7][4].toString(), (horiPoint5 - (horiPoint1 / 2)), (vertPoint8 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[7][5].toString(), (horiPoint6 - (horiPoint1 / 2)), (vertPoint8 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[7][6].toString(), (horiPoint7 - (horiPoint1 / 2)), (vertPoint8 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[7][7].toString(), (horiPoint8 - (horiPoint1 / 2)), (vertPoint8 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[7][8].toString(), (horiPoint9 - (horiPoint1 / 2)), (vertPoint8 - (vertPoint1 / 3)), paint)

        //numbers for 9th grid lane
        canvas.drawText(grid[8][0].toString(), (horiPoint1 / 2), (vertPoint9 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[8][1].toString(), (horiPoint2 - (horiPoint1 / 2)), (vertPoint9 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[8][2].toString(), (horiPoint3 - (horiPoint1 / 2)), (vertPoint9 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[8][3].toString(), (horiPoint4 - (horiPoint1 / 2)), (vertPoint9 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[8][4].toString(), (horiPoint5 - (horiPoint1 / 2)), (vertPoint9 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[8][5].toString(), (horiPoint6 - (horiPoint1 / 2)), (vertPoint9 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[8][6].toString(), (horiPoint7 - (horiPoint1 / 2)), (vertPoint9 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[8][7].toString(), (horiPoint8 - (horiPoint1 / 2)), (vertPoint9 - (vertPoint1 / 3)), paint)
        canvas.drawText(grid[8][8].toString(), (horiPoint9 - (horiPoint1 / 2)), (vertPoint9 - (vertPoint1 / 3)), paint)
        */


        //starting disks
        //interesting resource I found but not used: https://www.geeksforgeeks.org/create-different-types-of-circle-in-canvas-in-android-using-jetpack-compose/
        //canvas.drawCircle(cx, cy, radius, paint)

        //SETUP disks for every grid so that when the grid value is changed, the correct disk will be displayed.
        //i = vertical (row)
        //j = horizontal (col)
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                paint.color = gridDisk[grid[i][j]]
                paint.style = Paint.Style.FILL_AND_STROKE

                if (i == 0) {
                    if (j == 0) {
                        canvas.drawCircle((horiPoint1 / 2), (vertPoint1 / 2f), 50f, paint)
                    }

                    else if (j == 1) {
                        canvas.drawCircle((horiPoint2 - (horiPoint1 / 2)), (vertPoint1 / 2f), 50f, paint)
                    }

                    else if (j == 2) {
                        canvas.drawCircle((horiPoint3 - (horiPoint1 / 2)), (vertPoint1 / 2f), 50f, paint)
                    }

                    else if (j == 3) {
                        canvas.drawCircle((horiPoint4 - (horiPoint1 / 2)), (vertPoint1 / 2f), 50f, paint)
                    }

                    else if (j == 4) {
                        canvas.drawCircle((horiPoint5 - (horiPoint1 / 2)), (vertPoint1 / 2f), 50f, paint)
                    }

                    else if (j == 5) {
                        canvas.drawCircle((horiPoint6 - (horiPoint1 / 2)), (vertPoint1 / 2f), 50f, paint)
                    }

                    else if (j == 6) {
                        canvas.drawCircle((horiPoint7 - (horiPoint1 / 2)), (vertPoint1 / 2f), 50f, paint)
                    }

                    else if (j == 7) {
                        canvas.drawCircle((horiPoint8 - (horiPoint1 / 2)), (vertPoint1 / 2f), 50f, paint)
                    }
                }

                else if (i == 1) {
                    if (j == 0) {
                        canvas.drawCircle((horiPoint1 / 2), (vertPoint2 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 1) {
                        canvas.drawCircle((horiPoint2 - (horiPoint1 / 2)), (vertPoint2 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 2) {
                        canvas.drawCircle((horiPoint3 - (horiPoint1 / 2)), (vertPoint2 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 3) {
                        canvas.drawCircle((horiPoint4 - (horiPoint1 / 2)), (vertPoint2 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 4) {
                        canvas.drawCircle((horiPoint5 - (horiPoint1 / 2)), (vertPoint2 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 5) {
                        canvas.drawCircle((horiPoint6 - (horiPoint1 / 2)), (vertPoint2 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 6) {
                        canvas.drawCircle((horiPoint7 - (horiPoint1 / 2)), (vertPoint2 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 7) {
                        canvas.drawCircle((horiPoint8 - (horiPoint1 / 2)), (vertPoint2 - (vertPoint1 / 2f)), 50f, paint)
                    }
                }

                else if (i == 2) {
                    if (j == 0) {
                        canvas.drawCircle((horiPoint1 / 2), (vertPoint3 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 1) {
                        canvas.drawCircle((horiPoint2 - (horiPoint1 / 2)), (vertPoint3 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 2) {
                        canvas.drawCircle((horiPoint3 - (horiPoint1 / 2)), (vertPoint3 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 3) {
                        canvas.drawCircle((horiPoint4 - (horiPoint1 / 2)), (vertPoint3 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 4) {
                        canvas.drawCircle((horiPoint5 - (horiPoint1 / 2)), (vertPoint3 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 5) {
                        canvas.drawCircle((horiPoint6 - (horiPoint1 / 2)), (vertPoint3 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 6) {
                        canvas.drawCircle((horiPoint7 - (horiPoint1 / 2)), (vertPoint3 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 7) {
                        canvas.drawCircle((horiPoint8 - (horiPoint1 / 2)), (vertPoint3 - (vertPoint1 / 2f)), 50f, paint)
                    }
                }

                else if (i == 3) {
                    if (j == 0) {
                        canvas.drawCircle((horiPoint1 / 2), (vertPoint4 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 1) {
                        canvas.drawCircle((horiPoint2 - (horiPoint1 / 2)), (vertPoint4 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 2) {
                        canvas.drawCircle((horiPoint3 - (horiPoint1 / 2)), (vertPoint4 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 3) {
                        canvas.drawCircle((horiPoint4 - (horiPoint1 / 2)), (vertPoint4 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 4) {
                        canvas.drawCircle((horiPoint5 - (horiPoint1 / 2)), (vertPoint4 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 5) {
                        canvas.drawCircle((horiPoint6 - (horiPoint1 / 2)), (vertPoint4 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 6) {
                        canvas.drawCircle((horiPoint7 - (horiPoint1 / 2)), (vertPoint4 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 7) {
                        canvas.drawCircle((horiPoint8 - (horiPoint1 / 2)), (vertPoint4 - (vertPoint1 / 2f)), 50f, paint)
                    }
                }

                else if (i == 4) {
                    if (j == 0) {
                        canvas.drawCircle((horiPoint1 / 2), (vertPoint5 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 1) {
                        canvas.drawCircle((horiPoint2 - (horiPoint1 / 2)), (vertPoint5 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 2) {
                        canvas.drawCircle((horiPoint3 - (horiPoint1 / 2)), (vertPoint5 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 3) {
                        canvas.drawCircle((horiPoint4 - (horiPoint1 / 2)), (vertPoint5 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 4) {
                        canvas.drawCircle((horiPoint5 - (horiPoint1 / 2)), (vertPoint5 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 5) {
                        canvas.drawCircle((horiPoint6 - (horiPoint1 / 2)), (vertPoint5 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 6) {
                        canvas.drawCircle((horiPoint7 - (horiPoint1 / 2)), (vertPoint5 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 7) {
                        canvas.drawCircle((horiPoint8 - (horiPoint1 / 2)), (vertPoint5 - (vertPoint1 / 2f)), 50f, paint)
                    }
                }

                else if (i == 5) {
                    if (j == 0) {
                        canvas.drawCircle((horiPoint1 / 2), (vertPoint6 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 1) {
                        canvas.drawCircle((horiPoint2 - (horiPoint1 / 2)), (vertPoint6 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 2) {
                        canvas.drawCircle((horiPoint3 - (horiPoint1 / 2)), (vertPoint6 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 3) {
                        canvas.drawCircle((horiPoint4 - (horiPoint1 / 2)), (vertPoint6 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 4) {
                        canvas.drawCircle((horiPoint5 - (horiPoint1 / 2)), (vertPoint6 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 5) {
                        canvas.drawCircle((horiPoint6 - (horiPoint1 / 2)), (vertPoint6 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 6) {
                        canvas.drawCircle((horiPoint7 - (horiPoint1 / 2)), (vertPoint6 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 7) {
                        canvas.drawCircle((horiPoint8 - (horiPoint1 / 2)), (vertPoint6 - (vertPoint1 / 2f)), 50f, paint)
                    }
                }

                else if (i == 6) {
                    if (j == 0) {
                        canvas.drawCircle((horiPoint1 / 2), (vertPoint7 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 1) {
                        canvas.drawCircle((horiPoint2 - (horiPoint1 / 2)), (vertPoint7 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 2) {
                        canvas.drawCircle((horiPoint3 - (horiPoint1 / 2)), (vertPoint7 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 3) {
                        canvas.drawCircle((horiPoint4 - (horiPoint1 / 2)), (vertPoint7 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 4) {
                        canvas.drawCircle((horiPoint5 - (horiPoint1 / 2)), (vertPoint7 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 5) {
                        canvas.drawCircle((horiPoint6 - (horiPoint1 / 2)), (vertPoint7 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 6) {
                        canvas.drawCircle((horiPoint7 - (horiPoint1 / 2)), (vertPoint7 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 7) {
                        canvas.drawCircle((horiPoint8 - (horiPoint1 / 2)), (vertPoint7 - (vertPoint1 / 2f)), 50f, paint)
                    }
                }

                else if (i == 7) {
                    if (j == 0) {
                        canvas.drawCircle((horiPoint1 / 2), (vertPoint8 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 1) {
                        canvas.drawCircle((horiPoint2 - (horiPoint1 / 2)), (vertPoint8 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 2) {
                        canvas.drawCircle((horiPoint3 - (horiPoint1 / 2)), (vertPoint8 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 3) {
                        canvas.drawCircle((horiPoint4 - (horiPoint1 / 2)), (vertPoint8 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 4) {
                        canvas.drawCircle((horiPoint5 - (horiPoint1 / 2)), (vertPoint8 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 5) {
                        canvas.drawCircle((horiPoint6 - (horiPoint1 / 2)), (vertPoint8 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 6) {
                        canvas.drawCircle((horiPoint7 - (horiPoint1 / 2)), (vertPoint8 - (vertPoint1 / 2f)), 50f, paint)
                    }

                    else if (j == 7) {
                        canvas.drawCircle((horiPoint8 - (horiPoint1 / 2)), (vertPoint8 - (vertPoint1 / 2f)), 50f, paint)
                    }
                }
            }
        }
    }

    override fun onDown(e: MotionEvent): Boolean {
        return true
    }

    override fun onShowPress(e: MotionEvent) {
        // not used
    }

    //count how many disks are there for specific player(ask either white or black)
    fun countDisks (player: Boolean): Int {
        //if it asks to count white disks
        if (player) {
            var whiteDisks = 0

            for (i in grid) {
                for (j in i) {
                    if (i[j] == 1) {
                        whiteDisks++
                    }
                }
            }

            return whiteDisks
        }

        //if it asks to count black disks
        else {
            var blackDisks = 0

            for (i in grid) {
                for (j in i) {
                    if (i[j] == 2) {
                        blackDisks++
                    }
                }
            }

            return blackDisks
        }
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        var col = 0
        var row = 0

        // Check which specific column the touch is detected at
        if (e.x < horiPoint1) {
            col = 0
        }

        else if (e.x > horiPoint1 && e.x < horiPoint2) {
            col = 1
        }

        else if (e.x > horiPoint2 && e.x < horiPoint3) {
            col = 2
        }

        else if (e.x > horiPoint3 && e.x < horiPoint4) {
            col = 3
        }

        else if (e.x > horiPoint4 && e.x < horiPoint5) {
            col = 4
        }

        else if (e.x > horiPoint5 && e.x < horiPoint6) {
            col = 5
        }

        else if (e.x > horiPoint6 && e.x < horiPoint7) {
            col = 6
        }

        else if (e.x > horiPoint7 && e.x < horiPoint8) {
            col = 7
        }

        /*
        else if (e.x > horiPoint8 && e.x < horiPoint9) {
            col = 8
        }
        */

        // Now, check which specific row the touch is detected at
        if (e.y < vertPoint1) {
            row = 0
        }

        else if (e.y > vertPoint1 && e.y < vertPoint2) {
            row = 1
        }

        else if (e.y > vertPoint2 && e.y < vertPoint3) {
            row = 2
        }

        else if (e.y > vertPoint3 && e.y < vertPoint4) {
            row = 3
        }

        else if (e.y > vertPoint4 && e.y < vertPoint5) {
            row = 4
        }

        else if (e.y > vertPoint5 && e.y < vertPoint6) {
            row = 5
        }

        else if (e.y > vertPoint6 && e.y < vertPoint7) {
            row = 6
        }

        else if (e.y > vertPoint7 && e.y < vertPoint8) {
            row = 7
        }

        /*
        else if (e.y > vertPoint8 && e.y < vertPoint9) {
            row = 8
        }
        */


        //Now, check if grid that user tapped is valid move or not

        //First, check if game is commenced or not. If not, there is no point of checking
        //since there is no game going on.
        if (gameStarted) {
            //if it's white disk's turn
            if (whosTurn == true) {

                //first, check if white disk has any valid move available.
                //if no, set 'whiteDone' variable as true to indicate that white disk
                //don't have anymore valid moves to makes and give black disk a turn
                if (!checkRemainingValidMoves(whosTurn)) {
                    whiteDone = true
                    whosTurn = false
                    validMoveMade = false
                }

                //if there is valid move available for white disk, let the white disk play its turn
                else {
                    //Use a recursive function to check if it's valid move or not
                    //once the valid move has been made, use boolean variable to switch the player turn
                    //keep the player turn as it is until valid move has been made
                    if (row > 0 && row < 7) {
                        if (col > 0 && col < 7) {

                            //check if top-left grid has black disk
                            if (grid[row - 1][col - 1] == 2) {

                                //if valid move is detected at top-left diagonal direction line,
                                //flip all the black disks between selected grid to the white disk at the end of top-left diagonal direction line
                                //and place white disk at selected grid
                                if (checkValidTopLeft(whosTurn, row, col)) {
                                    changeTopLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if top grid has black disk
                            if (grid[row - 1][col] == 2) {

                                //if valid move is detected at top direction line,
                                //flip all the black disks between selected grid to the white disk at the end of top direction line
                                //and place white disk at selected grid
                                if (checkValidTop(whosTurn, row, col)) {
                                    changeTop(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if top-right grid has black disk
                            if (grid[row - 1][col + 1] == 2) {

                                //if valid move is detected at top-right diagonal direction line,
                                //flip all the black disks between selected grid to the white disk at the end of top-right diagonal direction line
                                //and place white disk at selected grid
                                if (checkValidTopRight(whosTurn, row, col)) {
                                    changeTopRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if left grid has black disk
                            if (grid[row][col - 1] == 2) {

                                //if valid move is detected at left direction line,
                                //flip all the black disks between selected grid to the white disk at the end of left direction line
                                //and place white disk at selected grid
                                if (checkValidLeft(whosTurn, row, col)) {
                                    changeLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if right grid has black disk
                            if (grid[row][col + 1] == 2) {

                                //if valid move is detected at right direction line,
                                //flip all the black disks between selected grid to the white disk at the end of right direction line
                                //and place white disk at selected grid
                                if (checkValidRight(whosTurn, row, col)) {
                                    changeRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom-left grid has black disk
                            if (grid[row + 1][col - 1] == 2) {

                                //if valid move is detected at bottom-left diagonal direction line,
                                //flip all the black disks between selected grid to the white disk at the end of bottom-left diagonal direction line
                                //and place white disk at selected grid
                                if (checkValidBottomLeft(whosTurn, row, col)) {
                                    changeBottomLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom grid has black disk
                            if (grid[row + 1][col] == 2) {

                                //if valid move is detected at bottom direction line,
                                //flip all the black disks between selected grid to the white disk at the end of bottom direction line
                                //and place white disk at selected grid
                                if (checkValidBottom(whosTurn, row, col)) {
                                    changeBottom(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom-right grid has black disk
                            if (grid[row + 1][col + 1] == 2) {

                                //if valid move is detected at bottom-right diagonal direction line,
                                //flip all the black disks between selected grid to the white disk at the end of bottom-right diagonal direction line
                                //and place white disk at selected grid
                                if (checkValidBottomRight(whosTurn, row, col)) {
                                    changeBottomRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        } else if (col == 0) {

                            //check if top grid has black disk
                            if (grid[row - 1][col] == 2) {

                                //if valid move is detected at top direction line,
                                //flip all the black disks between selected grid to the white disk at the end of top direction line
                                //and place white disk at selected grid
                                if (checkValidTop(whosTurn, row, col)) {
                                    changeTop(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if top-right grid has black disk
                            if (grid[row - 1][col + 1] == 2) {

                                //if valid move is detected at top-right diagonal direction line,
                                //flip all the black disks between selected grid to the white disk at the end of top-right diagonal direction line
                                //and place white disk at selected grid
                                if (checkValidTopRight(whosTurn, row, col)) {
                                    changeTopRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if right grid has black disk
                            if (grid[row][col + 1] == 2) {

                                //if valid move is detected at right direction line,
                                //flip all the black disks between selected grid to the white disk at the end of right direction line
                                //and place white disk at selected grid
                                if (checkValidRight(whosTurn, row, col)) {
                                    changeRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom grid has black disk
                            if (grid[row + 1][col] == 2) {

                                //if valid move is detected at bottom direction line,
                                //flip all the black disks between selected grid to the white disk at the end of bottom direction line
                                //and place white disk at selected grid
                                if (checkValidBottom(whosTurn, row, col)) {
                                    changeBottom(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom-right grid has black disk
                            if (grid[row + 1][col + 1] == 2) {

                                //if valid move is detected at bottom-right diagonal direction line,
                                //flip all the black disks between selected grid to the white disk at the end of bottom-right diagonal direction line
                                //and place white disk at selected grid
                                if (checkValidBottomRight(whosTurn, row, col)) {
                                    changeBottomRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        } else if (col == 7) {
                            //check if top-left grid has black disk
                            if (grid[row - 1][col - 1] == 2) {

                                //if valid move is detected at top-left diagonal direction line,
                                //flip all the black disks between selected grid to the white disk at the end of top-left diagonal direction line
                                //and place white disk at selected grid
                                if (checkValidTopLeft(whosTurn, row, col)) {
                                    changeTopLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if top grid has black disk
                            if (grid[row - 1][col] == 2) {

                                //if valid move is detected at top direction line,
                                //flip all the black disks between selected grid to the white disk at the end of top direction line
                                //and place white disk at selected grid
                                if (checkValidTop(whosTurn, row, col)) {
                                    changeTop(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if left grid has black disk
                            if (grid[row][col - 1] == 2) {

                                //if valid move is detected at left direction line,
                                //flip all the black disks between selected grid to the white disk at the end of left direction line
                                //and place white disk at selected grid
                                if (checkValidLeft(whosTurn, row, col)) {
                                    changeLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom-left grid has black disk
                            if (grid[row + 1][col - 1] == 2) {

                                //if valid move is detected at bottom-left diagonal direction line,
                                //flip all the black disks between selected grid to the white disk at the end of bottom-left diagonal direction line
                                //and place white disk at selected grid
                                if (checkValidBottomLeft(whosTurn, row, col)) {
                                    changeBottomLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom grid has black disk
                            if (grid[row + 1][col] == 2) {

                                //if valid move is detected at bottom direction line,
                                //flip all the black disks between selected grid to the white disk at the end of bottom direction line
                                //and place white disk at selected grid
                                if (checkValidBottom(whosTurn, row, col)) {
                                    changeBottom(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        }

                    }

                    else if (row == 0) {
                        if (col > 0 && col < 7) {

                            //check if left grid has black disk
                            if (grid[row][col - 1] == 2) {

                                //if valid move is detected at left direction line,
                                //flip all the black disks between selected grid to the white disk at the end of left direction line
                                //and place white disk at selected grid
                                if (checkValidLeft(whosTurn, row, col)) {
                                    changeLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if right grid has black disk
                            if (grid[row][col + 1] == 2) {

                                //if valid move is detected at right direction line,
                                //flip all the black disks between selected grid to the white disk at the end of right direction line
                                //and place white disk at selected grid
                                if (checkValidRight(whosTurn, row, col)) {
                                    changeRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom-left grid has black disk
                            if (grid[row + 1][col - 1] == 2) {

                                //if valid move is detected at bottom-left diagonal direction line,
                                //flip all the black disks between selected grid to the white disk at the end of bottom-left diagonal direction line
                                //and place white disk at selected grid
                                if (checkValidBottomLeft(whosTurn, row, col)) {
                                    changeBottomLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom grid has black disk
                            if (grid[row + 1][col] == 2) {

                                //if valid move is detected at bottom direction line,
                                //flip all the black disks between selected grid to the white disk at the end of bottom direction line
                                //and place white disk at selected grid
                                if (checkValidBottom(whosTurn, row, col)) {
                                    changeBottom(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom-right grid has black disk
                            if (grid[row + 1][col + 1] == 2) {

                                //if valid move is detected at bottom-right diagonal direction line,
                                //flip all the black disks between selected grid to the white disk at the end of bottom-right diagonal direction line
                                //and place white disk at selected grid
                                if (checkValidBottomRight(whosTurn, row, col)) {
                                    changeBottomRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        } else if (col == 0) {

                            //check if right grid has black disk
                            if (grid[row][col + 1] == 2) {

                                //if valid move is detected at right direction line,
                                //flip all the black disks between selected grid to the white disk at the end of right direction line
                                //and place white disk at selected grid
                                if (checkValidRight(whosTurn, row, col)) {
                                    changeRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom grid has black disk
                            if (grid[row + 1][col] == 2) {

                                //if valid move is detected at bottom direction line,
                                //flip all the black disks between selected grid to the white disk at the end of bottom direction line
                                //and place white disk at selected grid
                                if (checkValidBottom(whosTurn, row, col)) {
                                    changeBottom(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom-right grid has black disk
                            if (grid[row + 1][col + 1] == 2) {

                                //if valid move is detected at bottom-right diagonal direction line,
                                //flip all the black disks between selected grid to the white disk at the end of bottom-right diagonal direction line
                                //and place white disk at selected grid
                                if (checkValidBottomRight(whosTurn, row, col)) {
                                    changeBottomRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        } else if (col == 7) {

                            //check if left grid has black disk
                            if (grid[row][col - 1] == 2) {

                                //if valid move is detected at left direction line,
                                //flip all the black disks between selected grid to the white disk at the end of left direction line
                                //and place white disk at selected grid
                                if (checkValidLeft(whosTurn, row, col)) {
                                    changeLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom-left grid has black disk
                            if (grid[row + 1][col - 1] == 2) {

                                //if valid move is detected at bottom-left diagonal direction line,
                                //flip all the black disks between selected grid to the white disk at the end of bottom-left diagonal direction line
                                //and place white disk at selected grid
                                if (checkValidBottomLeft(whosTurn, row, col)) {
                                    changeBottomLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom grid has black disk
                            if (grid[row + 1][col] == 2) {

                                //if valid move is detected at bottom direction line,
                                //flip all the black disks between selected grid to the white disk at the end of bottom direction line
                                //and place white disk at selected grid
                                if (checkValidBottom(whosTurn, row, col)) {
                                    changeBottom(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        }
                    }

                    else if (row == 7) {
                        if (col > 0 && col < 7) {

                            //check if top-left grid has black disk
                            if (grid[row - 1][col - 1] == 2) {

                                //if valid move is detected at top-left diagonal direction line,
                                //flip all the black disks between selected grid to the white disk at the end of top-left diagonal direction line
                                //and place white disk at selected grid
                                if (checkValidTopLeft(whosTurn, row, col)) {
                                    changeTopLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if top grid has black disk
                            if (grid[row - 1][col] == 2) {

                                //if valid move is detected at top direction line,
                                //flip all the black disks between selected grid to the white disk at the end of top direction line
                                //and place white disk at selected grid
                                if (checkValidTop(whosTurn, row, col)) {
                                    changeTop(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if top-right grid has black disk
                            if (grid[row - 1][col + 1] == 2) {

                                //if valid move is detected at top-right diagonal direction line,
                                //flip all the black disks between selected grid to the white disk at the end of top-right diagonal direction line
                                //and place white disk at selected grid
                                if (checkValidTopRight(whosTurn, row, col)) {
                                    changeTopRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if left grid has black disk
                            if (grid[row][col - 1] == 2) {

                                //if valid move is detected at left direction line,
                                //flip all the black disks between selected grid to the white disk at the end of left direction line
                                //and place white disk at selected grid
                                if (checkValidLeft(whosTurn, row, col)) {
                                    changeLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if right grid has black disk
                            if (grid[row][col + 1] == 2) {

                                //if valid move is detected at right direction line,
                                //flip all the black disks between selected grid to the white disk at the end of right direction line
                                //and place white disk at selected grid
                                if (checkValidRight(whosTurn, row, col)) {
                                    changeRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        } else if (col == 0) {

                            //check if top grid has black disk
                            if (grid[row - 1][col] == 2) {

                                //if valid move is detected at top direction line,
                                //flip all the black disks between selected grid to the white disk at the end of top direction line
                                //and place white disk at selected grid
                                if (checkValidTop(whosTurn, row, col)) {
                                    changeTop(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if top-right grid has black disk
                            if (grid[row - 1][col + 1] == 2) {

                                //if valid move is detected at top-right diagonal direction line,
                                //flip all the black disks between selected grid to the white disk at the end of top-right diagonal direction line
                                //and place white disk at selected grid
                                if (checkValidTopRight(whosTurn, row, col)) {
                                    changeTopRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if right grid has black disk
                            if (grid[row][col + 1] == 2) {

                                //if valid move is detected at right direction line,
                                //flip all the black disks between selected grid to the white disk at the end of right direction line
                                //and place white disk at selected grid
                                if (checkValidRight(whosTurn, row, col)) {
                                    changeRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        } else if (col == 7) {
                            //check if top-left grid has black disk
                            if (grid[row - 1][col - 1] == 2) {

                                //if valid move is detected at top-left diagonal direction line,
                                //flip all the black disks between selected grid to the white disk at the end of top-left diagonal direction line
                                //and place white disk at selected grid
                                if (checkValidTopLeft(whosTurn, row, col)) {
                                    changeTopLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if top grid has black disk
                            if (grid[row - 1][col] == 2) {

                                //if valid move is detected at top direction line,
                                //flip all the black disks between selected grid to the white disk at the end of top direction line
                                //and place white disk at selected grid
                                if (checkValidTop(whosTurn, row, col)) {
                                    changeTop(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if left grid has black disk
                            if (grid[row][col - 1] == 2) {

                                //if valid move is detected at left direction line,
                                //flip all the black disks between selected grid to the white disk at the end of left direction line
                                //and place white disk at selected grid
                                if (checkValidLeft(whosTurn, row, col)) {
                                    changeLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        }
                    }

                    //check to see if both the valid move and the disk changes were made using the boolean variable 'validMoveMade'
                    //if yes, reset this boolean variable back to false
                    //this is used to check if player has made valid move and the disk changes has completed.
                    //once the disk change is finished, this boolean variable will be used to check if it's ready
                    //to change player turn.
                    if (validMoveMade) {
                        grid[row][col] = 1
                        whosTurn = false
                        validMoveMade = false
                    }
                }
            }

            //if it's black disk's turn
            else {
                //first, check if black disk has any valid move available.
                //if no, set 'blackDone' variable as true to indicate that black disk
                //don't have anymore valid moves to makes and give white disk a turn
                if (!checkRemainingValidMoves(whosTurn)) {
                    blackDone = true
                    whosTurn = true
                    validMoveMade = false
                }

                else {
                    //Use a recursive function to check if it's valid move or not
                    //once the valid move has been made, use boolean variable to switch the player turn
                    //keep the player turn as it is until valid move has been made
                    if (row > 0 && row < 7) {
                        if (col > 0 && col < 7) {

                            //check if top-left grid has white disk
                            if (grid[row - 1][col - 1] == 1) {

                                //if valid move is detected at top-left diagonal direction line,
                                //flip all the white disks between selected grid to the black disk at the end of top-left diagonal direction line
                                //and place black disk at selected grid
                                if (checkValidTopLeft(whosTurn, row, col)) {
                                    changeTopLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if top grid has white disk
                            if (grid[row - 1][col] == 1) {

                                //if valid move is detected at top direction line,
                                //flip all the white disks between selected grid to the black disk at the end of top direction line
                                //and place black disk at selected grid
                                if (checkValidTop(whosTurn, row, col)) {
                                    changeTop(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if top-right grid has white disk
                            if (grid[row - 1][col + 1] == 1) {

                                //if valid move is detected at top-right diagonal direction line,
                                //flip all the white disks between selected grid to the black disk at the end of top-right diagonal direction line
                                //and place black disk at selected grid
                                if (checkValidTopRight(whosTurn, row, col)) {
                                    changeTopRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if left grid has white disk
                            if (grid[row][col - 1] == 1) {

                                //if valid move is detected at left direction line,
                                //flip all the white disks between selected grid to the black disk at the end of left direction line
                                //and place black disk at selected grid
                                if (checkValidLeft(whosTurn, row, col)) {
                                    changeLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if right grid has white disk
                            if (grid[row][col + 1] == 1) {

                                //if valid move is detected at right direction line,
                                //flip all the white disks between selected grid to the black disk at the end of right direction line
                                //and place black disk at selected grid
                                if (checkValidRight(whosTurn, row, col)) {
                                    changeRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom-left grid has white disk
                            if (grid[row + 1][col - 1] == 1) {

                                //if valid move is detected at bottom-left diagonal direction line,
                                //flip all the white disks between selected grid to the black disk at the end of bottom-left diagonal direction line
                                //and place black disk at selected grid
                                if (checkValidBottomLeft(whosTurn, row, col)) {
                                    changeBottomLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom grid has white disk
                            if (grid[row + 1][col] == 1) {

                                //if valid move is detected at bottom direction line,
                                //flip all the white disks between selected grid to the black disk at the end of bottom direction line
                                //and place black disk at selected grid
                                if (checkValidBottom(whosTurn, row, col)) {
                                    changeBottom(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom-right grid has white disk
                            if (grid[row + 1][col + 1] == 1) {

                                //if valid move is detected at bottom-right diagonal direction line,
                                //flip all the white disks between selected grid to the black disk at the end of bottom-right diagonal direction line
                                //and place black disk at selected grid
                                if (checkValidBottomRight(whosTurn, row, col)) {
                                    changeBottomRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        } else if (col == 0) {

                            //check if top grid has white disk
                            if (grid[row - 1][col] == 1) {

                                //if valid move is detected at top direction line,
                                //flip all the white disks between selected grid to the black disk at the end of top direction line
                                //and place black disk at selected grid
                                if (checkValidTop(whosTurn, row, col)) {
                                    changeTop(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if top-right grid has white disk
                            if (grid[row - 1][col + 1] == 1) {

                                //if valid move is detected at top-right diagonal direction line,
                                //flip all the white disks between selected grid to the black disk at the end of top-right diagonal direction line
                                //and place black disk at selected grid
                                if (checkValidTopRight(whosTurn, row, col)) {
                                    changeTopRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if right grid has white disk
                            if (grid[row][col + 1] == 1) {

                                //if valid move is detected at right direction line,
                                //flip all the white disks between selected grid to the black disk at the end of right direction line
                                //and place black disk at selected grid
                                if (checkValidRight(whosTurn, row, col)) {
                                    changeRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom grid has white disk
                            if (grid[row + 1][col] == 1) {

                                //if valid move is detected at bottom direction line,
                                //flip all the white disks between selected grid to the black disk at the end of bottom direction line
                                //and place black disk at selected grid
                                if (checkValidBottom(whosTurn, row, col)) {
                                    changeBottom(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom-right grid has white disk
                            if (grid[row + 1][col + 1] == 1) {

                                //if valid move is detected at bottom-right diagonal direction line,
                                //flip all the white disks between selected grid to the black disk at the end of bottom-right diagonal direction line
                                //and place black disk at selected grid
                                if (checkValidBottomRight(whosTurn, row, col)) {
                                    changeBottomRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        } else if (col == 7) {
                            //check if top-left grid has white disk
                            if (grid[row - 1][col - 1] == 1) {

                                //if valid move is detected at top-left diagonal direction line,
                                //flip all the white disks between selected grid to the black disk at the end of top-left diagonal direction line
                                //and place black disk at selected grid
                                if (checkValidTopLeft(whosTurn, row, col)) {
                                    changeTopLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if top grid has white disk
                            if (grid[row - 1][col] == 1) {

                                //if valid move is detected at top direction line,
                                //flip all the white disks between selected grid to the black disk at the end of top direction line
                                //and place black disk at selected grid
                                if (checkValidTop(whosTurn, row, col)) {
                                    changeTop(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if left grid has white disk
                            if (grid[row][col - 1] == 1) {

                                //if valid move is detected at left direction line,
                                //flip all the white disks between selected grid to the black disk at the end of left direction line
                                //and place black disk at selected grid
                                if (checkValidLeft(whosTurn, row, col)) {
                                    changeLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom-left grid has white disk
                            if (grid[row + 1][col - 1] == 1) {

                                //if valid move is detected at bottom-left diagonal direction line,
                                //flip all the white disks between selected grid to the black disk at the end of bottom-left diagonal direction line
                                //and place black disk at selected grid
                                if (checkValidBottomLeft(whosTurn, row, col)) {
                                    changeBottomLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom grid has white disk
                            if (grid[row + 1][col] == 1) {

                                //if valid move is detected at bottom direction line,
                                //flip all the white disks between selected grid to the black disk at the end of bottom direction line
                                //and place black disk at selected grid
                                if (checkValidBottom(whosTurn, row, col)) {
                                    changeBottom(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        }

                    }

                    else if (row == 0) {
                        if (col > 0 && col < 7) {

                            //check if left grid has white disk
                            if (grid[row][col - 1] == 1) {

                                //if valid move is detected at left direction line,
                                //flip all the white disks between selected grid to the black disk at the end of left direction line
                                //and place black disk at selected grid
                                if (checkValidLeft(whosTurn, row, col)) {
                                    changeLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if right grid has white disk
                            if (grid[row][col + 1] == 1) {

                                //if valid move is detected at right direction line,
                                //flip all the white disks between selected grid to the black disk at the end of right direction line
                                //and place black disk at selected grid
                                if (checkValidRight(whosTurn, row, col)) {
                                    changeRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom-left grid has white disk
                            if (grid[row + 1][col - 1] == 1) {

                                //if valid move is detected at bottom-left diagonal direction line,
                                //flip all the white disks between selected grid to the black disk at the end of bottom-left diagonal direction line
                                //and place black disk at selected grid
                                if (checkValidBottomLeft(whosTurn, row, col)) {
                                    changeBottomLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom grid has white disk
                            if (grid[row + 1][col] == 1) {

                                //if valid move is detected at bottom direction line,
                                //flip all the white disks between selected grid to the black disk at the end of bottom direction line
                                //and place black disk at selected grid
                                if (checkValidBottom(whosTurn, row, col)) {
                                    changeBottom(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom-right grid has white disk
                            if (grid[row + 1][col + 1] == 1) {

                                //if valid move is detected at bottom-right diagonal direction line,
                                //flip all the white disks between selected grid to the black disk at the end of bottom-right diagonal direction line
                                //and place black disk at selected grid
                                if (checkValidBottomRight(whosTurn, row, col)) {
                                    changeBottomRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        } else if (col == 0) {

                            //check if right grid has white disk
                            if (grid[row][col + 1] == 1) {

                                //if valid move is detected at right direction line,
                                //flip all the white disks between selected grid to the black disk at the end of right direction line
                                //and place black disk at selected grid
                                if (checkValidRight(whosTurn, row, col)) {
                                    changeRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom grid has white disk
                            if (grid[row + 1][col] == 1) {

                                //if valid move is detected at bottom direction line,
                                //flip all the white disks between selected grid to the black disk at the end of bottom direction line
                                //and place black disk at selected grid
                                if (checkValidBottom(whosTurn, row, col)) {
                                    changeBottom(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom-right grid has white disk
                            if (grid[row + 1][col + 1] == 1) {

                                //if valid move is detected at bottom-right diagonal direction line,
                                //flip all the white disks between selected grid to the black disk at the end of bottom-right diagonal direction line
                                //and place black disk at selected grid
                                if (checkValidBottomRight(whosTurn, row, col)) {
                                    changeBottomRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        } else if (col == 7) {

                            //check if left grid has white disk
                            if (grid[row][col - 1] == 1) {

                                //if valid move is detected at left direction line,
                                //flip all the white disks between selected grid to the black disk at the end of left direction line
                                //and place black disk at selected grid
                                if (checkValidLeft(whosTurn, row, col)) {
                                    changeLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom-left grid has white disk
                            if (grid[row + 1][col - 1] == 1) {

                                //if valid move is detected at bottom-left diagonal direction line,
                                //flip all the white disks between selected grid to the black disk at the end of bottom-left diagonal direction line
                                //and place black disk at selected grid
                                if (checkValidBottomLeft(whosTurn, row, col)) {
                                    changeBottomLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if bottom grid has white disk
                            if (grid[row + 1][col] == 1) {

                                //if valid move is detected at bottom direction line,
                                //flip all the white disks between selected grid to the black disk at the end of bottom direction line
                                //and place black disk at selected grid
                                if (checkValidBottom(whosTurn, row, col)) {
                                    changeBottom(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        }
                    }

                    else if (row == 7) {
                        if (col > 0 && col < 7) {

                            //check if top-left grid has white disk
                            if (grid[row - 1][col - 1] == 1) {

                                //if valid move is detected at top-left diagonal direction line,
                                //flip all the white disks between selected grid to the black disk at the end of top-left diagonal direction line
                                //and place black disk at selected grid
                                if (checkValidTopLeft(whosTurn, row, col)) {
                                    changeTopLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if top grid has white disk
                            if (grid[row - 1][col] == 1) {

                                //if valid move is detected at top direction line,
                                //flip all the white disks between selected grid to the black disk at the end of top direction line
                                //and place black disk at selected grid
                                if (checkValidTop(whosTurn, row, col)) {
                                    changeTop(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if top-right grid has white disk
                            if (grid[row - 1][col + 1] == 1) {

                                //if valid move is detected at top-right diagonal direction line,
                                //flip all the white disks between selected grid to the black disk at the end of top-right diagonal direction line
                                //and place black disk at selected grid
                                if (checkValidTopRight(whosTurn, row, col)) {
                                    changeTopRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if left grid has white disk
                            if (grid[row][col - 1] == 1) {

                                //if valid move is detected at left direction line,
                                //flip all the white disks between selected grid to the black disk at the end of left direction line
                                //and place black disk at selected grid
                                if (checkValidLeft(whosTurn, row, col)) {
                                    changeLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if right grid has white disk
                            if (grid[row][col + 1] == 1) {

                                //if valid move is detected at right direction line,
                                //flip all the white disks between selected grid to the black disk at the end of right direction line
                                //and place black disk at selected grid
                                if (checkValidRight(whosTurn, row, col)) {
                                    changeRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        } else if (col == 0) {

                            //check if top grid has white disk
                            if (grid[row - 1][col] == 1) {

                                //if valid move is detected at top direction line,
                                //flip all the white disks between selected grid to the black disk at the end of top direction line
                                //and place black disk at selected grid
                                if (checkValidTop(whosTurn, row, col)) {
                                    changeTop(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if top-right grid has white disk
                            if (grid[row - 1][col + 1] == 1) {

                                //if valid move is detected at top-right diagonal direction line,
                                //flip all the white disks between selected grid to the black disk at the end of top-right diagonal direction line
                                //and place black disk at selected grid
                                if (checkValidTopRight(whosTurn, row, col)) {
                                    changeTopRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if right grid has white disk
                            if (grid[row][col + 1] == 1) {

                                //if valid move is detected at right direction line,
                                //flip all the white disks between selected grid to the black disk at the end of right direction line
                                //and place black disk at selected grid
                                if (checkValidRight(whosTurn, row, col)) {
                                    changeRight(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        } else if (col == 7) {
                            //check if top-left grid has white disk
                            if (grid[row - 1][col - 1] == 1) {

                                //if valid move is detected at top-left diagonal direction line,
                                //flip all the white disks between selected grid to the black disk at the end of top-left diagonal direction line
                                //and place black disk at selected grid
                                if (checkValidTopLeft(whosTurn, row, col)) {
                                    changeTopLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if top grid has white disk
                            if (grid[row - 1][col] == 1) {

                                //if valid move is detected at top direction line,
                                //flip all the white disks between selected grid to the black disk at the end of top direction line
                                //and place black disk at selected grid
                                if (checkValidTop(whosTurn, row, col)) {
                                    changeTop(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }

                            //check if left grid has white disk
                            if (grid[row][col - 1] == 1) {

                                //if valid move is detected at left direction line,
                                //flip all the white disks between selected grid to the black disk at the end of left direction line
                                //and place black disk at selected grid
                                if (checkValidLeft(whosTurn, row, col)) {
                                    changeLeft(whosTurn, row, col)
                                    validMoveMade = true
                                }
                            }
                        }
                    }

                    //check to see if both the valid move and the disk changes were made using the boolean variable 'validMoveMade'
                    //if yes, reset this boolean variable back to false
                    //this is used to check if player has made valid move and the disk changes has completed.
                    //once the disk change is finished, this boolean variable will be used to check if it's ready
                    //to change player turn.
                    if (validMoveMade) {
                        grid[row][col] = 2
                        whosTurn = true
                        validMoveMade = false
                    }
                }

            }

            //once the white and black disk runs out of valid moves, the game will be finished
            //and will calculate the disks to see who's the winner
            if (whiteDone && blackDone) {
                gameStarted = false
            }
        }

        //MainActivity().updateDisplay()
        //*I need to call updateDisplay to update the game data but calling it here causes the app to crash*

        invalidate()
        return true
    }




    //recursive functions used to check if user's move is valid or not

    //recursive function for checking on top direction
    fun checkValidTop(user: Boolean, r: Int, c: Int): Boolean {
        var isitValid : Boolean = false

        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (r > 0) {
                //check if top grid has black disk. If yes, keep recursively call the function
                if (grid[r-1][c] == 2) {
                    isitValid = checkValidTop(user, r - 1, c)
                }

                //check if top grid has white disk. If yes, return true to indicate that it's a valid move
                else if (grid[r-1][c] == 1) {
                    return true
                }

                //check if top grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r-1][c] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither white disk or empty grid was still found,
            //then top direction is invalid
            else if (r <= 0) {
                return false
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (r > 0) {
                //check if top grid has white disk. If yes, keep recursively call the function
                if (grid[r-1][c] == 1) {
                    isitValid = checkValidTop(user, r - 1, c)
                }

                //check if top grid has black disk. If yes, return true to indicate that it's a valid move
                else if (grid[r-1][c] == 2) {
                    return true
                }

                //check if top grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r-1][c] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither black disk or empty grid was still found,
            //then top direction is invalid
            else if (r <= 0) {
                return false
            }
        }

        return isitValid
    }

    //recursive function for checking on top-left direction
    fun checkValidTopLeft(user: Boolean, r: Int, c: Int): Boolean {
        var isitValid : Boolean = false

        //if it's white disk's turn
        if (user == true) {
            if (r > 0 && c > 0) {
                //check if top-left grid has black disk. If yes, keep recursively call the function
                if (grid[r-1][c-1] == 2) {
                    isitValid = checkValidTopLeft(user, r - 1, c - 1)
                }

                //check if top-left grid has white disk. If yes, return true to indicate that it's a valid move
                else if (grid[r-1][c-1] == 1) {
                    return true
                }

                //check if top-left grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r-1][c-1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither white disk or empty grid was still found,
            //then top-left direction is invalid
            else if (r <= 0 || c <= 0) {
                return false
            }
        }

        //if it's black disk's turn
        else {
            if (r > 0 && c > 0) {
                //check if top-left grid has white disk. If yes, keep recursively call the function
                if (grid[r-1][c-1] == 1) {
                    isitValid = checkValidTopLeft(user, r - 1, c - 1)
                }

                //check if top-left grid has black disk. If yes, return true to indicate that it's a valid move
                else if (grid[r-1][c-1] == 2) {
                    return true
                }

                //check if top-left grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r-1][c-1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither black disk or empty grid was still found,
            //then top-left direction is invalid
            else if (r <= 0 || c <= 0) {
                return false
            }
        }

        return isitValid
    }

    //recursive function for checking on top-right direction
    fun checkValidTopRight(user: Boolean, r: Int, c: Int): Boolean {
        var isitValid : Boolean = false

        //if it's white disk's turn
        if (user == true) {
            if (r > 0 && c < 7) {
                //check if top-right grid has black disk. If yes, keep recursively call the function
                if (grid[r-1][c+1] == 2) {
                    isitValid = checkValidTopRight(user, r - 1, c + 1)
                }

                //check if top-right grid has white disk. If yes, return true to indicate that it's a valid move
                else if (grid[r-1][c+1] == 1) {
                    return true
                }

                //check if top-right is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r-1][c+1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither white disk or empty grid was still found,
            //then top-right direction is invalid
            else if (r <= 0 || c >= 7) {
                return false
            }
        }

        //if it's black disk's turn
        else {
            if (r > 0 && c < 7) {
                //check if top-right grid has white disk. If yes, keep recursively call the function
                if (grid[r-1][c+1] == 1) {
                    isitValid = checkValidTopRight(user, r - 1, c + 1)
                }

                //check if top-right grid has black disk. If yes, return true to indicate that it's a valid move
                else if (grid[r-1][c+1] == 2) {
                    return true
                }

                //check if top-right is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r-1][c+1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither black disk or empty grid was still found,
            //then top-right direction is invalid
            else if (r <= 0 || c >= 7) {
                return false
            }
        }

        return isitValid
    }

    //recursive function for checking on left direction
    fun checkValidLeft(user: Boolean, r: Int, c: Int): Boolean {
        var isitValid : Boolean = false

        //if it's white disk's turn
        if (user == true) {
            if (c > 0) {
                //check if left grid has black disk. If yes, keep recursively call the function
                if (grid[r][c-1] == 2) {
                    isitValid = checkValidLeft(user, r, c - 1)
                }

                //check if left grid has white disk. If yes, return true to indicate that it's a valid move
                else if (grid[r][c-1] == 1) {
                    return true
                }

                //check if left grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r][c-1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither white disk or empty grid was still found,
            //then left direction is invalid
            else if (c <= 0) {
                return false
            }
        }

        //if it's black disk's turn
        else {
            if (c > 0) {
                //check if left grid has white disk. If yes, keep recursively call the function
                if (grid[r][c-1] == 1) {
                    isitValid = checkValidLeft(user, r, c - 1)
                }

                //check if left grid has black disk. If yes, return true to indicate that it's a valid move
                else if (grid[r][c-1] == 2) {
                    return true
                }

                //check if left grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r][c-1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither black disk or empty grid was still found,
            //then left direction is invalid
            else if (c <= 0) {
                return false
            }
        }

        return isitValid
    }

    //recursive function for checking on right direction
    fun checkValidRight(user: Boolean, r: Int, c: Int): Boolean {
        var isitValid : Boolean = false

        //if it's white disk's turn
        if (user == true) {
            if (c < 7) {
                //check if right grid has black disk. If yes, keep recursively call the function
                if (grid[r][c+1] == 2) {
                    isitValid = checkValidRight(user, r, c + 1)
                }

                //check if right grid has white disk. If yes, return true to indicate that it's a valid move
                else if (grid[r][c+1] == 1) {
                    return true
                }

                //check if right grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r][c+1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither white disk or empty grid was still found,
            //then right direction is invalid
            else if (c >= 7) {
                return false
            }
        }

        //if it's black disk's turn
        else {
            if (c < 7) {
                //check if right grid has white disk. If yes, keep recursively call the function
                if (grid[r][c+1] == 1) {
                    isitValid = checkValidRight(user, r, c + 1)
                }

                //check if right grid has black disk. If yes, return true to indicate that it's a valid move
                else if (grid[r][c+1] == 2) {
                    return true
                }


                //check if right grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r][c+1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither black disk or empty grid was still found,
            //then right direction is invalid
            else if (c >= 7) {
                return false
            }
        }

        return isitValid
    }

    //recursive function for checking on bottom direction
    fun checkValidBottom(user: Boolean, r: Int, c: Int): Boolean {
        var isitValid : Boolean = false

        //if it's white disk's turn
        if (user == true) {
            if (r < 7) {
                //check if bottom grid has black disk. If yes, keep recursively call the function
                if (grid[r+1][c] == 2) {
                    isitValid = checkValidBottom(user, r + 1, c)
                }

                //check if bottom grid has white disk. If yes, return true to indicate that it's a valid move
                else if (grid[r+1][c] == 1) {
                    return true
                }

                //check if bottom grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r+1][c] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither white disk or empty grid was still found,
            //then bottom direction is invalid
            else if (r >= 7) {
                return false
            }
        }

        //if it's black disk's turn
        else {
            if (r < 7) {
                //check if bottom grid has white disk. If yes, keep recursively call the function
                if (grid[r+1][c] == 1) {
                    isitValid = checkValidBottom(user, r + 1, c)
                }

                //check if bottom grid has black disk. If yes, return true to indicate that it's a valid move
                else if (grid[r+1][c] == 2) {
                    return true
                }

                //check if bottom grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r+1][c] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither black disk or empty grid was still found,
            //then bottom direction is invalid
            else if (r >= 7) {
                return false
            }
        }

        return isitValid
    }

    //recursive function for checking on bottom-left direction
    fun checkValidBottomLeft(user: Boolean, r: Int, c: Int): Boolean {
        var isitValid : Boolean = false

        //if it's white disk's turn
        if (user == true) {
            if (r < 7 && c > 0) {
                //check if bottom-left grid has black disk. If yes, keep recursively call the function
                if (grid[r+1][c-1] == 2) {
                    isitValid = checkValidBottomLeft(user, r + 1, c - 1)
                }

                //check if bottom-left grid has white disk. If yes, return true to indicate that it's a valid move
                else if (grid[r+1][c-1] == 1) {
                    return true
                }

                //check if bottom-left grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r+1][c-1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither white disk or empty grid was still found,
            //then bottom-left direction is invalid
            else if (r >= 7 || c <= 0) {
                return false
            }
        }

        //if it's black disk's turn
        else {
            if (r < 7 && c > 0) {
                //check if bottom-left grid has white disk. If yes, keep recursively call the function
                if (grid[r+1][c-1] == 1) {
                    isitValid = checkValidBottomLeft(user, r + 1, c - 1)
                }

                //check if bottom-left grid has black disk. If yes, return true to indicate that it's a valid move
                else if (grid[r+1][c-1] == 2) {
                    return true
                }

                //check if bottom-left grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r+1][c-1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither black disk or empty grid was still found,
            //then bottom-left direction is invalid
            else if (r >= 7 || c <= 0) {
                return false
            }
        }

        return isitValid
    }

    //recursive function for checking on bottom-right direction
    fun checkValidBottomRight(user: Boolean, r: Int, c: Int): Boolean {
        var isitValid : Boolean = false

        //if it's white disk's turn
        if (user == true) {
            if (r < 7 && c < 7) {
                //check if bottom-right grid has black disk. If yes, keep recursively call the function
                if (grid[r+1][c+1] == 2) {
                    isitValid = checkValidBottomRight(user, r + 1, c + 1)
                }

                //check if bottom-right grid has white disk. If yes, return true to indicate that it's a valid move
                else if (grid[r+1][c+1] == 1) {
                    return true
                }

                //check if bottom-right grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r+1][c+1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither white disk or empty grid was still found,
            //then bottom-right direction is invalid
            else if (r >= 7 || c >= 7) {
                return false
            }
        }

        //if it's black disk's turn
        else {
            if (r < 7 && c < 7) {
                //check if bottom-right grid has white disk. If yes, keep recursively call the function
                if (grid[r+1][c+1] == 1) {
                    isitValid = checkValidBottomRight(user, r + 1, c + 1)
                }

                //check if bottom-right grid has black disk. If yes, return true to indicate that it's a valid move
                else if (grid[r+1][c+1] == 2) {
                    return true
                }

                //check if bottom-right grid is empty. If yes, return false to indicate that it's a invalid move
                else if (grid[r+1][c+1] == 0) {
                    return false
                }
            }

            //if it reaches to the farthest possible grid but neither blacke disk or empty grid was still found,
            //then bottom-right direction is invalid
            else if (r >= 7 || c >= 7) {
                return false
            }
        }

        return isitValid
    }




    //recursive functions used to replace certain disks after valid move is detected

    //recursive function for making changes on top direction
    fun changeTop(user: Boolean, r: Int, c: Int) {
        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (r > 0) {
                //check if top grid has black disk. If yes, change that black disk to white disk
                //and keep recursively call the function to check if more black disk exists on top direction
                //if not, recursion will be stopped, indicating that top direction is done for change
                if (grid[r-1][c] == 2) {
                    grid[r-1][c] = 1
                    changeTop(user, r - 1, c)
                }
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (r > 0) {
                //check if top grid has white disk. If yes, change that white disk to black disk
                //and keep recursively call the function to check if more white disk exists on top direction
                //if not, recursion will be stopped, indicating that top direction is done for change
                if (grid[r-1][c] == 1) {
                    grid[r-1][c] = 2
                    changeTop(user, r - 1, c)
                }
            }
        }
    }

    //recursive function for making changes on top-left direction
    fun changeTopLeft(user: Boolean, r: Int, c: Int) {
        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (r > 0 && c > 0) {
                //check if top-left grid has black disk. If yes, change that black disk to white disk
                //and keep recursively call the function to check if more black disk exists on top-left direction
                //if not, recursion will be stopped, indicating that top-left direction is done for change
                if (grid[r-1][c-1] == 2) {
                    grid[r-1][c-1] = 1
                    changeTopLeft(user, r - 1, c - 1)
                }
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (r > 0 && c > 0) {
                //check if top-left grid has white disk. If yes, change that white disk to black disk
                //and keep recursively call the function to check if more white disk exists on top-left direction
                //if not, recursion will be stopped, indicating that top-left direction is done for change
                if (grid[r-1][c-1] == 1) {
                    grid[r-1][c-1] = 2
                    changeTopLeft(user, r - 1, c - 1)
                }
            }
        }
    }

    //recursive function for making changes on top-right direction
    fun changeTopRight(user: Boolean, r: Int, c: Int) {
        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (r > 0 && c < 7) {
                //check if top-right grid has black disk. If yes, change that black disk to white disk
                //and keep recursively call the function to check if more black disk exists on top-right direction
                //if not, recursion will be stopped, indicating that top-right direction is done for change
                if (grid[r-1][c+1] == 2) {
                    grid[r-1][c+1] = 1
                    changeTopRight(user, r - 1, c + 1)
                }
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (r > 0 && c < 7) {
                //check if top-right grid has white disk. If yes, change that white disk to black disk
                //and keep recursively call the function to check if more white disk exists on top-right direction
                //if not, recursion will be stopped, indicating that top-right direction is done for change
                if (grid[r-1][c+1] == 1) {
                    grid[r-1][c+1] = 2
                    changeTopRight(user, r - 1, c + 1)
                }
            }
        }
    }

    //recursive function for making changes on left direction
    fun changeLeft(user: Boolean, r: Int, c: Int) {
        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (c > 0) {
                //check if left grid has black disk. If yes, change that black disk to white disk
                //and keep recursively call the function to check if more black disk exists on left direction
                //if not, recursion will be stopped, indicating that left direction is done for change
                if (grid[r][c-1] == 2) {
                    grid[r][c-1] = 1
                    changeLeft(user, r, c - 1)
                }
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (c > 0) {
                //check if top-left grid has white disk. If yes, change that white disk to black disk
                //and keep recursively call the function to check if more white disk exists on top-left direction
                //if not, recursion will be stopped, indicating that top-left direction is done for change
                if (grid[r][c-1] == 1) {
                    grid[r][c-1] = 2
                    changeLeft(user, r, c - 1)
                }
            }
        }
    }

    //recursive function for making changes on right direction
    fun changeRight(user: Boolean, r: Int, c: Int) {
        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (c < 7) {
                //check if right grid has black disk. If yes, change that black disk to white disk
                //and keep recursively call the function to check if more black disk exists on right direction
                //if not, recursion will be stopped, indicating that right direction is done for change
                if (grid[r][c+1] == 2) {
                    grid[r][c+1] = 1
                    changeRight(user, r, c + 1)
                }
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (c < 7) {
                //check if right grid has white disk. If yes, change that white disk to black disk
                //and keep recursively call the function to check if more white disk exists on right direction
                //if not, recursion will be stopped, indicating that right direction is done for change
                if (grid[r][c+1] == 1) {
                    grid[r][c+1] = 2
                    changeRight(user, r, c + 1)
                }
            }
        }
    }

    //recursive function for making changes on bottom direction
    fun changeBottom(user: Boolean, r: Int, c: Int) {
        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (r < 7) {
                //check if bottom grid has black disk. If yes, change that black disk to white disk
                //and keep recursively call the function to check if more black disk exists on bottom direction
                //if not, recursion will be stopped, indicating that bottom direction is done for change
                if (grid[r+1][c] == 2) {
                    grid[r+1][c] = 1
                    changeBottom(user, r + 1, c)
                }
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (r < 7) {
                //check if bottom grid has white disk. If yes, change that white disk to black disk
                //and keep recursively call the function to check if more white disk exists on bottom direction
                //if not, recursion will be stopped, indicating that bottom direction is done for change
                if (grid[r+1][c] == 1) {
                    grid[r+1][c] = 2
                    changeBottom(user, r + 1, c)
                }
            }
        }
    }

    //recursive function for making changes on bottom-left direction
    fun changeBottomLeft(user: Boolean, r: Int, c: Int) {
        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (r < 7 && c > 0) {
                //check if bottom-left grid has black disk. If yes, change that black disk to white disk
                //and keep recursively call the function to check if more black disk exists on bottom-left direction
                //if not, recursion will be stopped, indicating that bottom-left direction is done for change
                if (grid[r+1][c-1] == 2) {
                    grid[r+1][c-1] = 1
                    changeBottomLeft(user, r + 1, c - 1)
                }
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (r < 7 && c > 0) {
                //check if bottom-left grid has white disk. If yes, change that white disk to black disk
                //and keep recursively call the function to check if more white disk exists on bottom-left direction
                //if not, recursion will be stopped, indicating that bottom-left direction is done for change
                if (grid[r+1][c-1] == 1) {
                    grid[r+1][c-1] = 2
                    changeBottomLeft(user, r + 1, c - 1)
                }
            }
        }
    }

    //recursive function for making changes on bottom-right direction
    fun changeBottomRight(user: Boolean, r: Int, c: Int) {
        //if it's white disk's turn
        if (user == true) {
            //keep iterating until it runs into either white disk or empty grid
            if (r < 7 && c < 7) {
                //check if bottom-right grid has black disk. If yes, change that black disk to white disk
                //and keep recursively call the function to check if more black disk exists on bottom-right direction
                //if not, recursion will be stopped, indicating that bottom-right direction is done for change
                if (grid[r+1][c+1] == 2) {
                    grid[r+1][c+1] = 1
                    changeBottomRight(user, r + 1, c + 1)
                }
            }
        }

        //if it's black disk's turn
        else {
            //keep iterating until it runs into either black disk or empty grid
            if (r < 7 && c < 7) {
                //check if bottom-right grid has white disk. If yes, change that white disk to black disk
                //and keep recursively call the function to check if more white disk exists on bottom-right direction
                //if not, recursion will be stopped, indicating that bottom-right direction is done for change
                if (grid[r+1][c+1] == 1) {
                    grid[r+1][c+1] = 2
                    changeBottomRight(user, r + 1, c + 1)
                }
            }
        }
    }



    // function used to check if there are valid moves remaining
    fun checkRemainingValidMoves(user: Boolean): Boolean {
        var areThereValidMoves : Boolean = false

        //check if there are any valid moves remained for white disk
        if (user) {
            //go through the entire board and find at least one possible valid move available for white disk
            for (i in grid.indices) {
                if (i > 0 && i < 7) {
                    for (j in grid[i].indices) {
                        if (j > 0 && j < 7) {
                            //top-left
                            if (grid[i-1][j-1] == 2) {
                                if (checkValidTopLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //top
                            if (grid[i-1][j] == 2) {
                                if (checkValidTop(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //top-right
                            if (grid[i-1][j+1] == 2) {
                                if (checkValidTopRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //left
                            if (grid[i][j-1] == 2) {
                                if (checkValidLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //right
                            if (grid[i][j+1] == 2) {
                                if (checkValidRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom-left
                            if (grid[i+1][j-1] == 2) {
                                if (checkValidBottomLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom
                            if (grid[i+1][j] == 2) {
                                if (checkValidBottom(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom-right
                            if (grid[i+1][j+1] == 2) {
                                if (checkValidBottomRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                        }

                        else if (j == 0) {
                            //top
                            if (grid[i-1][j] == 2) {
                                if (checkValidTop(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //top-right
                            if (grid[i-1][j+1] == 2) {
                                if (checkValidTopRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //right
                            if (grid[i][j+1] == 2) {
                                if (checkValidRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom
                            if (grid[i+1][j] == 2) {
                                if (checkValidBottom(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom-right
                            if (grid[i+1][j+1] == 2) {
                                if (checkValidBottomRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }
                        }

                        else if (j == 7) {
                            //top-left
                            if (grid[i-1][j-1] == 2) {
                                if (checkValidTopLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //top
                            if (grid[i-1][j] == 2) {
                                if (checkValidTop(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //left
                            if (grid[i][j-1] == 2) {
                                if (checkValidLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom-left
                            if (grid[i+1][j-1] == 2) {
                                if (checkValidBottomLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom
                            if (grid[i+1][j] == 2) {
                                if (checkValidBottom(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                        }

                    }
                }

                else if (i == 0) {
                    for (j in grid[i].indices) {
                        if (j > 0 && j < 7) {
                            //left
                            if (grid[i][j-1] == 2) {
                                if (checkValidLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //right
                            if (grid[i][j+1] == 2) {
                                if (checkValidRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom-left
                            if (grid[i+1][j-1] == 2) {
                                if (checkValidBottomLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom
                            if (grid[i+1][j] == 2) {
                                if (checkValidBottom(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom-right
                            if (grid[i+1][j+1] == 2) {
                                if (checkValidBottomRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }
                        }

                        else if (j == 0) {
                            //right
                            if (grid[i][j+1] == 2) {
                                if (checkValidRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom
                            if (grid[i+1][j] == 2) {
                                if (checkValidBottom(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom-right
                            if (grid[i+1][j+1] == 2) {
                                if (checkValidBottomRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }
                        }

                        else if (j == 7) {
                            //left
                            if (grid[i][j-1] == 2) {
                                if (checkValidLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom-left
                            if (grid[i+1][j-1] == 2) {
                                if (checkValidBottomLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom
                            if (grid[i+1][j] == 2) {
                                if (checkValidBottom(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }
                        }
                    }
                }

                else if (i == 7) {
                    for (j in grid[i].indices) {
                        if (j > 0 && j < 7) {
                            //top-left
                            if (grid[i-1][j-1] == 2) {
                                if (checkValidTopLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //top
                            if (grid[i-1][j] == 2) {
                                if (checkValidTop(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //top-right
                            if (grid[i-1][j+1] == 2) {
                                if (checkValidTopRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //left
                            if (grid[i][j-1] == 2) {
                                if (checkValidLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //right
                            if (grid[i][j+1] == 2) {
                                if (checkValidRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }
                        }

                        else if (j == 0) {
                            //top
                            if (grid[i-1][j] == 2) {
                                if (checkValidTop(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //top-right
                            if (grid[i-1][j+1] == 2) {
                                if (checkValidTopRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //right
                            if (grid[i][j+1] == 2) {
                                if (checkValidRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }
                        }

                        else if (j == 7) {
                            //top-left
                            if (grid[i-1][j-1] == 2) {
                                if (checkValidTopLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //top
                            if (grid[i-1][j] == 2) {
                                if (checkValidTop(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //left
                            if (grid[i][j-1] == 2) {
                                if (checkValidLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }
                        }
                    }
                }

            }
        }

        //check if there are any valid moves remained for black disk
        else {
            //go through the entire board and find at least one possible valid move available for black disk
            for (i in grid.indices) {
                if (i > 0 && i < 7) {
                    for (j in grid[i].indices) {
                        if (j > 0 && j < 7) {
                            //top-left
                            if (grid[i-1][j-1] == 1) {
                                if (checkValidTopLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //top
                            if (grid[i-1][j] == 1) {
                                if (checkValidTop(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //top-right
                            if (grid[i-1][j+1] == 1) {
                                if (checkValidTopRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //left
                            if (grid[i][j-1] == 1) {
                                if (checkValidLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //right
                            if (grid[i][j+1] == 1) {
                                if (checkValidRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom-left
                            if (grid[i+1][j-1] == 1) {
                                if (checkValidBottomLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom
                            if (grid[i+1][j] == 1) {
                                if (checkValidBottom(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom-right
                            if (grid[i+1][j+1] == 1) {
                                if (checkValidBottomRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                        }

                        else if (j == 0) {
                            //top
                            if (grid[i-1][j] == 1) {
                                if (checkValidTop(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //top-right
                            if (grid[i-1][j+1] == 1) {
                                if (checkValidTopRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //right
                            if (grid[i][j+1] == 1) {
                                if (checkValidRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom
                            if (grid[i+1][j] == 1) {
                                if (checkValidBottom(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom-right
                            if (grid[i+1][j+1] == 1) {
                                if (checkValidBottomRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }
                        }

                        else if (j == 7) {
                            //top-left
                            if (grid[i-1][j-1] == 1) {
                                if (checkValidTopLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //top
                            if (grid[i-1][j] == 1) {
                                if (checkValidTop(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //left
                            if (grid[i][j-1] == 1) {
                                if (checkValidLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom-left
                            if (grid[i+1][j-1] == 1) {
                                if (checkValidBottomLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom
                            if (grid[i+1][j] == 1) {
                                if (checkValidBottom(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                        }

                    }
                }

                else if (i == 0) {
                    for (j in grid[i].indices) {
                        if (j > 0 && j < 7) {
                            //left
                            if (grid[i][j-1] == 1) {
                                if (checkValidLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //right
                            if (grid[i][j+1] == 1) {
                                if (checkValidRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom-left
                            if (grid[i+1][j-1] == 1) {
                                if (checkValidBottomLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom
                            if (grid[i+1][j] == 1) {
                                if (checkValidBottom(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom-right
                            if (grid[i+1][j+1] == 1) {
                                if (checkValidBottomRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }
                        }

                        else if (j == 0) {
                            //right
                            if (grid[i][j+1] == 1) {
                                if (checkValidRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom
                            if (grid[i+1][j] == 1) {
                                if (checkValidBottom(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom-right
                            if (grid[i+1][j+1] == 1) {
                                if (checkValidBottomRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }
                        }

                        else if (j == 7) {
                            //left
                            if (grid[i][j-1] == 1) {
                                if (checkValidLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom-left
                            if (grid[i+1][j-1] == 1) {
                                if (checkValidBottomLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //bottom
                            if (grid[i+1][j] == 1) {
                                if (checkValidBottom(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }
                        }
                    }
                }

                else if (i == 7) {
                    for (j in grid[i].indices) {
                        if (j > 0 && j < 7) {
                            //top-left
                            if (grid[i-1][j-1] == 1) {
                                if (checkValidTopLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //top
                            if (grid[i-1][j] == 1) {
                                if (checkValidTop(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //top-right
                            if (grid[i-1][j+1] == 1) {
                                if (checkValidTopRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //left
                            if (grid[i][j-1] == 1) {
                                if (checkValidLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //right
                            if (grid[i][j+1] == 1) {
                                if (checkValidRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }
                        }

                        else if (j == 0) {
                            //top
                            if (grid[i-1][j] == 1) {
                                if (checkValidTop(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //top-right
                            if (grid[i-1][j+1] == 1) {
                                if (checkValidTopRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //right
                            if (grid[i][j+1] == 1) {
                                if (checkValidRight(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }
                        }

                        else if (j == 7) {
                            //top-left
                            if (grid[i-1][j-1] == 1) {
                                if (checkValidTopLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //top
                            if (grid[i-1][j] == 1) {
                                if (checkValidTop(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }

                            //left
                            if (grid[i][j-1] == 1) {
                                if (checkValidLeft(user, i, j)) {
                                    areThereValidMoves = true
                                    break
                                }
                            }
                        }
                    }
                }

            }
        }

        return areThereValidMoves
    }


    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent) {
        // not used
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return false
    }


    // * No longer used but keeping it commented just in case if it's needed again *
    // Recursive function used to check if player's move is valid.
    // Replaced with separate multiple functions that checks different directions
    /*
    fun checkValidMove(user: Boolean, r: Int, c: Int): Boolean {
        //if it's white disk's turn
        if (user == true) {
            if (r > 0 && r < 7) {
                if (c > 0 && c < 7) {
                    /* MADE SEPARATE FUNCTION
                    //check if top-left grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c-1] == 2) {
                        checkValidMove(user, r - 1, c - 1)
                    }

                    //check if top-left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c-1] == 1) {
                        return true
                    }

                    //check if top-left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c-1] == 0) {
                        return false
                    }
                    */


                    /* MADE SEPARATE FUNCTION
                    //check if top grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c] == 2) {
                        checkValidMove(user, r - 1, c)
                    }

                    //check if top grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c] == 1) {
                        return true
                    }

                    //check if top grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c] == 0) {
                        return false
                    }
                    */

                    /*
                    //check if top-right grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c+1] == 2) {
                        checkValidMove(user, r - 1, c + 1)
                    }

                    //check if top-right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c+1] == 1) {
                        return true
                    }

                    //check if top-right is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c+1] == 0) {
                        return false
                    }
                    */

                    /*
                    //check if left grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c-1] == 2) {
                        checkValidMove(user, r, c - 1)
                    }

                    //check if left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c-1] == 1) {
                        return true
                    }

                    //check if left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c-1] == 0) {
                        return false
                    }
                    */

                    /*
                    //check if right grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c+1] == 2) {
                        checkValidMove(user, r, c + 1)
                    }

                    //check if right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c+1] == 1) {
                        return true
                    }

                    //check if right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c+1] == 0) {
                        return false
                    }
                    */

                    /*
                    //check if bottom-left grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c-1] == 2) {
                        checkValidMove(user, r + 1, c - 1)
                    }

                    //check if bottom-left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c-1] == 1) {
                        return true
                    }

                    //check if bottom-left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c-1] == 0) {
                        return false
                    }
                    */

                    /*
                    //check if bottom grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c] == 2) {
                        checkValidMove(user, r + 1, c)
                    }

                    //check if bottom grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c] == 1) {
                        return true
                    }

                    //check if bottom grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c] == 0) {
                        return false
                    }
                    */

                    /*
                    //check if bottom-right grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c+1] == 2) {
                        checkValidMove(user, r + 1, c + 1)
                    }

                    //check if bottom-right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c+1] == 1) {
                        return true
                    }

                    //check if bottom-right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c+1] == 0) {
                        return false
                    }
                    */
                }

                else if (c == 0) {
                    //check if top grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c] == 2) {
                        checkValidMove(user, r - 1, c)
                    }

                    //check if top grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c] == 1) {
                        return true
                    }

                    //check if top grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c] == 0) {
                        return false
                    }



                    //check if top-right grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c+1] == 2) {
                        checkValidMove(user, r - 1, c + 1)
                    }

                    //check if top-right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c+1] == 1) {
                        return true
                    }

                    //check if top-right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c+1] == 0) {
                        return false
                    }



                    //check if right grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c+1] == 2) {
                        checkValidMove(user, r, c + 1)
                    }

                    //check if right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c+1] == 1) {
                        return true
                    }

                    //check if right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c+1] == 0) {
                        return false
                    }



                    //check if bottom grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c] == 2) {
                        checkValidMove(user, r + 1, c)
                    }

                    //check if bottom grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c] == 1) {
                        return true
                    }

                    //check if bottom grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c] == 0) {
                        return false
                    }



                    //check if bottom-right grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c+1] == 2) {
                        checkValidMove(user, r + 1, c + 1)
                    }

                    //check if bottom-right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c+1] == 1) {
                        return true
                    }

                    //check if bottom-right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c+1] == 0) {
                        return false
                    }
                }

                else if (c == 7) {
                    //check if top-left grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c-1] == 2) {
                        checkValidMove(user, r - 1, c - 1)
                    }

                    //check if top-left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c-1] == 1) {
                        return true
                    }

                    //check if top-left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c-1] == 0) {
                        return false
                    }



                    //check if top grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c] == 2) {
                        checkValidMove(user, r - 1, c)
                    }

                    //check if top grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c] == 1) {
                        return true
                    }

                    //check if top grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c] == 0) {
                        return false
                    }



                    //check if left grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c-1] == 2) {
                        checkValidMove(user, r, c - 1)
                    }

                    //check if left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c-1] == 1) {
                        return true
                    }

                    //check if left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c-1] == 0) {
                        return false
                    }



                    //check if bottom-left grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c-1] == 2) {
                        checkValidMove(user, r + 1, c - 1)
                    }

                    //check if bottom-left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c-1] == 1) {
                        return true
                    }

                    //check if bottom-left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c-1] == 0) {
                        return false
                    }



                    //check if bottom grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c] == 2) {
                        checkValidMove(user, r + 1, c)
                    }

                    //check if bottom grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c] == 1) {
                        return true
                    }

                    //check if bottom grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c] == 0) {
                        return false
                    }
                }
            }

            else if (r == 0) {
                if (c > 0 && c < 7) {
                    //check if left grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c-1] == 2) {
                        checkValidMove(user, r, c - 1)
                    }

                    //check if left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c-1] == 1) {
                        return true
                    }

                    //check if left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c-1] == 0) {
                        return false
                    }



                    //check if right grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c+1] == 2) {
                        checkValidMove(user, r, c + 1)
                    }

                    //check if right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c+1] == 1) {
                        return true
                    }

                    //check if right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c+1] == 0) {
                        return false
                    }



                    //check if bottom-left grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c-1] == 2) {
                        checkValidMove(user, r + 1, c - 1)
                    }

                    //check if bottom-left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c-1] == 1) {
                        return true
                    }

                    //check if bottom-left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c-1] == 0) {
                        return false
                    }



                    //check if bottom grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c] == 2) {
                        checkValidMove(user, r + 1, c)
                    }

                    //check if bottom grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c] == 1) {
                        return true
                    }

                    //check if bottom grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c] == 0) {
                        return false
                    }



                    //check if bottom-right grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c+1] == 2) {
                        checkValidMove(user, r + 1, c + 1)
                    }

                    //check if bottom-right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c+1] == 1) {
                        return true
                    }

                    //check if bottom-right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c+1] == 0) {
                        return false
                    }
                }

                else if (c == 0) {
                    //check if right grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c+1] == 2) {
                        checkValidMove(user, r, c + 1)
                    }

                    //check if right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c+1] == 1) {
                        return true
                    }

                    //check if right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c+1] == 0) {
                        return false
                    }



                    //check if bottom grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c] == 2) {
                        checkValidMove(user, r + 1, c)
                    }

                    //check if bottom grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c] == 1) {
                        return true
                    }

                    //check if bottom grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c] == 0) {
                        return false
                    }



                    //check if bottom-right grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c+1] == 2) {
                        checkValidMove(user, r + 1, c + 1)
                    }

                    //check if bottom-right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c+1] == 1) {
                        return true
                    }

                    //check if bottom-right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c+1] == 0) {
                        return false
                    }
                }

                //HOLD ON
                else if (c == 7) {
                    //check if left grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c-1] == 2) {
                        checkValidMove(user, r, c - 1)
                    }

                    //check if left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c-1] == 1) {
                        return true
                    }

                    //check if left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c-1] == 0) {
                        return false
                    }



                    //check if bottom-left grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c-1] == 2) {
                        checkValidMove(user, r + 1, c - 1)
                    }

                    //check if bottom-left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c-1] == 1) {
                        return true
                    }

                    //check if bottom-left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c-1] == 0) {
                        return false
                    }



                    //check if bottom grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c] == 2) {
                        checkValidMove(user, r + 1, c)
                    }

                    //check if bottom grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c] == 1) {
                        return true
                    }

                    //check if bottom grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c] == 0) {
                        return false
                    }
                }
            }

            else if (r == 7) {
                if (c > 0 && c < 7) {
                    //check if top-left grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c-1] == 2) {
                        checkValidMove(user, r - 1, c - 1)
                    }

                    //check if top-left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c-1] == 1) {
                        return true
                    }

                    //check if top-left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c-1] == 0) {
                        return false
                    }



                    //check if top grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c] == 2) {
                        checkValidMove(user, r - 1, c)
                    }

                    //check if top grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c] == 1) {
                        return true
                    }

                    //check if top grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c] == 0) {
                        return false
                    }



                    //check if top-right grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c+1] == 2) {
                        checkValidMove(user, r - 1, c + 1)
                    }

                    //check if top-right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c+1] == 1) {
                        return true
                    }

                    //check if top-right is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c+1] == 0) {
                        return false
                    }



                    //check if left grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c-1] == 2) {
                        checkValidMove(user, r, c - 1)
                    }

                    //check if left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c-1] == 1) {
                        return true
                    }

                    //check if left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c-1] == 0) {
                        return false
                    }



                    //check if right grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c+1] == 2) {
                        checkValidMove(user, r, c + 1)
                    }

                    //check if right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c+1] == 1) {
                        return true
                    }

                    //check if right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c+1] == 0) {
                        return false
                    }
                }

                else if (c == 0) {
                    //check if top grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c] == 2) {
                        checkValidMove(user, r - 1, c)
                    }

                    //check if top grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c] == 1) {
                        return true
                    }

                    //check if top grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c] == 0) {
                        return false
                    }



                    //check if top-right grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c+1] == 2) {
                        checkValidMove(user, r - 1, c + 1)
                    }

                    //check if top-right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c+1] == 1) {
                        return true
                    }

                    //check if top-right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c+1] == 0) {
                        return false
                    }



                    //check if right grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c+1] == 2) {
                        checkValidMove(user, r, c + 1)
                    }

                    //check if right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c+1] == 1) {
                        return true
                    }

                    //check if right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c+1] == 0) {
                        return false
                    }
                }

                else if (c == 7) {
                    //check if top-left grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c-1] == 2) {
                        checkValidMove(user, r - 1, c - 1)
                    }

                    //check if top-left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c-1] == 1) {
                        return true
                    }

                    //check if top-left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c-1] == 0) {
                        return false
                    }



                    //check if top grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c] == 2) {
                        checkValidMove(user, r - 1, c)
                    }

                    //check if top grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c] == 1) {
                        return true
                    }

                    //check if top grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c] == 0) {
                        return false
                    }



                    //check if left grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c-1] == 2) {
                        checkValidMove(user, r, c - 1)
                    }

                    //check if left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c-1] == 1) {
                        return true
                    }

                    //check if left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c-1] == 0) {
                        return false
                    }
                }
            }

        }

        //if it's black disk's turn
        else {
            if (r > 0 && r < 7) {
                if (c > 0 && c < 7) {
                    //check if top-left grid has white disk. If yes, keep recursively call the function
                    if (grid[r-1][c-1] == 1) {
                        checkValidMove(user, r - 1, c - 1)
                    }

                    //check if top-left grid has black disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c-1] == 2) {
                        return true
                    }

                    //check if top-left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c-1] == 0) {
                        return false
                    }



                    //check if top grid has white disk. If yes, keep recursively call the function
                    if (grid[r-1][c] == 1) {
                        checkValidMove(user, r - 1, c)
                    }

                    //check if top grid has black disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c] == 2) {
                        return true
                    }

                    //check if top grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c] == 0) {
                        return false
                    }



                    //check if top-right grid has white disk. If yes, keep recursively call the function
                    if (grid[r-1][c+1] == 1) {
                        checkValidMove(user, r - 1, c + 1)
                    }

                    //check if top-right grid has black disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c+1] == 2) {
                        return true
                    }

                    //check if top-right is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c+1] == 0) {
                        return false
                    }

//============================================ PAUSE

                    //check if left grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c-1] == 2) {
                        checkValidMove(user, r, c - 1)
                    }

                    //check if left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c-1] == 1) {
                        return true
                    }

                    //check if left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c-1] == 0) {
                        return false
                    }



                    //check if right grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c+1] == 2) {
                        checkValidMove(user, r, c + 1)
                    }

                    //check if right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c+1] == 1) {
                        return true
                    }

                    //check if right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c+1] == 0) {
                        return false
                    }



                    //check if bottom-left grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c-1] == 2) {
                        checkValidMove(user, r + 1, c - 1)
                    }

                    //check if bottom-left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c-1] == 1) {
                        return true
                    }

                    //check if bottom-left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c-1] == 0) {
                        return false
                    }



                    //check if bottom grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c] == 2) {
                        checkValidMove(user, r + 1, c)
                    }

                    //check if bottom grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c] == 1) {
                        return true
                    }

                    //check if bottom grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c] == 0) {
                        return false
                    }



                    //check if bottom-right grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c+1] == 2) {
                        checkValidMove(user, r + 1, c + 1)
                    }

                    //check if bottom-right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c+1] == 1) {
                        return true
                    }

                    //check if bottom-right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c+1] == 0) {
                        return false
                    }
                }

                else if (c == 0) {
                    //check if top grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c] == 2) {
                        checkValidMove(user, r - 1, c)
                    }

                    //check if top grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c] == 1) {
                        return true
                    }

                    //check if top grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c] == 0) {
                        return false
                    }



                    //check if top-right grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c+1] == 2) {
                        checkValidMove(user, r - 1, c + 1)
                    }

                    //check if top-right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c+1] == 1) {
                        return true
                    }

                    //check if top-right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c+1] == 0) {
                        return false
                    }



                    //check if right grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c+1] == 2) {
                        checkValidMove(user, r, c + 1)
                    }

                    //check if right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c+1] == 1) {
                        return true
                    }

                    //check if right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c+1] == 0) {
                        return false
                    }



                    //check if bottom grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c] == 2) {
                        checkValidMove(user, r + 1, c)
                    }

                    //check if bottom grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c] == 1) {
                        return true
                    }

                    //check if bottom grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c] == 0) {
                        return false
                    }



                    //check if bottom-right grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c+1] == 2) {
                        checkValidMove(user, r + 1, c + 1)
                    }

                    //check if bottom-right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c+1] == 1) {
                        return true
                    }

                    //check if bottom-right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c+1] == 0) {
                        return false
                    }
                }

                else if (c == 7) {
                    //check if top-left grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c-1] == 2) {
                        checkValidMove(user, r - 1, c - 1)
                    }

                    //check if top-left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c-1] == 1) {
                        return true
                    }

                    //check if top-left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c-1] == 0) {
                        return false
                    }



                    //check if top grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c] == 2) {
                        checkValidMove(user, r - 1, c)
                    }

                    //check if top grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c] == 1) {
                        return true
                    }

                    //check if top grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c] == 0) {
                        return false
                    }



                    //check if left grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c-1] == 2) {
                        checkValidMove(user, r, c - 1)
                    }

                    //check if left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c-1] == 1) {
                        return true
                    }

                    //check if left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c-1] == 0) {
                        return false
                    }



                    //check if bottom-left grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c-1] == 2) {
                        checkValidMove(user, r + 1, c - 1)
                    }

                    //check if bottom-left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c-1] == 1) {
                        return true
                    }

                    //check if bottom-left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c-1] == 0) {
                        return false
                    }



                    //check if bottom grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c] == 2) {
                        checkValidMove(user, r + 1, c)
                    }

                    //check if bottom grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c] == 1) {
                        return true
                    }

                    //check if bottom grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c] == 0) {
                        return false
                    }
                }
            }

            else if (r == 0) {
                if (c > 0 && c < 7) {
                    //check if left grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c-1] == 2) {
                        checkValidMove(user, r, c - 1)
                    }

                    //check if left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c-1] == 1) {
                        return true
                    }

                    //check if left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c-1] == 0) {
                        return false
                    }



                    //check if right grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c+1] == 2) {
                        checkValidMove(user, r, c + 1)
                    }

                    //check if right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c+1] == 1) {
                        return true
                    }

                    //check if right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c+1] == 0) {
                        return false
                    }



                    //check if bottom-left grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c-1] == 2) {
                        checkValidMove(user, r + 1, c - 1)
                    }

                    //check if bottom-left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c-1] == 1) {
                        return true
                    }

                    //check if bottom-left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c-1] == 0) {
                        return false
                    }



                    //check if bottom grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c] == 2) {
                        checkValidMove(user, r + 1, c)
                    }

                    //check if bottom grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c] == 1) {
                        return true
                    }

                    //check if bottom grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c] == 0) {
                        return false
                    }



                    //check if bottom-right grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c+1] == 2) {
                        checkValidMove(user, r + 1, c + 1)
                    }

                    //check if bottom-right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c+1] == 1) {
                        return true
                    }

                    //check if bottom-right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c+1] == 0) {
                        return false
                    }
                }

                else if (c == 0) {
                    //check if right grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c+1] == 2) {
                        checkValidMove(user, r, c + 1)
                    }

                    //check if right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c+1] == 1) {
                        return true
                    }

                    //check if right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c+1] == 0) {
                        return false
                    }



                    //check if bottom grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c] == 2) {
                        checkValidMove(user, r + 1, c)
                    }

                    //check if bottom grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c] == 1) {
                        return true
                    }

                    //check if bottom grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c] == 0) {
                        return false
                    }



                    //check if bottom-right grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c+1] == 2) {
                        checkValidMove(user, r + 1, c + 1)
                    }

                    //check if bottom-right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c+1] == 1) {
                        return true
                    }

                    //check if bottom-right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c+1] == 0) {
                        return false
                    }
                }

                //HOLD ON
                else if (c == 7) {
                    //check if left grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c-1] == 2) {
                        checkValidMove(user, r, c - 1)
                    }

                    //check if left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c-1] == 1) {
                        return true
                    }

                    //check if left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c-1] == 0) {
                        return false
                    }



                    //check if bottom-left grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c-1] == 2) {
                        checkValidMove(user, r + 1, c - 1)
                    }

                    //check if bottom-left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c-1] == 1) {
                        return true
                    }

                    //check if bottom-left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c-1] == 0) {
                        return false
                    }



                    //check if bottom grid has black disk. If yes, keep recursively call the function
                    if (grid[r+1][c] == 2) {
                        checkValidMove(user, r + 1, c)
                    }

                    //check if bottom grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r+1][c] == 1) {
                        return true
                    }

                    //check if bottom grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r+1][c] == 0) {
                        return false
                    }
                }
            }


            else if (r == 7) {
                if (c > 0 && c < 7) {
                    //check if top-left grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c-1] == 2) {
                        checkValidMove(user, r - 1, c - 1)
                    }

                    //check if top-left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c-1] == 1) {
                        return true
                    }

                    //check if top-left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c-1] == 0) {
                        return false
                    }



                    //check if top grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c] == 2) {
                        checkValidMove(user, r - 1, c)
                    }

                    //check if top grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c] == 1) {
                        return true
                    }

                    //check if top grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c] == 0) {
                        return false
                    }



                    //check if top-right grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c+1] == 2) {
                        checkValidMove(user, r - 1, c + 1)
                    }

                    //check if top-right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c+1] == 1) {
                        return true
                    }

                    //check if top-right is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c+1] == 0) {
                        return false
                    }



                    //check if left grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c-1] == 2) {
                        checkValidMove(user, r, c - 1)
                    }

                    //check if left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c-1] == 1) {
                        return true
                    }

                    //check if left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c-1] == 0) {
                        return false
                    }



                    //check if right grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c+1] == 2) {
                        checkValidMove(user, r, c + 1)
                    }

                    //check if right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c+1] == 1) {
                        return true
                    }

                    //check if right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c+1] == 0) {
                        return false
                    }
                }

                else if (c == 0) {
                    //check if top grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c] == 2) {
                        checkValidMove(user, r - 1, c)
                    }

                    //check if top grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c] == 1) {
                        return true
                    }

                    //check if top grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c] == 0) {
                        return false
                    }



                    //check if top-right grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c+1] == 2) {
                        checkValidMove(user, r - 1, c + 1)
                    }

                    //check if top-right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c+1] == 1) {
                        return true
                    }

                    //check if top-right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c+1] == 0) {
                        return false
                    }



                    //check if right grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c+1] == 2) {
                        checkValidMove(user, r, c + 1)
                    }

                    //check if right grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c+1] == 1) {
                        return true
                    }

                    //check if right grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c+1] == 0) {
                        return false
                    }
                }

                else if (c == 7) {
                    //check if top-left grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c-1] == 2) {
                        checkValidMove(user, r - 1, c - 1)
                    }

                    //check if top-left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c-1] == 1) {
                        return true
                    }

                    //check if top-left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c-1] == 0) {
                        return false
                    }



                    //check if top grid has black disk. If yes, keep recursively call the function
                    if (grid[r-1][c] == 2) {
                        checkValidMove(user, r - 1, c)
                    }

                    //check if top grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r-1][c] == 1) {
                        return true
                    }

                    //check if top grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r-1][c] == 0) {
                        return false
                    }



                    //check if left grid has black disk. If yes, keep recursively call the function
                    if (grid[r][c-1] == 2) {
                        checkValidMove(user, r, c - 1)
                    }

                    //check if left grid has white disk. If yes, return true to indicate that it's a valid move
                    else if (grid[r][c-1] == 1) {
                        return true
                    }

                    //check if left grid is empty. If yes, return false to indicate that it's a invalid move
                    else if (grid[r][c-1] == 0) {
                        return false
                    }
                }
            }
        }
    }
    */
}


