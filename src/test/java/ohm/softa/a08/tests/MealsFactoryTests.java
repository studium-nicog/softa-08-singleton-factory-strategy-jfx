package de.thro.inf.prg3.a08.tests;

import de.thro.inf.prg3.a08.filtering.MealFilterFactory;
import de.thro.inf.prg3.a08.filtering.MealsFilter;
import de.thro.inf.prg3.a08.filtering.filters.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Peter Kurfer
 */

class MealsFactoryTests {

	@Test
	void testGetVegetarianStrategy() {
		MealsFilter filter = MealFilterFactory.getStrategy("Vegetarian");
		assertTrue(filter instanceof VegetarianStrategy);
	}

	@Test
	void testGetNoSoyStrategy() {
		MealsFilter filter = MealFilterFactory.getStrategy("No soy");
		assertTrue(filter instanceof NoSoyStrategy);
	}

	@Test
	void testGetNoPorkStrategy() {
		MealsFilter filter = MealFilterFactory.getStrategy("No pork");
		assertTrue(filter instanceof NoPorkStrategy);
	}

	@Test
	void testGetAllStrategy() {
		MealsFilter filter = MealFilterFactory.getStrategy("All");
		assertTrue(filter instanceof AllMealsStrategy);
	}
}
