package com.descolab.bacadulucom.main

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.descolab.bacadulucom.App
import com.descolab.bacadulucom.R
import com.descolab.bacadulucom.category.CategoryFragment
import com.descolab.bacadulucom.helper.Utils
import com.descolab.bacadulucom.home.HomeFragment
import com.descolab.bacadulucom.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        @JvmStatic lateinit var instance: MainActivity
        @JvmStatic var popup = 0
    }

    init {
        instance = this
    }

    private var menu: Menu? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.frame_container, HomeFragment(), null)
        transaction.commit()


        App.get().sharedPreferences1000

/*        setSupportActionBar(toolbarid)
        // Now get the support action bar
        val actionBar = supportActionBar

        // Set toolbar title/app title
        actionBar!!.title = ""

        // Display the app icon in action bar/toolbar
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayUseLogoEnabled(true)*/

        navigation.enableShiftingMode(false)
        navigation.enableItemShiftingMode(false)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_container)
        navigation.onNavigationItemSelectedListener = null
        currentFragment?.let { setBottomMenu(it) }
        navigation.onNavigationItemSelectedListener = mOnNavigationItemSelectedListener
    }

    override fun onResume() {
        super.onResume()
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_container)
        navigation.onNavigationItemSelectedListener = null
        currentFragment?.let { setBottomMenu(it) }
        navigation.onNavigationItemSelectedListener = mOnNavigationItemSelectedListener
    }


    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    if (supportFragmentManager.findFragmentById(R.id.frame_container) !is HomeFragment) {
                        supportFragmentManager.findFragmentById(R.id.frame_container)?.let {
                            Utils.addFragment(
                                it,
                                HomeFragment(), null
                            )
                        }
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_category -> {
                    if (supportFragmentManager.findFragmentById(R.id.frame_container) !is CategoryFragment) {
                        supportFragmentManager.findFragmentById(R.id.frame_container)?.let {

                            Utils.addFragment(
                                it,
                                CategoryFragment(), null
                            )
                        }
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_profil -> {
                    if (supportFragmentManager.findFragmentById(R.id.frame_container) !is ProfileFragment) {
                        supportFragmentManager.findFragmentById(R.id.frame_container)?.let {

                            Utils.addFragment(
                                it,
                                ProfileFragment(), null
                            )

                        }
                    }
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    fun setBottomMenu(fragment: Fragment) {
        navigation.visibility = View.VISIBLE
        if (fragment is HomeFragment) {
            navigation.currentItem = 0
        } else if (fragment is CategoryFragment) {
            navigation.currentItem = 1
        } else if (fragment is ProfileFragment) {
            navigation.currentItem = 2
        } else {
            navigation.visibility = View.GONE
        }
    }




}