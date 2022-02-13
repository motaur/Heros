# Android Application "Heroes"

With this application you can find your favorite superhero from any Universe and Publishers and share it with your friends.
The application uses https://superheroapi.com


## Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com:motaur/heroes.git
```

## Configuration
1. Get your api key from https://superheroapi.com
2. Create file `apikey.properties` in the root of the project with the following info:
```gradle
HEROES_API_KEY="..."
```


## Build variants
Use the Android Studio *Build Variants* button to choose between **debug** and **mock** flavors combined with debug and release build types

## Architecture
The App uses MVVM Design Pattern.
![Alt text](arch.png?raw=true "Architecture")

+ <strong>ViewModels</strong> are responsible for UI logic; <br />
+ <strong>Services</strong> are responsible for business logic such as models mapping, data validations etc.<br />
+ <strong>Repositories</strong> are abstractions for data sources, such as API, Database and so on.<br />
