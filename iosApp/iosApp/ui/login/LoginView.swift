//
//  LoginView.swift
//  iosApp
//
//  Created by Taehoon Lee on 2022/08/23.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LoginView: View {
    
    private let component: KRXLogin
    
    @ObservedObject
    private var observableModel: ObservableValue<KRXLoginModel>
    
    private var model: KRXLoginModel {
        observableModel.value
    }
    
    init(component: KRXLogin) {
        self.component = component
        observableModel = ObservableValue(component.model)
    }
    
    var body: some View {
        let emailBinding = Binding(get: { model.email }, set: component.onEmailChanged)
        let passwdBinding = Binding(get: { model.password }, set: component.onPasswdChanged)
        
        VStack {
            TextEditor(text: emailBinding)
                .frame(width: 200, height: 30)
                .textFieldStyle(.roundedBorder)
                .border(.black)
            
            TextEditor(text: passwdBinding)
                .frame(width: 200, height: 30)
                .textFieldStyle(.roundedBorder)
                .border(.black)
            
            Button("Login", action: component.onLogin)
                .disabled(!model.isAvailableLogin)
        }
    }
}
