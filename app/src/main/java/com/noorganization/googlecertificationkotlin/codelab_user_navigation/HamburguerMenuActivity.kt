package com.noorganization.googlecertificationkotlin.codelab_user_navigation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.noorganization.googlecertificationkotlin.R

class HamburguerMenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    /**
     * Creates the content view and toolbar, sets up the drawer layout and the
     * action bar toggle, and sets up the navigation view.
     * @param savedInstanceState    Saved instance state bundle.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hamburguer_menu)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    /**
     * Handles the Back button: closes the nav drawer.
     */
    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    /**
     * Inflates the options menu.
     * @param menu  Menu to inflate
     * @return      Returns true if menu is inflated.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    /**
     * Handles a click on the Settings item in the options menu.
     * @param item  Item in options menu that was clicked.
     * @return      Returns true if the item was Settings.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    /**
     * Handles a navigation drawer item click. It detects which item was
     * clicked and displays a toast message showing which item.
     * @param item  Item in the navigation drawer
     * @return      Returns true after closing the nav drawer
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        // Handle navigation view item clicks here.
        when (item.getItemId()) {
            R.id.nav_camera -> {
                // Handle the camera import action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START)
                displayToast(getString(R.string.chose_camera))
                return true
            }
            R.id.nav_gallery -> {
                // Handle the gallery action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START)
                displayToast(getString(R.string.chose_gallery))
                return true
            }
            R.id.nav_slideshow -> {
                // Handle the slideshow action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START)
                displayToast(getString(R.string.chose_slideshow))
                return true
            }
            R.id.nav_manage -> {
                // Handle the tools action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START)
                displayToast(getString(R.string.chose_tools))
                return true
            }
            R.id.nav_share -> {
                // Handle the share action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START)
                displayToast(getString(R.string.chose_share))
                return true
            }
            R.id.nav_send -> {
                // Handle the send action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START)
                displayToast(getString(R.string.chose_send))
                return true
            }
            else -> return false
        }
    }

    /**
     * Displays a toast message.
     * @param message   Message to display in toast
     */
    private fun displayToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
