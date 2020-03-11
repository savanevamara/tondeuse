package io.tondeuse.mowitnow.io.parseur;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.RequiredArgsConstructor;

import com.google.common.base.Splitter;

import io.tondeuse.mowitnow.base.Orientation;
import io.tondeuse.mowitnow.mower.Lawn;
import io.tondeuse.mowitnow.mower.Mower;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * Renvoie un objet tondeuse à partir d'un string
 *
 * @author savane vamara
 * 
 */
@RequiredArgsConstructor
public class MowerParser implements Parser<String, Mower> {
  private static final String SEPARATOR = " ";
  private static final String PATTERN = "^\\d+ \\d+ [N|E|W|S]$";

  private final Lawn lawn;
  private final AtomicInteger counter = new AtomicInteger();

  @Override
  public Mower parse(final String source) {
    checkArgument(source.matches(PATTERN),
        "Erreur de parse des infos de la tondeuse [attendue: 'x y Orientaion'; actuelle: '"
            + source + "']");
    List<String> champs = Splitter.on(SEPARATOR).splitToList(source);
    int x = Integer.valueOf(champs.get(0)) - 1;
    int y = Integer.valueOf(champs.get(1)) - 1;
    Orientation orientation = Orientation.byCode(champs.get(2));
    return new Mower(counter.getAndIncrement(), lawn.cellAt(x, y), orientation);
  }
}
