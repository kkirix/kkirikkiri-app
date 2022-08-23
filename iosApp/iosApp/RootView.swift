//
//  RootView.swift
//  iosApp
//
//  Created by Taehoon Lee on 2022/08/23.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared
import SwiftUI

struct RootView: View {
    
    private let root: KRXRoot
    
    @ObservedObject
    private var childStack: ObservableValue<ChildStack<AnyObject, KRXRootChild>>
    
    private var activeChild: KRXRootChild {
        childStack.value.active.instance
    }
    
    init(_ root: KRXRoot) {
        self.root = root
        childStack = ObservableValue(root.childStack)
    }
    
    var body: some View {
        switch activeChild {
        case let login as KRXRootChildLogin:
            LoginView(component: login.component)
        case let main as KRXRootChildMain:
            MainView(component: main.component)
        default: EmptyView()
        }
    }
}
