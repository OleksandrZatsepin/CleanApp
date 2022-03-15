package dev.surehand.cleanapp.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.surehand.cleanapp.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            sendButton.setOnClickListener {
                val text = dataEditText.text.toString()
                viewModel.save(text)
            }
            receiveButton.setOnClickListener {
                viewModel.load()
            }
        }
        
        viewModel.resultLive.observe(
            this,
        ) { text ->
            binding.dataTextView.text = text
        }
    }
}