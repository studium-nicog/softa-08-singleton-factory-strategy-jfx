package ohm.softa.a08.filtering;

import ohm.softa.a08.filtering.filters.AllMealsStrategy;
import ohm.softa.a08.filtering.filters.NoPorkStrategy;
import ohm.softa.a08.filtering.filters.NoSoyStrategy;
import ohm.softa.a08.filtering.filters.VegetarianStrategy;

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
		filters.put("All", new AllMealsStrategy());
		filters.put("Vegetarian", new VegetarianStrategy());
		filters.put("No pork", new NoPorkStrategy());
		filters.put("No soy", new NoSoyStrategy());
	}

	/* private constructor to ensure that singleton cannot be instantiated outside of the class
	 * in fact there's a possibility but it is really really dirty! */
	private MealFilterFactory() {
	}

	/**
	 * Get the matching strategy to the given keyword
	 * if strategy is unknown the factory will fallback to the AllMealsStrategy
	 *
	 * @param key key to resolve strategy
	 * @return MealsFilter instance corresponding to the given key or AllMealsStrategy as fallback
	 */
	public static MealsFilter getStrategy(String key) {
		return filters.getOrDefault(key, filters.get("All"));
	}

	/**
	 * Get the matching strategy to the given keyword
	 * if strategy is unknown the factory will fallback to the AllMealsStrategy
	 * Method does not cache strategy instances but creates a new instance per call
	 *
	 * @param key key to resolve strategy
	 * @return MealsFilter instance corresponding to the given key or AllMealsStrategy as fallback
	 */
	public static MealsFilter getStrategyThroughSwitch(String key) {
		switch (key) {
			case "Vegetarian":
				return new VegetarianStrategy();
			case "No soy":
				return new NoSoyStrategy();
			case "No pork":
				return new NoPorkStrategy();
			default:
				return new AllMealsStrategy();
		}
	}
}









