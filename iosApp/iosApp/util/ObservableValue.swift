//
//  ComponentHolder.swift
//  iosApp
//
//  Created by Taehoon Lee on 2022/08/23.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared
import SwiftUI

class ObservableValue<T: AnyObject> : ObservableObject {
    private let observableValue: Value<T>
    
    @Published
    var value: T
    
    private var observer: ((T) -> Void)?
    
    init(_ value: Value<T>) {
        observableValue = value
        self.value = observableValue.value
        observer = { [weak self] value in self?.value = value }
        observableValue.subscribe(observer: observer!)
    }
    
    deinit {
        observableValue.unsubscribe(observer: self.observer!)
    }
}
