package io.tondeuse.mowitnow.unit;

import io.tondeuse.mowitnow.base.Orientation;
import io.tondeuse.mowitnow.mower.Instruction;

/**
 * Grouper les enums en constante pour alleger le code
 *
 * @author savane vamara
 * 
 */
interface Constant {

	// Orientations
	static final Orientation EAST = Orientation.EAST;
	static final Orientation NORTH = Orientation.NORTH;
	static final Orientation WEST = Orientation.WEST;
	static final Orientation SOUTH = Orientation.SOUTH;

	// Instructions
	static final Instruction D = Instruction.ROTATE_RIGHT;
	static final Instruction G = Instruction.ROTATE_LEFT;
	static final Instruction A = Instruction.FORWARD;
}
