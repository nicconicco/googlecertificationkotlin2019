package com.noorganization.googlecertificationkotlin.codelab_sharepreference

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.SharedPreferences
import androidx.core.content.ContextCompat
import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import android.view.View
import com.noorganization.googlecertificationkotlin.R


class SharedPreferenceActivity : AppCompatActivity() {



    // Current count
    private var mCount = 0
    // Current background color
    private var mColor: Int = 0
    // Text view to display both count and color
    private var mShowCountTextView: TextView? = null

    // Key for current count
    private val COUNT_KEY = "count"
    // Key for current color
    private val COLOR_KEY = "color"

    // Shared preferences object
    private var mPreferences: SharedPreferences? = null

    // Name of shared preferences file
    private val sharedPrefFile = "com.example.android.hellosharedprefs"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

        // Initialize views, color, preferences
        mShowCountTextView = findViewById(R.id.count_textview)
        mColor = ContextCompat.getColor(
            this,
            R.color.default_background
        )

        mPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        // Restore preferences
        mCount = mPreferences!!.getInt(COUNT_KEY, 0)
        mShowCountTextView!!.text = String.format("%s", mCount)
        mColor = mPreferences!!.getInt(COLOR_KEY, mColor)
        mShowCountTextView!!.setBackgroundColor(mColor)
    }

    /**
     * Handles the onClick for the background color buttons.  Gets background
     * color of the button that was clicked, and sets the TextView background
     * to that color.
     *
     * @param view The view (Button) that was clicked.
     */
    fun changeBackground(view: View) {
        val color = (view.background as ColorDrawable).color
        mShowCountTextView!!.setBackgroundColor(color)
        mColor = color
    }

    /**
     * Handles the onClick for the Count button.  Increments the value of the
     * mCount global and updates the TextView.
     *
     * @param view The view (Button) that was clicked.
     */
    fun countUp(view: View) {
        mCount++
        mShowCountTextView!!.text = String.format("%s", mCount)
    }

    /**
     * Handles the onClick for the Reset button.  Resets the global count and
     * background variables to the defaults and resets the views to those
     * default values.
     *
     * @param view The view (Button) that was clicked.
     */
    fun reset(view: View) {
        // Reset count
        mCount = 0
        mShowCountTextView!!.text = String.format("%s", mCount)

        // Reset color
        mColor = ContextCompat.getColor(
            this,
            R.color.default_background
        )
        mShowCountTextView!!.setBackgroundColor(mColor)

        // Clear preferences
        val preferencesEditor = mPreferences!!.edit()
        preferencesEditor.clear()
        preferencesEditor.apply()
    }

    /**
     * Callback for activity pause.  Shared preferences are saved here.
     */
    override fun onPause() {
        super.onPause()

        val preferencesEditor = mPreferences!!.edit()
        preferencesEditor.putInt(COUNT_KEY, mCount)
        preferencesEditor.putInt(COLOR_KEY, mColor)
        preferencesEditor.apply()
    }
}
