# Tic Tac Toe

### This project focuses on Low Level Design.
#### The code is structured to be extensible and open to change in requirements.

### Features
- Run multiple Tic Tac Toe games locally over the command line, using threads to manage moves in concurrent games.
- Run multiple Tic Tac Toe games over a network connection.
- Tweak Tic Tac Toe, make a bigger grid, modify the streak size (characters in a row required to win).
<hr />

### How To Run
- Run `Game.Main` to launch the command line version.
- Run `ServerClient.MultithreadedSockerServer` and as many instances of `ServerClient.TCPClient` as you wish to play concurrent and parallel games over the network.
<hr />

### Scope For Improvement
- Exception Handling, custom exceptions and graceful recovery.
- The Client Server version needs a better Client Protocol, allowing configurable games.
- Currently, the Client side of the Client Server version doesn't perform validation checks, those are done on the Server side. The Board is sent over by the server, although a tic-tac-toe board is not huge, it still is a waste of network calls.
