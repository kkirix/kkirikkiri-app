import androidx.compose.material.Text
import androidx.compose.ui.window.Application
import com.kkirrix.kkirikkiri.LoginContent
import com.kkirrix.kkirikkiri.naver.AuthCallback
import com.kkirrix.kkirikkiri.naver.Naver
import kotlinx.cinterop.*
import platform.Foundation.NSError
import platform.Foundation.NSStringFromClass
import platform.UIKit.*
import kotlin.naver.login.NaverThirdPartyLoginConnection

fun main() {
    val args = emptyArray<String>()
    memScoped {
        val argc = args.size + 1
        val argv = (arrayOf("skikoApp") + args).map { it.cstr.ptr }.toCValues()
        autoreleasepool {
            UIApplicationMain(argc, argv, null, NSStringFromClass(SkikoAppDelegate))
        }
    }
}

class SkikoAppDelegate : UIResponder, UIApplicationDelegateProtocol {
    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta

    @ObjCObjectBase.OverrideInit
    constructor() : super()

    private var _window: UIWindow? = null
    override fun window() = _window
    override fun setWindow(window: UIWindow?) {
        _window = window
    }

    override fun application(application: UIApplication, didFinishLaunchingWithOptions: Map<Any?, *>?): Boolean {
        Naver.auth.initialize(application, "AbHsG_wTKLoq9k5eOK4A", "WZHJxwDCDf", "끼리끼리")
        window = UIWindow(frame = UIScreen.mainScreen.bounds)
        window!!.rootViewController = Application("KKiriKKiri") {
//            LoginContent(this)
            Text("Hello World")
        }
        window!!.makeKeyAndVisible()
        return true
    }
}