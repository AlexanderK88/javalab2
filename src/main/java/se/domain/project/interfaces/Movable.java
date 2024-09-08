package se.domain.project.interfaces;

import se.domain.project.Maze;

public interface Movable {
  void move(String direction, Maze maze);
}