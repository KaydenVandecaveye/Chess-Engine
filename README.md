# Chess Engine

## Description

This Chess Engine is a fully functional chess game built using Java. It allows two-player gameplay (PvP) or player vs. computer (PvC) mode, with an AI opponent. The game follows traditional chess rules and includes a graphical interface developed with Java Swing.

## Motivation

This project was originally a terminal based chess project for my CSCI 1933 class (Data Structures & Algorithms), after it's completion I decided I wanted to add more features because I love chess and wanted to explore programming concepts such as game trees, AI algorithms, and user interface design. The goal was to build a customizable chess game that could be expanded with new features like an AI bot.

## Features

### Core Features:
- Interactive Chessboard: Move pieces, track turns, and handle special moves (pawn promotion, castling, en passant).
- Player vs. Player (PvP): Two players can compete against each other. (On the same computer as of right now) 
- Player vs. Computer (PvC): Play against a basic AI.
- Move Validation: Ensures legal moves, checks, and checkmate detection.
- Piece Promotion: Pawns can be promoted to other pieces (queen, rook, bishop, or knight).

### User Interface:
- Graphical Interface: Simple UI with images representing chess pieces.
- Move Highlights: Shows valid moves when a piece is selected.
- Turn Indicator: Displays whose turn it is.
- Move Log: Displays past moves in chess notation.

### AI Features:
- Basic AI: Uses a minimax algorithm with alpha-beta pruning for choosing the "best" move relatively quickly. 

### Potential Future Enhancements:
- Advanced AI: The AI as of right now is okay but not very strong. The main issue is speed so in the future I could make try to make the bot search faster (which allows it to look more moves ahead) to improve it's strength.
