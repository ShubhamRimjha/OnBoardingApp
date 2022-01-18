package com.test.onboardingapp

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    private lateinit var onBoardingItemsAdapter: onBoardingItemsAdapter
    private lateinit var indicatorContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnBoardingItems()
        setupIndicator()
        setCurrentIndicator(0)
    }

    private fun setOnBoardingItems() {
        onBoardingItemsAdapter = onBoardingItemsAdapter(
            listOf(
                onBoardingItem(
                    onBoardingImg = R.drawable.img_onboarding_01,
                    description = "Get services from the best people in \nthe industry, at a low cost."
                ),
                onBoardingItem(
                    onBoardingImg = R.drawable.img_onboarding_02,
                    description = "Take control of the notifications and \nschedule the work according to \nyour time."
                ),
                onBoardingItem(
                    onBoardingImg = R.drawable.img_onboarding_03,
                    description = "Our professionals ensure low contact \nand follow WHO hygiene guidelines."
                )
            )
        )
        val onBoardingViewPager = findViewById<ViewPager2>(R.id.onBoardingViewPager)
        onBoardingViewPager.adapter = onBoardingItemsAdapter
        onBoardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onBoardingViewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.imgNext).setOnClickListener {
            if (onBoardingViewPager.currentItem + 1 < onBoardingItemsAdapter.itemCount) {
                onBoardingViewPager.currentItem += 1
            } else
                navigateToHome()
        }

        findViewById<TextView>(R.id.textSkip).setOnClickListener {
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        startActivity((Intent(applicationContext, HomeActivity::class.java)))
        finish()
    }

    private fun setupIndicator() {
        indicatorContainer = findViewById(R.id.indicatorContainer)
        val indicators = arrayOfNulls<ImageView>(onBoardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_bg
                    )
                )
                it.layoutParams = layoutParams
                indicatorContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_bg
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_bg
                    )
                )
            }
        }
    }
}