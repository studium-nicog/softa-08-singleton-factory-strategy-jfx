package ohm.softa.a08.filtering.filters;


import ohm.softa.a08.filtering.NotesFilterBase;

/**
 * @author Peter Kurfer
 */
public class NoPorkStrategy extends NotesFilterBase {
	public NoPorkStrategy(){
		super(new String[]{"schwein"});
	}
}
