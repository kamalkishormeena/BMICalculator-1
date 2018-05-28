package com.example.kamal.bmicalculator

/**
 * Created by kamal on 5/28/2018.
 */

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.fragment_imperial.*
import kotlinx.android.synthetic.main.fragment_imperial.view.*

class ImperialFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance(): ImperialFragment {
            return ImperialFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_imperial, container, false)
        rootView.bt_calculate.setOnClickListener(this)
        rootView.et_weight.setOnEditorActionListener({ _, actionId, keyEvent ->
            if (keyEvent?.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                displayResult()
            }

            false
        })

        return rootView
    }

    override fun onClick(clickedView: View?) {
        if (clickedView == bt_calculate) {
            displayResult()
        }
    }

    private fun displayResult() {
        val rootView = view!!

        try {
            val feet = rootView.et_feet.text.toString().toDoubleOrNull()
            val inches = rootView.et_inches.text.toString().toDoubleOrNull()
            val pounds = rootView.et_weight.text.toString().toDouble()
            val height = Utils.inchToMeter(feet, inches)
            val weight = Utils.poundToKg(pounds)
            val bmiValue = Utils.getBmiValue(height, weight)
            val bmiText = Utils.getBmiText(context!!, bmiValue)

            rootView.tv_result.text = bmiText
        } catch (e: NumberFormatException) {
            rootView.tv_result.text = ""

            val mainActivity = activity
            if (mainActivity is MainActivity) {
                mainActivity.setToastText(getString(R.string.toast_invalid_number))
            }
        }
    }

}