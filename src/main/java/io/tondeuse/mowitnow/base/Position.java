package io.tondeuse.mowitnow.base;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Identifie une position de coordonnées (x, y)
 *
 * @author savane vamara
 * 
 */
@AllArgsConstructor(staticName = "at")
@Data
public class Position {

  private final int x;

  private final int y;
}
