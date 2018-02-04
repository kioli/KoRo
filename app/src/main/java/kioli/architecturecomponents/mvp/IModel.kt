package kioli.architecturecomponents.mvp

internal interface IModel<out S> {
    val status: S
}