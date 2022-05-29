package michael.testapp.breathSession.viewModel

import android.os.CountDownTimer
import michael.testapp.breathSession.SessionDelegate
import michael.testapp.breathSession.SessionParams

class BreathViewModel {



    fun startSession() {

        timer = object : CountDownTimer(params.sessionLength*1000L, 1000L) {
            override fun onTick(p0: Long) {
                updateSession()
                listener?.onChange(currentParams)
            }

            override fun onFinish() {
                timer?.cancel()
                listener?.changeEnabled(true)
            }

        }
        timer?.start()
        listener?.changeEnabled(false)
    }

    private fun updateSession() {
        currentParams =  currentParams.copy(sessionLength = currentParams.sessionLength - 1)

        if(currentParams.breathIn > 0) {
            currentParams = currentParams.copy(breathIn = currentParams.breathIn - 1)
        } else if(currentParams.sessionPause1 > 0) {
            currentParams = currentParams.copy(sessionPause1 = currentParams.sessionPause1 - 1)
        } else if(currentParams.breathOut > 0) {
            currentParams = currentParams.copy(breathOut = currentParams.breathOut - 1)
        } else if(currentParams.sessionPause2 > 0) {
            currentParams = currentParams.copy(sessionPause2 = currentParams.sessionPause2 - 1)
        } else{
            currentParams = params.copy(sessionLength = currentParams.sessionLength)
        }
    }

    fun setSessionParams(len: Int, del1: Int, del2: Int, bIn: Int, bOut: Int) {
        params = SessionParams(len, del1, del2, bIn, bOut)
    }


    var params: SessionParams = SessionParams()
    var currentParams = params.copy()

    var timer: CountDownTimer? = null

    var listener: SessionDelegate? = null
}