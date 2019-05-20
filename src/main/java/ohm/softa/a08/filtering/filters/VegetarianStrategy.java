package ohm.softa.a08.filtering.filters;

import ohm.softa.a08.filtering.NotesFilterBase;

/**
 * @author Peter Kurfer
 */
public class VegetarianStrategy extends NotesFilterBase {

	public VegetarianStrategy() {
		super(new String[]{"fleisch", "schwein", "rind"});
	}
}
