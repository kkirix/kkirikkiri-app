import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        IosModuleKt.startKoin()
    }
    
    @StateObject
    private var rootHolder = RootHolder()
    
	var body: some Scene {
		WindowGroup {
            RootView(rootHolder.root)
		}
	}
}

private class RootHolder : ObservableObject {
    
    let lifecycle: LifecycleRegistry
    let root: KRXRoot
    
    init() {
        lifecycle = LifecycleRegistryKt.LifecycleRegistry()
        root = RootComponent(componentContext: DefaultComponentContext(lifecycle: lifecycle))
        lifecycle.onCreate()
    }
    
    deinit {
        lifecycle.onDestroy()
    }
}
