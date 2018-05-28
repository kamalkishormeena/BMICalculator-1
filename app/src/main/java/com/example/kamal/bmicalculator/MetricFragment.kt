package com.example.kamal.bmicalculator

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.example.kamal.bmicalculator.databinding.FragmentMetricBinding
import com.example.kamal.bmicalculator.model.BodyJava
import kotlinx.android.synthetic.main.fragment_metric.*
import kotlinx.android.synthetic.main.fragment_metric.view.*

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

    override fun onClick(clickedView: View?) {
        if (clickedView == bt_calculate) {
            displayResult()
        }
    }

    private fun displayResult() {
        val rootView = view!!

        val bmiText = Utils.getBmiText(context!!, body!!.bmi)
        rootView.tv_result.text = bmiText
    }

}