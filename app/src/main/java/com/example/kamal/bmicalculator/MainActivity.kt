package com.example.kamal.bmicalculator

import android.app.Dialog
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
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.color_fragment.*
import kotlinx.android.synthetic.main.fragment_metric.*
import android.app.Activity
import android.R.attr.colorPrimary
import android.R.attr.label
import android.app.ActivityManager.TaskDescription
import android.graphics.Color

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
    var sharedColor: SharedPref? = null
    var view: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedpref = SharedPref(this@MainActivity)
        sharedColor = SharedPref(this@MainActivity)

        if (sharedpref?.loadNightModeState() === true)
        {
            setTheme(R.style.DarkTheme)
        } else if(sharedColor?.loadColorRed()==true){
            setTheme(R.style.RedTheme)
        } else if(sharedColor?.loadDefaultColor()==true){
            setTheme(R.style.AppTheme)
        } else if(sharedColor?.loadColorOrange()==true){
            setTheme(R.style.OrangeTheme)
        } else if(sharedColor?.loadColorSky()==true){
            setTheme(R.style.SkyTheme)
        } else if(sharedColor?.loadColorGreen()==true){
            setTheme(R.style.GreenTheme)
        } else if(sharedColor?.loadColorPink()==true){
            setTheme(R.style.PinkTheme)
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
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_dark -> {
            // User choose the "Dark" item

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
        R.id.action_color->{
            showColor()

            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    fun showColor(){
        val dialog = Dialog(this@MainActivity)
        dialog.setTitle("Choose Color")
        var myLayout = LayoutInflater.from(this).inflate(R.layout.color_fragment, null)
        dialog.setContentView(myLayout)
        dialog.window.setBackgroundDrawableResource(R.drawable.dialog)
        dialog.show()
    }

    fun colorRed(view: View){
        Toast.makeText(this,"Theme Successfully Applied", Toast.LENGTH_SHORT).show();
        sharedColor!!.setColorRed(true)
        sharedColor!!.setDefaultColor(false)
        sharedColor!!.setColorOrange(false)
        sharedColor!!.setColorSky(false)
        sharedColor!!.setColorGreen(false)
        sharedColor!!.setColorPink(false)

        restartApp()

    }

    fun defaultColor(view: View){
        Toast.makeText(this,"Theme Successfully Applied", Toast.LENGTH_SHORT).show();
        sharedColor!!.setDefaultColor(true)
        sharedColor!!.setColorRed(false)
        sharedColor!!.setColorOrange(false)
        sharedColor!!.setColorSky(false)
        sharedColor!!.setColorGreen(false)
        sharedColor!!.setColorPink(false)
        restartApp()

    }
    fun colorOrange(view: View){
        Toast.makeText(this,"Theme Successfully Applied", Toast.LENGTH_SHORT).show();
        sharedColor!!.setColorOrange(true)
        sharedColor!!.setColorRed(false)
        sharedColor!!.setDefaultColor(false)
        sharedColor!!.setColorSky(false)
        sharedColor!!.setColorGreen(false)
        sharedColor!!.setColorPink(false)
        restartApp()

    }
    fun colorSky(view: View){
        Toast.makeText(this,"Theme Successfully Applied", Toast.LENGTH_SHORT).show();
        sharedColor!!.setColorSky(true)
        sharedColor!!.setColorRed(false)
        sharedColor!!.setDefaultColor(false)
        sharedColor!!.setColorOrange(false)
        sharedColor!!.setColorGreen(false)
        sharedColor!!.setColorPink(false)
        restartApp()

    }
    fun colorGreen(view: View){
        Toast.makeText(this,"Theme Successfully Applied", Toast.LENGTH_SHORT).show();
        sharedColor!!.setColorGreen(true)
        sharedColor!!.setColorRed(false)
        sharedColor!!.setDefaultColor(false)
        sharedColor!!.setColorOrange(false)
        sharedColor!!.setColorSky(false)
        sharedColor!!.setColorPink(false)
        restartApp()

    }
    fun colorPink(view: View){
        Toast.makeText(this,"Theme Successfully Applied", Toast.LENGTH_SHORT).show();
        sharedColor!!.setColorPink(true)
        sharedColor!!.setColorRed(false)
        sharedColor!!.setDefaultColor(false)
        sharedColor!!.setColorOrange(false)
        sharedColor!!.setColorSky(false)
        sharedColor!!.setColorGreen(false)
        restartApp()

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

