package io.tondeuse.mowitnow.unit;

import java.io.File;

import io.tondeuse.mowitnow.io.Loader;
import lombok.SneakyThrows;

import org.junit.Test;

import com.google.common.io.Resources;

import io.tondeuse.mowitnow.mower.Lawn;
import io.tondeuse.mowitnow.mower.Monitor;
import io.tondeuse.mowitnow.mower.Mower;
import static com.google.common.collect.Lists.newLinkedList;
import static io.tondeuse.mowitnow.base.Position.at;
import static io.tondeuse.mowitnow.unit.Constant.A;
import static io.tondeuse.mowitnow.unit.Constant.D;
import static io.tondeuse.mowitnow.unit.Constant.EAST;
import static io.tondeuse.mowitnow.unit.Constant.G;
import static io.tondeuse.mowitnow.unit.Constant.NORTH;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import static org.junit.Assert.assertEquals;

/**
 * Test le chargement et l'execution de plusieurs tondeuses sur une pelouse
 *
 * @author savane vamara
 *
 */
public class MonitorTest {

  private final Lawn p = new Lawn(5, 5);


  @Test
  @SneakyThrows
  public void loaderTest() {
    File file = new File(Resources.getResource("data.txt").toURI());
    Monitor monitor = new Loader().fromFile(file);
    Lawn p = new Lawn(5, 5);
    assertEquals(p, monitor.getLawn());
    assertThat(monitor.getMowers(), contains(mowers()));
  }

  @Test
  public void monitorTest() {
    Monitor monitor = new Monitor(p, asList(mowers()));
    monitor.mow();
    assertEquals(at(1, 2), monitor.getMowers().get(0).position());
    assertEquals(at(4, 0), monitor.getMowers().get(1).position());
  }

  private Mower[] mowers() {
    Mower t1 = new Mower(0, p.cellAt(0, 1), NORTH);
    t1.setInstructions(newLinkedList(asList(G, A, G, A, G, A, G, A, A)));
    Mower t2 = new Mower(1, p.cellAt(2, 2), EAST);
    t2.setInstructions(newLinkedList(asList(A, A, D, A, A, D, A, D, D, A)));
    return new Mower[] {t1, t2};
  }
}
