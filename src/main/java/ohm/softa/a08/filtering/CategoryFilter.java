package ohm.softa.a08.filtering;

import ohm.softa.a08.model.Meal;

import java.util.Arrays;

public class CategoryFilter extends FilterBase {
	private boolean include;
	private String[] categories;

	public CategoryFilter(boolean include, String... categories) {
		this.include = include;
		this.categories = categories;
	}

	@Override
	protected boolean include(Meal m) {
		// ok if any category matches
		if (include)
			return Arrays.stream(categories)
				.anyMatch(s -> s.equals(m.getCategory().toLowerCase()));
		else
			return Arrays.stream(categories)
				.noneMatch(s -> s.equals(m.getCategory().toLowerCase()));
	}
}
