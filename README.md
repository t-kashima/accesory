Accesory
============
Android library for displaying a draggable image through application.

Example
-------

The Accesory can be initialized in your Application class.

```kotlin
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Accesory.init(this, R.drawable.image)
    }
}
```
