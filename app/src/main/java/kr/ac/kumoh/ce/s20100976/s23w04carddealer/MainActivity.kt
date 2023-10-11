package kr.ac.kumoh.ce.s20100976.s23w04carddealer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.ce.s20100976.s23w04carddealer.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var main: ActivityMainBinding
    private lateinit var model: CardDealerViewModel

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(main.root)
       model = ViewModelProvider(this)[CardDealerViewModel::class.java]

        //binding.card1.setImageResource(R.drawable.c_2_of_hearts)
//        val c = Random.nextInt(52)
//        Log.i("Test", "$c : ${getCardName(c)}")
       model.cards.observe(this, Observer{
           val res = IntArray(5)
           for (i in it.indices) {
               res[i] = resources.getIdentifier(
                   getCardName(it[i]),
                   "drawable",
                   packageName
               )
           }
           main.card1.setImageResource(res[0])
           main.card2.setImageResource(res[1])
           main.card3.setImageResource(res[2])
           main.card4.setImageResource(res[3])
           main.card5.setImageResource(res[4])
       })

       main.btnShuffle.setOnClickListener{
           model.shuffle()
       }

//       // TODO: 하드 코딩 없앨 것
//       val card = 25
//       val res = resources.getIdentifier(
//            getCardName(card),
//            "drawable",
//            packageName
//        )
//
//        main.card1.setImageResource(res)
//        main.card2.setImageResource(res)
//        main.card3.setImageResource(res)
//        main.card4.setImageResource(res)
//        main.card5.setImageResource(res)
    }

    private fun getCardName(c: Int) : String {
        // val에서 var로 변경
        var shape = when (c / 13) {
            0 -> "spades"
            1 -> "diamonds"
            2 -> "hearts"
            3 -> "clubs"
            else -> "error"
        }

        val number = when (c % 13) {
            0 -> "ace"
            in 1..9 -> (c % 13 + 1).toString()
            10 -> {
                shape = shape.plus("2")
                "jack"
            }
            11 -> {
                shape = shape.plus("2")
                "queen"
            }
            12 -> {
                shape = shape.plus("2")
                "king"
            }
            else -> "error"
        }

        // 이 방법이 더 나을 듯
//        if (c % 13 in 10..12)
//            return "c_${number}_of_${shape}2"
//        else
//            return "c_${number}_of_${shape}"

        return "c_${number}_of_${shape}"
    }
}