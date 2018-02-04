package kioli.architecturecomponents.mvp

internal interface IPresenter<in S> {
    val view: IView<S>
}