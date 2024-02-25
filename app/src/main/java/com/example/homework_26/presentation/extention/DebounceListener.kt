package com.example.homework_26.presentation.extention

import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.AppCompatEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun AppCompatEditText.onDebouncedListener(
    debounceDuration: Long = 600L,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Main),
    onChanged: (String) -> Unit,
) {
    var debounceJob: Job? = null

    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            debounceJob?.cancel()

            if (s.toString().isEmpty()) {
                onChanged(s.toString())
            } else {
                debounceJob = scope.launch {
                    delay(debounceDuration)
                    s?.toString()?.let { onChanged(it) }
                }
            }
        }
    })
}