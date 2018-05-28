package com.example.kamal.bmicalculator

import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PAGE_NUM = 2
        private const val POSITION_METRIC = 0
        private const val POSITION_IMPERIAL = 1
    }

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var mToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                POSITION_METRIC -> MetricFragment.newInstance()
                POSITION_IMPERIAL -> ImperialFragment.newInstance()
                else -> throw RuntimeException("invalid position: " + position)
            }
        }

        override fun getCount(): Int {
            return PAGE_NUM
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                POSITION_METRIC -> getString(R.string.tab_title_metric)
                POSITION_IMPERIAL -> getString(R.string.tab_title_imperial)
                else -> throw RuntimeException("invalid position: " + position)
            }
        }
    }

    public fun setToastText(text: String) {
        if (mToast == null) {
            mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT)
        } else {
            mToast!!.setText(text)
        }

        mToast!!.show()
    }

}
