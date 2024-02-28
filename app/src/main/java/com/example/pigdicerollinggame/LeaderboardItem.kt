package com.example.pigdicerollinggame

//Single Item
class LeaderboardItem(var winner: String, var winningScore: String, var formattedDate: String) {
    override fun toString(): String {
        //return "$winner : $winningScore : $formattedDate"
        var retString = ""
        if(winner == "YOU"){
            retString += winner + " ".repeat(9) + ":" + " ".repeat(2) + winningScore + " ".repeat(1) + formattedDate
        }
        else if (winner == "COMPUTER"){
            retString += winner + " ".repeat(4) + ":" + " ".repeat(2) + winningScore + " ".repeat(1) + formattedDate
        }
        else if (winner == "TÚ"){
            retString += winner + " ".repeat(10) + ":" + " ".repeat(2) + winningScore + " ".repeat(1) + formattedDate
        }
        else {
            retString += winner + " ".repeat(1) + ":" + " ".repeat(2) + winningScore + " ".repeat(1) + formattedDate
        }
        return retString
    }
}