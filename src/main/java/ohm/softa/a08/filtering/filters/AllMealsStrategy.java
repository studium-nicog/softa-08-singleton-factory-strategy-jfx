package ohm.softa.a08.filtering.filters;

import ohm.softa.a08.filtering.MealsFilter;
import ohm.softa.a08.model.Meal;

import java.util.List;

/**
 * @author Peter Kurfer
 */

public class AllMealsStrategy implements MealsFilter {
	@Override
	public List<Meal> filter(List<Meal> meals) {
		/* don't filter anything */
		return meals;
	}
}
