package app.raihan.suitmedia_application_test.ui.first

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import app.raihan.suitmedia_application_test.databinding.ActivityFirstScreenBinding
import app.raihan.suitmedia_application_test.ui.second.SecondScreenActivity

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        val palindrome = binding.palindromeEdt.text
        val name = binding.nameEdt.text

        binding.checkBtn.setOnClickListener{
            var isPalindrome = true
            if (palindrome.isEmpty()) {
                binding.palindromeEdt.error ="Must be filled!"
                isPalindrome = false
            }

            val textLength = palindrome.length
            var i = 0
            while (i<textLength/2) {
                i++
                if (palindrome[i] !=palindrome[textLength-1-i]) {
                    isPalindrome = false
                    break
                }
            }

            if (isPalindrome){
                Toast.makeText(this, "Palindrome", Toast.LENGTH_SHORT).show()
            }else if (!isPalindrome && palindrome.isNotEmpty()) {
                Toast.makeText(this, "Not Palindrome", Toast.LENGTH_SHORT).show()
            }
        }

        binding.nextBtn.setOnClickListener{
            val intent = Intent(this, SecondScreenActivity::class.java)
//            intent.putExtra(SecondScreenActivity.EXTRA_NAME,name.toString())
            if(name.isEmpty()){
                binding.nameEdt.error = "Must be filled!"
            }else startActivity(intent)
        }
    }

    companion object {
        private var TAG = "FirstScreenActivity"
    }
}