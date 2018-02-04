package kioli.architecturecomponents.mvp

internal interface IView<in S> {
    fun render(status: S)
}