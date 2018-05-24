package kioli.koro.presentation

const val eventResult = "Result"

enum class Events(val id: String) {
    LoadQuoteStart("load quote start"),
    LoadQuoteResultOk("load quote result ok"),
    LoadQuoteResultFail("load quote result fail"),
    SaveQuoteStart("save quote start"),
    SaveQuoteResultOk("save quote result ok"),
    SaveQuoteResultFail("save quote result fail"),
    ClearQuoteStart("clear quote start"),
    ClearQuoteResultOk("clear quote result ok"),
    ClearQuoteResultFail("clear quote result fail")
}