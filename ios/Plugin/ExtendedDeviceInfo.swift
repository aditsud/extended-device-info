import Foundation

@objc public class ExtendedDeviceInfo: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
