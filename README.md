[![](https://jitpack.io/v/m0er/androidannotations-interval-click-plugin.svg)](https://jitpack.io/#m0er/androidannotations-interval-click-plugin)

# AndroidAnnotations Interval Click Plugin
An [Android Annotations](https://github.com/androidannotations/androidannotations/) custom plugin to prevent multiple click.

## Usage

Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
	repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

Add the dependency:

```groovy
dependencies {
    apt 'org.androidannotations:androidannotations:4.1.0'
    compile 'org.androidannotations:androidannotations-api:4.1.0'
    apt 'com.github.m0er.androidannotations-interval-click-plugin:intervalclick:1.0.2'
    compile 'com.github.m0er.androidannotations-interval-click-plugin:intervalclick-api:1.0.2'
}
```

Default interval delay is 600ms.

```java
@IntervalClick
void someButton() {
    Toast.makeText(this, "Click!!", Toast.LENGTH_SHORT).show();
}
```

You can customize it:

```java
@IntervalClick(intervalMilliseconds = 1000)
void someButton() {
    Toast.makeText(this, "Click!!", Toast.LENGTH_SHORT).show();
}
```
