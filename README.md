# Project: Mobile Applications SS2019
> Flappy Bird in Java with multiplayer mode

## Installation

How to get to the branch (basic_game):

```sh
git checkout basic_game #Changes the branch
git branch #Checks if it's the correct branch
```

## Usage example

Before compiling and running the game choose "desktop" (instead of "android") in the configurator at the top right bar in Android Studio. After that run the game by clicking on the play button next to it. Hint: Game will restart automatically if you die (


## Project Structure

For the development we use libgdx - a framework which allows us to develop multiplattform applications with java. All objects the user sees in the game were showed by the render method of some game-state (e.g. render method in playState) - there you can change the size of the objects and their position on screen.
In the project folder: 

- android folder - contains basically one class which just starts the business logic of the game, which is located in the core folder
- core folder - contains all classes of the game. SuperBirdsMain - the main class of the game. State package contains all states of the game like - the menuState which is activated at start of the game - or the playState which is activated if user clicked on the play button of the game.
  The gameObjects package contains all classes which are responsible for the behaviour of some objects in the game (e.g. birdController, pipeController)


## Roadmap

- [ ] Basic game
- [x] Persistence
- [ ] Networking
- [ ] Polishing
