package com.example.kamal.bmicalculator

import android.animation.ValueAnimator
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.NumberPicker
import android.widget.TextView
import com.example.kamal.bmicalculator.databinding.FragmentMetricBinding
import com.example.kamal.bmicalculator.model.BodyJava
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_metric.*
import kotlinx.android.synthetic.main.fragment_metric.view.*
import android.graphics.Typeface
import android.util.TypedValue


class MetricFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance(): MetricFragment {
            return MetricFragment()
        }
    }

    var binding: FragmentMetricBinding? = null
    var body: BodyJava? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_metric, container, false)
        body = BodyJava("", "")
        binding!!.body = body
        val rootView = binding!!.root

        rootView.bt_calculate.setOnClickListener(this)
        rootView.et_weight.setOnEditorActionListener({ _, actionId, keyEvent ->
            if (keyEvent?.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                displayResult()
            }

            false
        })

        return rootView
    }

    fun anim(view: View){
        val animation=AnimationUtils.loadAnimation(context!!,R.anim.slide_down)
    }

    override fun onClick(clickedView: View?) {
        if (clickedView == bt_calculate) {
            displayResult()
        }
    }

    private fun displayResult() {
        val rootView = view!!

        try {
            val bmiRes = Utils.getBmiText2(context!!, body!!.bmi)
            val bmiText = Utils.getBmiText(context!!, body!!.bmi)

            res.setText("Your BMI")
            res.setPadding(0, 0, 200, 220)
            res.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.mainText2))
            rootView.tv_result.text = bmiText
            rootView.result.text = bmiRes

        }
        catch (e: NumberFormatException){
                rootView.tv_result.setText("")

                val mainActivity = activity
                if (mainActivity is MainActivity) {
                    mainActivity.setToastText(getString(R.string.toast_invalid_number))
                    res.setText("Invalid Number\nPlease insert valid entries")
                    res.setTextSize(30F)
                }
        }
    }
}