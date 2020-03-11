package io.tondeuse.mowitnow.mower;

/**
 * Represente l'ensemble des instructions prévues
 *
 * @author savane vamara
 * 
 */
public enum Instruction {

  ROTATE_RIGHT {
    @Override
    public void executer(final Mower mower) {
      mower.rotateRight();
    }
  },

  ROTATE_LEFT {
    @Override
    public void executer(final Mower mower) {
      mower.rotateLeft();
    }
  },

  FORWARD {
    @Override
    public void executer(final Mower mower) {
      mower.advance();
    }
  };

  abstract void executer(final Mower mower);
}
