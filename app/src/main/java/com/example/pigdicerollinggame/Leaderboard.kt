package com.example.pigdicerollinggame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val LEADERBOARD_FILE = "leaderboard.txt"

class Leaderboard : AppCompatActivity() {
    var leaderList = ArrayList<LeaderboardItem>()
    private lateinit var leadListAdapter : LeaderboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        val recyclerView: RecyclerView = findViewById(R.id.leaderboardRecycler)
        leadListAdapter = LeaderboardAdapter(leaderList.asReversed())

        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = leadListAdapter

        val extras = intent.extras

        if(extras != null){
            val winner: String? = extras.getString(WINNER)
            val winningScore = extras.getString(WINNINGSCORE)
            val formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
            val formattedDate = LocalDateTime.now().format(formatter)

            //writing and appending date to file
            val fileOutputStream: FileOutputStream = openFileOutput(LEADERBOARD_FILE, MODE_APPEND)
            val leaderboardFile = OutputStreamWriter(fileOutputStream)
            leaderboardFile.write("$winner,$winningScore,$formattedDate\n")
            leaderboardFile.close()
        }

        readLeaderboardData()

    }

    fun readLeaderboardData (){
        val file = File(filesDir, LEADERBOARD_FILE)
        if (file.exists()){
            File(filesDir, LEADERBOARD_FILE).forEachLine {
                val parts = it.split(",")
                var leadItem = LeaderboardItem(parts[0], parts[1], parts[2])
                leaderList.add(leadItem)
            }
            leadListAdapter.notifyDataSetChanged()
        }
    }

    fun onPlayGameClick( v: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}