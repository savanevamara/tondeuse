package io.tondeuse.mowitnow.base;

/**
 * Represente un objet mobile
 *
 * @author savane vamara
 * 
 */
public interface Movable {

  /**
   * Avancer
   */
  void advance();

  /**
   * Pivoter à droite
   */
  void rotateRight();

  /**
   * Pivoter à gauche
   */
  void rotateLeft();
}
