package ohm.softa.a08.filtering;

import ohm.softa.a08.filtering.MealsFilter;
import ohm.softa.a08.model.Meal;

import java.util.List;

/**
 * @author Peter Kurfer
 */

public class NoFilter implements MealsFilter {
	@Override
	public List<Meal> filter(List<Meal> meals) {
		/* don't filter anything */
		return meals;
	}
}
