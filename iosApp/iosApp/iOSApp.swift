import SwiftUI
import shared

@main
struct iOSApp: App {
	
	@UIApplicationDelegateAdaptor(AppDelegate.self)
	var appDelegate: AppDelegate
	
	@Environment(\.scenePhase)
	var scenePhase: ScenePhase
	
	var body: some Scene {
        let mainViewControllerComponent = InjectMainViewControllerComponent(
            applicationComponent: appDelegate.applicationComponent
        )

		WindowGroup {
            ContentView(mainViewControllerComponent: mainViewControllerComponent)
		}
	}
}
