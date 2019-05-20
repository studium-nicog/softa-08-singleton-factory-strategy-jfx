package ohm.softa.a08.filtering;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Peter Kurfer
 */
public abstract class MealFilterFactory {

	/* 'caching' all known filters */
	private static final Map<String, MealsFilter> filters = new HashMap<>();

	static {
		/* fill 'cache' in static constructor */
		filters.put("All", new NoFilter());
		filters.put("Vegetarian", new CategoryFilter(true,"vegetarisch", "vegan"));
		filters.put("No pork", new CategoryFilter(false, "schwein"));
		filters.put("No soy", new NotesFilter("soja"));
	}

	/* private constructor to ensure that singleton cannot be instantiated outside of the class
	 * in fact there's a possibility but it is really really dirty! */
	private MealFilterFactory() {
	}

	/**
	 * Get the matching strategy to the given keyword
	 * if strategy is unknown the factory will fallback to the NoFilter
	 *
	 * @param key key to resolve strategy
	 * @return MealsFilter instance corresponding to the given key or NoFilter as fallback
	 */
	public static MealsFilter getStrategy(String key) {
		return filters.getOrDefault(key, filters.get("All"));
	}

	/**
	 * Get the matching strategy to the given keyword
	 * if strategy is unknown the factory will fallback to the NoFilter
	 * Method does not cache strategy instances but creates a new instance per call
	 *
	 * @param key key to resolve strategy
	 * @return MealsFilter instance corresponding to the given key or NoFilter as fallback
	 */
	public static MealsFilter getStrategyThroughSwitch(String key) {
		switch (key) {
			case "Vegetarian":
				return new CategoryFilter(true, "vegetarisch", "vegan");
			case "No soy":
				return new CategoryFilter(false, "schwein");
			case "No pork":
				return new NotesFilter("soja");
			default:
				return new NoFilter();
		}
	}
}









