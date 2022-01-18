package com.test.onboardingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author: Shubham Rimjha
 * @Date: 25-12-2021
 */
class onBoardingItemsAdapter(private val onBoardingItem: List<onBoardingItem>) :
    RecyclerView.Adapter<onBoardingItemsAdapter.onBoardingItemViewHolder>() {

    inner class onBoardingItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageonBoarding = view.findViewById<ImageView>(R.id.image_onBoarding)
        val textDescription = view.findViewById<TextView>(R.id.textDesc_onBoarding)

        fun bind(onBoardingItem: onBoardingItem) {
            imageonBoarding.setImageResource(onBoardingItem.onBoardingImg)
            textDescription.text = onBoardingItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): onBoardingItemViewHolder {
        return onBoardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.onboarding_item_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: onBoardingItemViewHolder, position: Int) {
        holder.bind(onBoardingItem[position])
    }

    override fun getItemCount(): Int {
        return onBoardingItem.size
    }
}