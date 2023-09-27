package com.bagusmerta.taskk.utils.extensions

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset

private const val DATE_PICKER_TAG = "date_picker"

fun AppCompatActivity.showDatePicker(
    selection: LocalDate? = null,
    selectedDate: (LocalDate) -> Unit
) {
    val zoneId = ZoneId.ofOffset("UTC", ZoneOffset.UTC)
    val picker = MaterialDatePicker
        .Builder
        .datePicker()
        .apply {
            if (selection != null) {
                setSelection(selection.atStartOfDay(zoneId).toInstant().toEpochMilli())
            }
        }
        .build()

    picker.show(supportFragmentManager, DATE_PICKER_TAG)
    picker.addOnPositiveButtonClickListener {
        selectedDate(
            Instant
                .ofEpochMilli(it)
                .atZone(zoneId)
                .toLocalDate()
        )
    }
}