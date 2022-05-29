package michael.testapp.breathSession.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import michael.testapp.R
import michael.testapp.breathSession.SessionDelegate
import michael.testapp.breathSession.SessionParams
import michael.testapp.breathSession.viewModel.BreathViewModel

class BreathFragment : Fragment(), SessionDelegate {

    private val viewModel = BreathViewModel()

    private lateinit var startBox: TextView

    private lateinit var pauseBox1: TextView

    private lateinit var endBox: TextView

    private lateinit var pauseBox2: TextView

    private lateinit var startSession: Button

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_breath_session, container, false)

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        val i = 1
        val o = 2
        val p1 = 3
        val p2 = 4

        viewModel.setSessionParams(i+o+p1+p2, p1, p2, i, o)
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        startBox = v.findViewById(R.id.et_breath_in)
        pauseBox1 = v.findViewById(R.id.et_breath_pause_in)
        endBox = v.findViewById(R.id.et_breath_out)
        pauseBox2 = v.findViewById(R.id.et_breath_pause_out)
        startSession = v.findViewById(R.id.bt_start_session)



        startSession.setOnClickListener {
            viewModel.startSession()
        }
    }

    override fun onChange(params: SessionParams) {
        startBox.text = params.breathIn.toString()
        pauseBox1.text = params.sessionPause1.toString()
        pauseBox2.text = params.sessionPause2.toString()
        endBox.text = params.breathOut.toString()
    }

    override fun changeEnabled(isEnabled: Boolean) {
        startSession.isEnabled = isEnabled
    }
}