package com.clint.dynamicui.others

import android.content.Context
import android.widget.EditText
import android.widget.Toast

object UiManager {

    private val allEds: ArrayList<EditText> = ArrayList()
    private val allEdsLabels: ArrayList<String> = ArrayList()
    private val uiMap = HashMap<String, String>()

    fun verifyLabelsAndSubmit(
        context: Context
    ): HashMap<String, String>? {
        var editTextEmpty = false
        allEdsLabels.forEachIndexed { index, item ->
            if (uiMap.contains(item)) {
                if (!allEds[index].text.isNullOrEmpty()) {
                    uiMap[item] = allEds[index].text.toString()
                } else {
                    editTextEmpty = true
                    Toast.makeText(
                        context,
                        "Please fill all the details correctly for $item",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        return if (editTextEmpty) null else uiMap
    }

    fun updateUiMap(key: String, value: String = "") {
        uiMap[key] = value
    }

    fun updateEdsLabel(value: String) {
        allEdsLabels.add(value)
    }

    fun updateAllEds(edittext: EditText) {
        allEds.add(edittext)
    }
}