package io.tondeuse.mowitnow.functional;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import io.tondeuse.mowitnow.base.Orientation;
import io.tondeuse.mowitnow.io.parseur.InstructionParser;
import io.tondeuse.mowitnow.mower.Lawn;
import io.tondeuse.mowitnow.mower.Mower;
import static io.tondeuse.mowitnow.base.Position.at;

import static org.junit.Assert.assertEquals;

public class MowerControlSteps {

	private Lawn lawn;
	
	private Mower mower;
	
	@Given("une pelouse de $largeur sur $longueur")
	public void lawn(@Named("largeur") final int width, @Named("longueur") final int height) {
		lawn = new Lawn(width, height);
	}

	@Given("une tondeuse coordonnee au ($x1, $y1) et orientee vers $o1")
	public void mower(@Named("x1") final int x, @Named("y1") final int y, @Named("o1") final Orientation orientation) {
		mower = new Mower(lawn.cellAt(at(x, y)), orientation);
	}
	
	@When("la tondeuse execute les instructions suivantes : $instructions")
	public void execute(@Named("instructions")final String instructions) {
		mower.setInstructions(new InstructionParser().parse(instructions));
		mower.start();
	}
	
	@Then("la tondeuse doit etre postionnee au ($x2, $y2) et orientee vers $o2")
	public void verify(@Named("x2") final int x,@Named("y2") final int y,@Named("o2") final Orientation orientation) {
		assertEquals(x, mower.position().getX());
		assertEquals(y, mower.position().getY());
		assertEquals(orientation, mower.getOrientation());
	}
}
