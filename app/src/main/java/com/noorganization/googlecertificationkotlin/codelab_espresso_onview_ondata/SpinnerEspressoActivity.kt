package com.noorganization.googlecertificationkotlin.codelab_espresso_onview_ondata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.noorganization.googlecertificationkotlin.R

class SpinnerEspressoActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val TAG = SpinnerEspressoActivity::class.java.simpleName
    private var mSpinnerLabel = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner_espresso)

        // Create the spinner.
        val spinner = findViewById<Spinner>(R.id.label_spinner)
        if (spinner != null) {
            spinner.onItemSelectedListener = this
        }

        // Create ArrayAdapter using the string array and default
        // spinner layout.
        val adapter = ArrayAdapter.createFromResource(
            this, R.array.labels_array,
            android.R.layout.simple_spinner_item
        )
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner.
        if (spinner != null) {
            spinner.adapter = adapter
        }
    }

    /**
     * Retrieves the text and spinner item and shows them in text_phonelabel.
     *
     * @param view  The view containing editText_main.
     */
    fun showText(view: View) {
        val editText = findViewById<EditText>(R.id.editText_main)
        val textView = findViewById<TextView>(R.id.text_phonelabel)
        if (editText != null) {
            // Assign to showString both the entered string and mSpinnerLabel.
            val showString = editText.text.toString() +
                    " - " + mSpinnerLabel
            // Display a Toast message with showString
            Toast.makeText(this, showString, Toast.LENGTH_SHORT).show()
            // Set the TextView to showString.
            textView.setText(showString)
        }
    }

    /**
     * Retrieves the selected item in the spinner using getItemAtPosition,
     * and assigns it to mSpinnerLabel.
     *
     * @param adapterView   The adapter for the spinner, where the selection
     * occurred.
     * @param view          The view within the adapterView that was clicked.
     * @param i             The position of the view in the adapter.
     * @param l             The row id of the item that is selected (not
     * used here).
     */
    override fun onItemSelected(
        adapterView: AdapterView<*>,
        view: View, i: Int, l: Long
    ) {
        mSpinnerLabel = adapterView.getItemAtPosition(i).toString()
        showText(view)
    }

    /**
     * Logs the fact that nothing was selected in the spinner.
     *
     * @param adapterView   The adapter for the spinner, where the selection
     * should have occurred.
     */
    override fun onNothingSelected(adapterView: AdapterView<*>) {
        Log.d(TAG, "onNothingSelected: ")
    }
}
