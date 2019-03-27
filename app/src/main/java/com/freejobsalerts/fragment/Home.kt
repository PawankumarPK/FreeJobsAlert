package com.freejobsalerts.fragment


import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import android.widget.Toast
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import com.freejobsalerts.BuildConfig
import com.freejobsalerts.R


class Home : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.mRelativeLayoutOne.visibility = View.VISIBLE

        mWelcome.setOnClickListener {
            fragmentManager!!.beginTransaction().replace(R.id.mFrameContainer, Welcome()).addToBackStack(null).commit()
        }
        mHomePage.setOnClickListener {
            fragmentManager!!.beginTransaction().replace(R.id.mFrameContainer, HomePage()).addToBackStack(null).commit()
        }
        mPrivacyPolicy.setOnClickListener {
            fragmentManager!!.beginTransaction().replace(R.id.mFrameContainer, PrivacyPolicy()).addToBackStack(null)
                .commit()
        }
        mYoutube.setOnClickListener {
            fragmentManager!!.beginTransaction().replace(R.id.mFrameContainer, Youtube()).addToBackStack(null)
                .commit()
        }

        mShareNow.setOnClickListener { shareNow() }
        rateApp.onRatingBarChangeListener = OnRatingBarChangeListener { ratingBar, rating, fromUser ->
            if (fromUser) {
               // Toast.makeText(ratingBar.context, "Your Selected Ratings  : $rating", Toast.LENGTH_LONG).show()
                ratingListener()
            }
        }
    }

    private fun shareNow() {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "FreeJob Alert Co.")
            var shareMessage = "\nFreeJob Alert Co.\n"
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" +
                    BuildConfig.APPLICATION_ID + "\n"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "choose one"))
        } catch (e: Exception) {
            //e.toString();
        }

    }

    private fun ratingListener() {
        var uri: Uri
        try {
            uri = Uri.parse("market://details?id=" + context!!.packageName)
            val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
            startActivity(myAppLinkToMarket)
        } catch (e: ActivityNotFoundException) {
            uri = Uri.parse("https://play.google.com/store/apps/details?id=" + context!!.packageName)
            val goMarket = Intent(Intent.ACTION_VIEW, uri)
            startActivity(goMarket)
        }

    }

//    val uri = Uri.parse("market://details?id=" + context!!.packageName)

}

