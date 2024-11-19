@Composable
fun NiaApp(
    appState: NiaAppState,
) {
    NiaApp(appState)
}

@Composable
internal fun NiaApp(
    appState: NiaAppState,
    x: () -> Unit = {},
) {
    NiaNavHost()
}
