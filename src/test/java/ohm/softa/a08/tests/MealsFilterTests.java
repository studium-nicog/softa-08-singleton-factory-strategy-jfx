package ohm.softa.a08.tests;


import ohm.softa.a08.filtering.MealFilterFactory;
import ohm.softa.a08.filtering.MealsFilter;
import ohm.softa.a08.model.Meal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Peter Kurfer
 */

class MealsFilterTests {

	private List<Meal> meals;

	@BeforeEach
	void setup() {
		meals = new LinkedList<>();
		meals.add(new Meal(1, "Pork", "schwein", "Schweinefleisch"));
		meals.add(new Meal(2, "Soy", "", "Soja"));
		meals.add(new Meal(3, "Beef", "", "Rindergulasch"));
	}

	@Test
	void testAllMealStrategy() {
		MealsFilter allMealsFilter = MealFilterFactory.getStrategy("All");
		List<Meal> filtered = allMealsFilter.filter(meals);

		assertNotNull(filtered);
		assertEquals(3, filtered.size());
	}

	@Test
	void testNoPorkStrategy() {
		MealsFilter noPorkFilter = MealFilterFactory.getStrategy("No pork");
		List<Meal> filtered = noPorkFilter.filter(meals);

		assertNotNull(filtered);
		assertEquals(2, filtered.size());
	}

	@Test
	void testNoSoyStrategy() {
		MealsFilter noSoyFilter = MealFilterFactory.getStrategy("No soy");
		List<Meal> filtered = noSoyFilter.filter(meals);

		assertNotNull(filtered);
		assertEquals(2, filtered.size());
	}

	@Test
	void testVegetarianStrategy() {
		MealsFilter vegetarianFilter = MealFilterFactory.getStrategy("Vegetarian");
		List<Meal> filtered = vegetarianFilter.filter(meals);

		assertNotNull(filtered);
		assertEquals(0, filtered.size());
	}
}
