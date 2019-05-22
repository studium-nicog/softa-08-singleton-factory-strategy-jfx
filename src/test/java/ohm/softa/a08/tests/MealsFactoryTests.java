package ohm.softa.a08.tests;


import ohm.softa.a08.filtering.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Peter Kurfer
 */

class MealsFactoryTests {

	private static HashMap<String, Class> filterStragyKeyAndExpectedType;

	@BeforeAll
	static void setup() {
		filterStragyKeyAndExpectedType = new HashMap<>();
		filterStragyKeyAndExpectedType.put("Vegetarian", CategoryFilter.class);
		filterStragyKeyAndExpectedType.put("No soy", NotesFilter.class);
		filterStragyKeyAndExpectedType.put("No pork", CategoryFilter.class);
		filterStragyKeyAndExpectedType.put("All", NoFilter.class);
	}

	@Test
	void testGetVegetarianStrategy() {
		MealsFilter filter = MealFilterFactory.getStrategy("Vegetarian");
		assertTrue(filter instanceof CategoryFilter);
	}

	@Test
	void testGetNoSoyStrategy() {
		MealsFilter filter = MealFilterFactory.getStrategy("No soy");
		assertTrue(filter instanceof NotesFilter);
	}

	@Test
	void testGetNoPorkStrategy() {
		MealsFilter filter = MealFilterFactory.getStrategy("No pork");
		assertTrue(filter instanceof CategoryFilter);
	}

	@Test
	void testGetAllStrategy() {
		MealsFilter filter = MealFilterFactory.getStrategy("All");
		assertTrue(filter instanceof NoFilter);
	}

	@Test
	void testGetStrategyThroughSwitch() {
		filterStragyKeyAndExpectedType.forEach((strategy, expectedType) ->
			assertTrue(expectedType.isInstance(MealFilterFactory.getStrategyThroughSwitch(strategy)))
		);
	}
}
