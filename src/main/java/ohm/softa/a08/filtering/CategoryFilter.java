package ohm.softa.a08.filtering;

import ohm.softa.a08.model.Meal;

public class CategoryFilter extends FilterBase {
	private String keyToExclude;
	public CategoryFilter(String keyToExclude) {
		this.keyToExclude = keyToExclude;
	}

	@Override
	protected boolean exclude(Meal meal) {
		return meal.getCategory().contains(keyToExclude);
	}
}
