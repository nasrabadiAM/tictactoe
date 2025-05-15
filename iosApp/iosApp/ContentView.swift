import UIKit
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {

    let mainViewControllerComponent: InjectMainViewControllerComponent

    init(mainViewControllerComponent: InjectMainViewControllerComponent) {
        self.mainViewControllerComponent = mainViewControllerComponent
    }

    func makeUIViewController(context: Context) -> UIViewController {
        return mainViewControllerComponent.mainViewControllerFactory()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {

    let mainViewControllerComponent: InjectMainViewControllerComponent

    init(mainViewControllerComponent: InjectMainViewControllerComponent) {
        self.mainViewControllerComponent = mainViewControllerComponent
    }

    var body: some View {
        ComposeView(mainViewControllerComponent: mainViewControllerComponent)
            .ignoresSafeArea(.keyboard)
            .edgesIgnoringSafeArea(.all)
    }
}