package com.example.vocabulary_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var definitions = ArrayList<String>()
    private var words = ArrayList<String>()
    private var wordToDefinition =HashMap<String,String>()
    private lateinit var myAdapter :ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        readWordsFiles()
        setupList2()

    }
    private fun readWordsFiles(){
        val reader = Scanner(resources.openRawResource(R.raw.words))
        while(reader.hasNextLine()){
            val line = reader.nextLine()
            Log.d("WordFile","The line is $line")
            val parts = line.split("-")
            words.add(parts[0])
            wordToDefinition.put(parts[0],parts[1])
        }
    }


    private fun setupList2() {
        val rand = java.util.Random()
        val index = rand.nextInt(words.size)
        val word = words[index]
        the_word.text=word
        definitions.clear()
        definitions.add(wordToDefinition[word]!!)//pick the correct definition of the word
        words.shuffle()
        for(otherWord in words.subList(0,4)) {
            definitions.add(wordToDefinition[otherWord]!!)//pick 4 wrong deinitions
         }
        myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, definitions    )
        definition_list.adapter = myAdapter
     }

}