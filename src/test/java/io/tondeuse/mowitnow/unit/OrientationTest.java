package io.tondeuse.mowitnow.unit;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.tondeuse.mowitnow.base.Orientation;

/**
 * Test les differentes orientations 
 *
 * @author savane vamara
 *
 */
@RunWith(JUnitParamsRunner.class)
public class OrientationTest {

  @Test
  @Parameters({"EAST, SOUTH", "SOUTH, WEST", "WEST, NORTH", "NORTH, EAST"})
  public void rightTest(final Orientation initiale, final Orientation finale) {
    Assert.assertEquals(initiale.right(), finale);
  }

  @Test
  @Parameters({"EAST, NORTH", "NORTH, WEST", "WEST, SOUTH", "SOUTH, EAST"})
  public void leftTest(final Orientation initiale, final Orientation finale) {
    Assert.assertEquals(initiale.left(), finale);
  }
}
