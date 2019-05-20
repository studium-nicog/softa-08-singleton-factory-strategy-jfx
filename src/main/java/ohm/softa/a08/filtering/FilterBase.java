package ohm.softa.a08.filtering;

import ohm.softa.a08.model.Meal;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Base class for all filter implementations
 *
 * @author Peter Kurfer
 */
public abstract class FilterBase implements MealsFilter {

	@Override
	public List<Meal> filter(List<Meal> meals) {
		return meals.stream()
			.filter(this::include)
			.collect(Collectors.toList());
	}

	/**
	 * Determine if a given meal should be included or excluded
	 *
	 * @param m meal to investigate
	 * @return boolean result, true means meal will be included
	 */
	protected abstract boolean include(Meal m);
}
