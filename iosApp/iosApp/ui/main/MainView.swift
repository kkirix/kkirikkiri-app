//
//  MainView.swift
//  iosApp
//
//  Created by Taehoon Lee on 2022/08/23.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MainView: View {
    
    private let component: KRXMain
    
    init(component: KRXMain) {
        self.component = component
    }
    
    var body: some View {
        Text("Main")
    }
}
