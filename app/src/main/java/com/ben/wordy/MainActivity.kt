package com.ben.wordy

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.ben.base.AutoClearedValue
import com.ben.speech_trainer.ChatFragment
import com.ben.wordy.adapter.SlidePageAdapter
import com.ben.wordy.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private var binding by AutoClearedValue<ActivityMainBinding>()
    private var adapter by AutoClearedValue<SlidePageAdapter>()
    private var mediator by AutoClearedValue<TabLayoutMediator>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        adapter = SlidePageAdapter(this)
        adapter.addTab(ChatFragment(), "Quiz")
        adapter.addTab(ChatFragment(), "Chat")
        binding.viewPager.adapter = adapter
        mediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) {
            tab, position ->
            tab.text = (binding.viewPager.adapter as? SlidePageAdapter)?.getTitle(position)
        }
    }

    override fun onResume() {
        super.onResume()
        mediator.attach()
    }

    override fun onPause() {
        super.onPause()
        mediator.detach()
    }
}