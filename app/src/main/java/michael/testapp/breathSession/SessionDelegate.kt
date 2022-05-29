package michael.testapp.breathSession

interface SessionDelegate{
    fun onChange(params: SessionParams)
    fun changeEnabled(isEnabled: Boolean)
}