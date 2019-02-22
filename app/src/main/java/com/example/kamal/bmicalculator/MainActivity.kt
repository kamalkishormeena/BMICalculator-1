package com.example.kamal.bmicalculator

import android.content.Intent
import android.graphics.Typeface
import android.graphics.Typeface.createFromAsset
import android.os.Build
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_metric.*

class MainActivity : AppCompatActivity() {


    companion object {
        private const val PAGE_NUM = 2
        private const val POSITION_METRIC = 0
        private const val POSITION_IMPERIAL = 1
    }

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var mToast: Toast? = null
    private var toolbar: Toolbar? = null
    var sharedpref: SharedPref? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedpref = SharedPref(this@MainActivity)

        if (sharedpref?.loadNightModeState() === true)
        {
            setTheme(R.style.DarkTheme)
        }
        else
        {
            setTheme(R.style.AppTheme)

        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = getString(R.string.app_name)
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)

        if (sharedpref?.loadNightModeState()==true) {
            menu?.getItem(0)?.setIcon(ContextCompat.getDrawable(this, R.drawable.toggle_on_24px))
        }
        return super.onCreateOptionsMenu(menu)
    }

    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_dark -> {
            // User chose the "Print" item

            if (sharedpref?.loadNightModeState() === true)
            {
                sharedpref?.setNightModeState(false)
                Toast.makeText(this,"Dark Theme Applied", Toast.LENGTH_SHORT).show();
                item.setIcon(R.drawable.toggle_off_24px)
                restartApp()
            }
            else
            {
                sharedpref?.setNightModeState(true);
                item.setIcon(R.drawable.toggle_on_24px)
                Toast.makeText(this,"Color Theme Applied", Toast.LENGTH_SHORT).show();
                restartApp()
            }

            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    fun restartApp() {
        val i = Intent(getApplicationContext(), MainActivity::class.java)
        startActivity(i)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
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

    fun setToastText(text: String) {
        if (mToast == null) {
            mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT)
        } else {
            mToast!!.setText(text)
        }

        mToast!!.show()
    }


}

