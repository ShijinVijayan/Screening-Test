package app.bank.screeningtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.bank.screeningtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var firstNumber: String = ""
    private var secondNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMultiply.setOnClickListener {
            firstNumber = binding.etFirstNumber.text.toString()
            secondNumber = binding.etSecondNumber.text.toString()
            val result = multiply(firstNumber, secondNumber)
            binding.tvResult.text = result

        }

        binding.btnClearAll.setOnClickListener {
            binding.etFirstNumber.text.clear()
            binding.etSecondNumber.text.clear()
            "Result".also { binding.tvResult.text = it }
        }
    }

    private fun multiply(firstnum: String, secondnum: String): String {

        if (firstnum == "0" || secondnum == "0") return "0"

        val result = IntArray(firstnum.length + secondnum.length)
        val num1Reversed = firstnum.reversed()
        val num2Reversed = secondnum.reversed()

        for (i in num1Reversed.indices) {
            for (j in num2Reversed.indices) {
                result[i + j] += (num1Reversed[i] - '0') * (num2Reversed[j] - '0')
                result[i + j + 1] += result[i + j] / 10
                result[i + j] %= 10
            }
        }

        var i = result.size - 1
        while (i > 0 && result[i] == 0) i--

        val resultString = StringBuilder()
        while (i >= 0) {
            resultString.append(result[i])
            i--
        }

        return resultString.toString()
    }


}
