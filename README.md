<h1 align="center">Dynamic UI</h1>
<p align="center"> The app fetches network details from a separate library and generates a dynamic layout.</p>

### Architecture and working 

* It is following MVVM architecture. 
* Tried to reduce business logic from the view layer as much as possible. 
* All the object creation and updations are handled by Extension , Utils, and manager classes. 

### How network calls are made 

* I'm using the .aar file of this __[library](https://github.com/clint22/NetWorkManager)__( Network module ) which I created to decouple it from the app. Please refer to the documentation to see how to make calls.

### Please note 

* I'm using a temporary JSON bin to fetch the details. If the network call is failing using this app, you have to change the base URL from the __[Network](https://github.com/clint22/NetWorkManager)__ module library and the end point point from the app.

### Find this repo useful 🤟?
Please star it 🌟 and follow me on __[LinkedIn](https://www.linkedin.com/in/clint-paul-2504bba7/)__ 

Also, do checkout my __[blog](https://clintpauldev.com/)__ for Android related articles.
