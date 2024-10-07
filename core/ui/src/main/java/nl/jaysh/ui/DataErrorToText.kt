package nl.jaysh.ui

import nl.jaysh.football.core.domain.model.error.DataError
import nl.jaysh.football.core.ui.R

fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Local.DISK_FULL -> UiText.StringResource(
            R.string.error_disk_full,
        )
        DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(
            R.string.error_timeout,
        )
        DataError.Network.TOO_MANY_REQUESTS -> UiText.StringResource(
            R.string.error_too_many_requests,
        )
        DataError.Network.NO_INTERNET -> UiText.StringResource(
            R.string.error_no_internet,
        )
        DataError.Network.PAYLOAD_TOO_LARGE -> UiText.StringResource(
            R.string.error_payload_too_large,
        )
        DataError.Network.SERVER_ERROR -> UiText.StringResource(R.string.error_something_went_wrong)
        DataError.Network.SERIALIZATION -> UiText.StringResource(R.string.error_serialization)
        else -> UiText.StringResource(R.string.error_something_went_wrong)
    }
}