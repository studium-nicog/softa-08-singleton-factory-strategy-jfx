package ohm.softa.a08.filtering;

public abstract class MealsFilterFactory {
	private MealsFilterFactory() { }

	public static MealsFilter getStrategy(String key) {
		System.out.println("Getting filter for key " + key);

		if (key == null) {
			return new NoFilter();
		}

		switch (key) {
			case "All":
				return new NoFilter();
			case "No pork":
				return new CategoryFilter("pork");
			case "No soy":
				return new NoteFilter("soy");
			case "Vegetarian":
				return new CategoryFilter("fish");
			default:
				throw new IllegalStateException("Unknown strategy: " + key);
		}
	}
}
