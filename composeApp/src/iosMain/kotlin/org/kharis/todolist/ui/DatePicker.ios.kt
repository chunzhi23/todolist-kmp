package org.kharis.todolist.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ObjCAction
import kotlinx.datetime.LocalDate
import platform.Foundation.*
import platform.UIKit.*

@Composable
actual fun PlatformDatePickerDialog(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onDismissRequest: () -> Unit,
) {
    LaunchedEffect(Unit) {
        val viewController =
            DatePickerViewController(
                selectedDate = selectedDate,
                onDateSelected = onDateSelected,
                onDismiss = onDismissRequest,
            )

        val rootVC = UIApplication.sharedApplication.keyWindow?.rootViewController
        rootVC?.presentViewController(viewController, animated = true, completion = null)
    }
}

@Suppress("MISSING_DEPENDENCY_SUPERCLASS_WARNING")
class DatePickerViewController(
    private val selectedDate: LocalDate,
    private val onDateSelected: (LocalDate) -> Unit,
    private val onDismiss: () -> Unit,
) : UIViewController(nibName = null, bundle = null) {
    private val datePicker =
        UIDatePicker().apply {
            datePickerMode = UIDatePickerMode.UIDatePickerModeDate
            preferredDatePickerStyle = UIDatePickerStyle.UIDatePickerStyleWheels
            this.date = selectedDate.toNSDate()
            translatesAutoresizingMaskIntoConstraints = false
        }

    override fun viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = UIColor.systemBackgroundColor

        val doneButton = UIButton.buttonWithType(UIButtonTypeSystem) as UIButton
        doneButton.setTitle("확인", forState = UIControlStateNormal)
        doneButton.translatesAutoresizingMaskIntoConstraints = false
        doneButton.addTarget(
            action = "onDone",
            forControlEvents = UIControlEventTouchUpInside,
        )

        view.addSubview(datePicker)
        view.addSubview(doneButton)

        datePicker.centerXAnchor.constraintEqualToAnchor(view.centerXAnchor).active = true
        datePicker.centerYAnchor.constraintEqualToAnchor(view.centerYAnchor).active = true

        doneButton.topAnchor.constraintEqualToAnchor(datePicker.bottomAnchor, constant = 16.0).active = true
        doneButton.centerXAnchor.constraintEqualToAnchor(view.centerXAnchor).active = true
    }

    @OptIn(BetaInteropApi::class)
    @ObjCAction
    fun onDone() {
        val pickedDate = datePicker.date.toKotlinLocalDate()
        dismissViewControllerAnimated(true) {
            onDateSelected(pickedDate)
        }
    }

    override fun viewWillDisappear(animated: Boolean) {
        super.viewWillDisappear(animated)
        onDismiss()
    }
}

fun LocalDate.toNSDate(): NSDate {
    val components =
        NSDateComponents().apply {
            year = this@toNSDate.year.toLong()
            month = this@toNSDate.monthNumber.toLong()
            day = this@toNSDate.dayOfMonth.toLong()
        }
    return NSCalendar.currentCalendar.dateFromComponents(components)!!
}

fun NSDate.toKotlinLocalDate(): LocalDate {
    val calendar = NSCalendar.currentCalendar
    val components =
        calendar.components(
            NSCalendarUnitYear or NSCalendarUnitMonth or NSCalendarUnitDay,
            fromDate = this,
        )
    return LocalDate(
        year = components.year.toInt(),
        monthNumber = components.month.toInt(),
        dayOfMonth = components.day.toInt(),
    )
}
