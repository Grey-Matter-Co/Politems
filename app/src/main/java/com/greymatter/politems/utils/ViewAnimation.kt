package com.greymatter.politems.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.Transformation


object ViewAnimation {
    fun expand(view: View, animListener: AnimListener) {
        val expandAction = expandAction(view)
        expandAction.setAnimationListener(object : AnimationListener {
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                animListener.onFinish()
            }
        })
        view.startAnimation(expandAction)
    }

    fun expand(view: View) {
        view.startAnimation(expandAction(view))
    }

    private fun expandAction(view: View): Animation {
        view.measure(-1, -2)
        val measuredHeight = view.measuredHeight
        view.layoutParams.height = 0
        view.visibility = View.VISIBLE
        val r1 = object : Animation() {
            override fun willChangeBounds(): Boolean {
                return true
            }

            /* access modifiers changed from: protected */
            public override fun applyTransformation(
                f: Float,
                transformation: Transformation
            ) {
                view.layoutParams.height =
                    if (f == 1.0f) -2 else (measuredHeight.toFloat() * f).toInt()
                view.requestLayout()
            }
        }
        r1.duration = (measuredHeight.toFloat() / view.context.resources
            .displayMetrics.density).toInt().toLong()
        view.startAnimation(r1)
        return r1
    }

    fun collapse(view: View) {
        val measuredHeight = view.measuredHeight
        val r1 = object : Animation() {
            override fun willChangeBounds(): Boolean {
                return true
            }

            /* access modifiers changed from: protected */
            public override fun applyTransformation(
                f: Float,
                transformation: Transformation
            ) {
                if (f == 1.0f) {
                    view.visibility = View.GONE
                    return
                }
                val layoutParams = view.layoutParams
                layoutParams.height = measuredHeight - (measuredHeight.toFloat() * f).toInt()
                view.requestLayout()
            }
        }
        r1.duration = (measuredHeight.toFloat() / view.context.resources
            .displayMetrics.density).toInt().toLong()
        view.startAnimation(r1)
    }

    fun flyInDown(view: View, animListener: AnimListener?)
    {
        view.visibility = View.VISIBLE
        view.alpha = 0.0f
        view.translationY = 0.0f
        view.translationY = (-view.height).toFloat()
        view.animate().setDuration(200).translationY(0.0f)
            .setListener(object : AnimatorListenerAdapter()
            {
                override fun onAnimationEnd(animator: Animator)
                {
                    animListener?.onFinish()
                    super.onAnimationEnd(animator)
                }
            }).alpha(1.0f).start()
    }

    fun flyOutDown(view: View, animListener: AnimListener?)
    {
        view.visibility = View.VISIBLE
        view.alpha = 1.0f
        view.translationY = 0.0f
        view.animate().setDuration(200).translationY(view.height.toFloat())
            .setListener(object : AnimatorListenerAdapter()
            {
                override fun onAnimationEnd(animator: Animator)
                {
                    animListener?.onFinish()
                    super.onAnimationEnd(animator)
                }
            }).alpha(0.0f).start()
    }

    @JvmOverloads
    fun fadeIn( view: View, animListener: AnimListener? = null )
    {
        view.visibility = View.GONE
        view.alpha = 0.0f
        view.animate().setDuration(200).setListener(object : AnimatorListenerAdapter()
        {
            override fun onAnimationEnd(animator: Animator)
            {
                view.visibility = View.VISIBLE
                animListener?.onFinish()
                super.onAnimationEnd(animator)
            }
        }).alpha(1.0f)
    }

    @JvmOverloads
    fun fadeOut( view: View, animListener: AnimListener? = null )
    {
        view.alpha = 1.0f
        view.animate().setDuration(500).setListener(object : AnimatorListenerAdapter()
        {
            override fun onAnimationEnd(animator: Animator)
            {
                animListener?.onFinish()
                super.onAnimationEnd(animator)
            }
        }).alpha(0.0f)
    }

    fun showIn(view: View) {
        view.visibility = View.VISIBLE
        view.alpha = 0.0f
        view.translationY = view.height.toFloat()
        view.animate().setDuration(200).translationY(0.0f)
            .setListener(object : AnimatorListenerAdapter() {
            }).alpha(1.0f).start()
    }

    fun initShowOut(view: View) {
        view.visibility = View.GONE
        view.translationY = view.height.toFloat()
        view.alpha = 0.0f
    }

    fun showOut(view: View) {
        view.visibility = View.VISIBLE
        view.alpha = 1.0f
        view.translationY = 0.0f
        view.animate().setDuration(200).translationY(view.height.toFloat())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animator: Animator) {
                    view.visibility = View.GONE
                    super.onAnimationEnd(animator)
                }
            }).alpha(0.0f).start()
    }

    fun rotateFab(view: View, z: Boolean): Boolean
    {
        view.animate().setDuration(200).setListener(object : AnimatorListenerAdapter() {})
            .rotation(if (z) 135.0f else 0.0f)
        return z
    }

    fun fadeOutIn(view: View)
    {
        view.alpha = 0.0f
        val animatorSet = AnimatorSet()
        val ofFloat = ObjectAnimator.ofFloat(view, "alpha", *floatArrayOf(0.0f, 0.5f, 1.0f))
        ObjectAnimator.ofFloat(view, "alpha", *floatArrayOf(0.0f)).start()
        ofFloat.duration = 500
        animatorSet.play(ofFloat)
        animatorSet.start()
    }

    @JvmOverloads
    fun showScale( view: View, animListener: AnimListener? = null) =
        view.animate().scaleY(1.0f).scaleX(1.0f).setDuration(200)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animator: Animator) {
                    val animListener = animListener
                    animListener?.onFinish()
                    super.onAnimationEnd(animator)
                }
            }).start()

    fun hideScale(view: View) =
        fadeOut(view, null as AnimListener?)

    fun hideScale(view: View, animListener: AnimListener?) =
        view.animate().scaleY(0.0f).scaleX(0.0f).setDuration(200)
            .setListener(object : AnimatorListenerAdapter()
            {
                override fun onAnimationEnd(animator: Animator)
                {
                    animListener?.onFinish()
                    super.onAnimationEnd(animator)
                }
            }).start()

    fun hideFab(view: View) =
        view.animate().translationY((view.height * 2).toFloat()).setDuration(300).start()

    fun showFab(view: View) =
        view.animate().translationY(0.0f).setDuration(300).start()

    interface AnimListener {
        fun onFinish()
    }
}
