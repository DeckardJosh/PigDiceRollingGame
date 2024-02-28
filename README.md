<h1>Pig Dice Rolling Game</h1>
<p>This is an application for a game called Pig Dice, where you play against a computer taking turns rolling dice and scoring points. This project includes the use of multiple activities, and basic andriod app functionality.</p>
<h2>Game Description:</h2>
<p>The player starts by rolling a set of dice. The player can roll as many times as he wants during his turn, but here is the catch. If either of the dice lands on a 1, you lose your points on that specific turn and the turn is passed to the computer. If both of the dice land on 1's, you lose all of your banked points for that game and the turn is passed to the computer. If the player rolls, and decides he wants to bank his points, by pressing the hold button you bank those points and the turn is passed to the computer. As for the computer's turn, the computer has the same set of rules, however the computer only rolls at max 3 times. The winner is declared when either the user or the computer reaches 100 or more points. TLDR: Roll the dice, but avoid rolling 1's.</p>
<h2>How its Made:</h2>
<strong>Tech I used: </strong><p>Kotlin, Andriod Studio</p>
<p>The idea behind this project was to have some fun, while still learning how to use Kotlin and Andriod Studio. The main activity host's the games main functionality, where the images are set based on the dice you roll, and the scores are manintained with the counts. Furthermore, there is a leaderboard page where the application utilizes andriod devices file manager. The leaderboard is manipulated by using a CSV file. and the CSV file is read into a Recycler View where the data is displayed with the latest game on top.</p>
<h2>Lessons Learned:</h2>
<p>There was a lot to learn here, as it was one of the first projects I ever created in Andriod Studio. The important lessons being how to use and manipulate data from TextViews, EditText, Buttons, and RecyclerViews. As far as the logic written in Kotlin, there was not much new, as I have been working with Kotlin for a while now.</p>
<h2>Demo:</h2>
<p>Here is a demo to showcase the functionality and feel of this project, so that you do not have to clone and view the project locally.</p>
