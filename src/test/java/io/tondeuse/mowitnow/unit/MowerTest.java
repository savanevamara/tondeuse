package io.tondeuse.mowitnow.unit;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import io.tondeuse.mowitnow.base.Cell;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.collect.Lists;

import io.tondeuse.mowitnow.base.Orientation;
import io.tondeuse.mowitnow.mower.Instruction;
import io.tondeuse.mowitnow.mower.Lawn;
import io.tondeuse.mowitnow.mower.Mower;
import io.tondeuse.mowitnow.unit.MowerTest.Data.DataBuilder;
import io.tondeuse.mowitnow.util.AsciiGrid;
import static io.tondeuse.mowitnow.unit.Constant.A;
import static io.tondeuse.mowitnow.unit.Constant.D;
import static io.tondeuse.mowitnow.unit.Constant.EAST;
import static io.tondeuse.mowitnow.unit.Constant.G;
import static io.tondeuse.mowitnow.unit.Constant.NORTH;
import static io.tondeuse.mowitnow.unit.Constant.SOUTH;
import static io.tondeuse.mowitnow.unit.Constant.WEST;

import static org.junit.Assert.assertEquals;

/**
 * Test le fonctionnement d'une tondeuse
 *
 * @author savane vamara
 *
 */
@Slf4j
@RunWith(JUnitParamsRunner.class)
public class MowerTest {

  @Test
  @Parameters
  public void startTest(final Data data) {
    data.mowers.start();
    assertEquals(data.expectedOrientation, data.mowers.getOrientation());
    assertEquals(data.expectedCell, data.mowers.getCell());
  }

  public Object[][] parametersForStartTest() {
    return new Object[][] {
        {DataBuilder.lawn(2, 2).mower(0, 0, EAST).expected(0, 0, EAST)},
        {DataBuilder.lawn(1, 1).mower(0, 0, EAST).todo(D).expected(0, 0, SOUTH)},
        {DataBuilder.lawn(1, 1).mower(0, 0, EAST).todo(G).expected(0, 0, NORTH)},
        {DataBuilder.lawn(2, 2).mower(0, 0, WEST).todo(G, G, G, G).expected(0, 0, WEST)},
        {DataBuilder.lawn(5, 5).mower(2, 2, WEST).todo(D, A, A, G, A, A, G, A, G, A)
            .expected(1, 3, EAST)},};
  }

  @lombok.Data
  static class Data implements Observer {

    private Lawn lawn;
    private Mower mowers;
    private Cell expectedCell;
    private Orientation expectedOrientation;

    Cell on(final int x, final int y) {
      return lawn.cellAt(x, y);
    }

    @Override
    public void update(final Observable o, final Object arg) {
      log.debug(AsciiGrid.draw(lawn, mowers));
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    static class DataBuilder {

      final Data data = new Data();

      static DataBuilder lawn(final int width, final int height) {
        DataBuilder builder = new DataBuilder();
        builder.data.lawn = new Lawn(width, height);
        return builder;
      }

      DataBuilder mower(final int x, final int y, final Orientation o) {
        data.mowers = new Mower(data.on(x, y), o);
        data.mowers.addObserver(data);
        return this;
      }

      DataBuilder todo(final Instruction... instructions) {
        data.mowers.setInstructions(Lists.newLinkedList(Arrays.asList(instructions)));
        return this;
      }

      Data expected(final int x, final int y, final Orientation o) {
        data.expectedCell = data.on(x, y);
        data.expectedOrientation = o;
        return data;
      }
    }
  }
}
