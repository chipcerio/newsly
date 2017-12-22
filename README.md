# Newsly 📰

Newsly is a **work-in-progress** news tracking Android app, that connects the sources to
[News API](https://newsapi.org/). It is still in development stage.

The app contains a Master/Detail flow type UI and uses RxJava heavily. Added a layer on top of data layer to separate the logic that retrieves the data and maps it to the entity model from the business logic that acts on the model.

It also works offline and has a pagination to fetch more articles.

Also, themeing isn't applied to the app

### Android development

Newsly uses the latest cutting edge libraries, tools, and patterns. As a summary:

 * app is written entirely in [Kotlin](https://kotlinlang.org/)
 * uses [RxJava2](https://github.com/ReactiveX/RxJava)
 * uses [Retrofit](http://square.github.io/retrofit/) for API interface
 * uses [Moshi](https://github.com/square/moshi) for JSON serialization/deserialization
 * uses [Room](https://developer.android.com/topic/libraries/architecture/room.html) for persistence
 * uses [dagger-android](https://google.github.io/dagger/android.html) for dependency injection
 * uses [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) architecture pattern
 * uses [Repository Design Pattern](https://msdn.microsoft.com/en-us/library/ff649690.aspx)
 * uses [Mockito](http://site.mockito.org/) for unit testing

### Motivation

To keep myself updated to any libraries, tools, or patterns that can be applied to an android app

### Development setup

Requires the latest Android Studio 3.0 (or newer) to be able to build the app.

### API keys

You need to register at [News API](https://newsapi.org/) and get an API key

When you obtain the API key, you can add it to the app by putting the following in the
`apikeys.properties` file at the root of the project:

```
# Get key from NewsAPI.org
NewsApiKey=<insert>
```

### TODO

 * surviving orientation change
 * more unit test cases

### Contributions

If you've found an error, you may file an issue.

Patches are encouraged, and may be submitted by forking this project and
sending a pull request would be awesome.