package ohm.softa.a08.filtering;

import ohm.softa.a08.model.Meal;

public class NoteFilter extends FilterBase {
	private String keyToExclude;

	public NoteFilter(String keyToExclude) {
		this.keyToExclude = keyToExclude;
	}

	@Override
	protected boolean exclude(Meal meal) {
		return meal.getNotes().contains(keyToExclude);
	}
}
