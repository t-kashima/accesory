Accesory
============
Android library for displaying a draggable image through application.

<img src="https://i.gyazo.com/fbe0a589532b6a36cd47c4bea1ff7097.gif" width="300">

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

Download
-------

```groovy
compile 'com.unuuu.android.accesory:accesory:1.0.0'
```