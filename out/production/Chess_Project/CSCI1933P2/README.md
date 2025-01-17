# Chess Engine

## Description

This Chess Engine is a fully functional chess game built using Java. It allows two-player gameplay (PvP) or player vs. computer (PvC) mode, with an AI opponent. The game follows traditional chess rules and includes a graphical interface developed with Java Swing.

## Motivation

This project was originally a simple chess terminal project for my CSCI 1933 class (Data Structures & Algorithms), after it's completion I decided I wanted to add more features because I love chess and wanted to explore programming concepts such as game trees, AI algorithms, and user interface design. The goal was to build a customizable chess game that could be expanded with new features like enhanced AI, multiplayer, and more.

## Features

### Core Features:
- Interactive Chessboard: Move pieces, track turns, and handle special moves (pawn promotion, castling, en passant).
- Player vs. Player (PvP): Two players can compete against each other.
- Player vs. Computer (PvC): Play against a basic AI with adjustable difficulty.
- Move Validation: Ensures legal moves, checks, and checkmate detection.
- Piece Promotion: Pawns can be promoted to other pieces (queen, rook, bishop, or knight).
- Move Log: Games are tracked using PGN (portable game notation) and full games can be downloaded.

### User Interface:
- Graphical Interface: Simple UI with images representing chess pieces.
- Move Highlights: Shows valid moves when a piece is selected.
- Turn Indicator: Displays whose turn it is.

### AI Features(Coming Soon):
- Basic AI: Makes random legal moves or uses a basic heuristic based on piece value.
- Difficulty Adjustment: Change the AI's difficulty by modifying its evaluation strategy.

### Potential Future Enhancements:

- Advanced AI: Implement techniques like minimax and alpha-beta pruning for smarter AI.
- Multiplayer Mode: Support online multiplayer via sockets or platforms like chess.com.
- Move History & Replay: Save and replay game moves.
- Chess Opening Book: Integrate popular opening strategies for AI.
- Game Analysis: Provide suggestions or recommendations based on engine analysis.
