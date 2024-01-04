package app.raihan.suitmedia_application_test.ui.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import app.raihan.suitmedia_application_test.R
import app.raihan.suitmedia_application_test.databinding.ActivitySecondScreenBinding
import app.raihan.suitmedia_application_test.ui.third.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = screen2

        val username = intent.getStringArrayExtra(EXTRA_NAME)
        binding.usernameTxt.text = username.toString()

        setupAction()
    }

    private fun setupAction() {
        var selectedUserName = binding.selectedTxt.isVisible
        binding.chooseBtn.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            if (!selectedUserName) {
                startActivity(intent)
            }else {
                Toast.makeText(this, "Must click name!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.usernameTxt.setOnClickListener {
            binding.selectedTxt.visibility = View.VISIBLE
            binding.selectedTxt.text = SELECTED
            binding.usernameTxt.setTextColor(ContextCompat.getColor(this, R.color.dark_tosca))
            selectedUserName = false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object{
        const val EXTRA_NAME = "extra_name"
        const val SELECTED = "Selected User Name"
        private var screen2 ="Second Screen"
    }
}