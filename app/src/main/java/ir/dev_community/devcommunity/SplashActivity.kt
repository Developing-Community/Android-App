package ir.dev_community.devcommunity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), Animation.AnimationListener {

    private val RaptorTAG: String? = "RAPTOR"

    lateinit var anim1: Animation
    lateinit var devAnim: Animation
    lateinit var communityAnim: Animation
    lateinit var progressBarAnim: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initilizeAnims()

        AnimateImageView()

        //todo: for test
        logo_imageview.setOnClickListener {
            dev_textView.visibility = View.INVISIBLE
            community_textView.visibility = View.INVISIBLE
            splash_progressbar.visibility = View.INVISIBLE
            AnimateImageView()
        }

    }

    override fun onAnimationRepeat(animation: Animation?) {

    }

    override fun onAnimationEnd(animation: Animation?) {
        when (animation) {
            anim1 -> AnimateDevCommunity()
            progressBarAnim -> Log.d(RaptorTAG, " ")
            else -> AnimateProgressBar()
        }
    }

    override fun onAnimationStart(animation: Animation?) {

    }

    private fun AnimateImageView() {
        logo_imageview.startAnimation(anim1)
    }

    private fun AnimateDevCommunity() {
        dev_textView.visibility = View.VISIBLE
        community_textView.visibility = View.VISIBLE

        dev_textView.startAnimation(devAnim)
        community_textView.startAnimation(communityAnim)
    }

    private fun AnimateProgressBar() {
        splash_progressbar.visibility=View.VISIBLE
        splash_progressbar.startAnimation(progressBarAnim)
    }

    private fun initilizeAnims(){
        progressBarAnim = AnimationUtils.loadAnimation(this, R.anim.progress_bar_slide_in)
        anim1 = AnimationUtils.loadAnimation(this, R.anim.splash_logo_slide_in)
        anim1.setAnimationListener(this)
        progressBarAnim.setAnimationListener(this)

        devAnim = AnimationUtils.loadAnimation(this, R.anim.dev_text_slide_in)
        communityAnim = AnimationUtils.loadAnimation(this, R.anim.community_slide_in)

        devAnim.setAnimationListener(this)
        communityAnim.setAnimationListener(this)
    }
}
