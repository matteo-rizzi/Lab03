package it.polito.tdp.spellchecker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Contiene informazioni su correttezza o meno delle parole
 * 
 * @author Rizzi
 *
 */
public class RichWord {

	String parola;
	boolean corretta;

	/**
	 * Costruisce una nuova RichWord
	 * 
	 * @param parola   parola di cui si vuol sapere la correttezza
	 * @param corretta indica se la parola è corretta o meno
	 */
	public RichWord(String parola, boolean corretta) {
		super();
		this.parola = parola;
		this.corretta = corretta;
	}

	public String getParola() {
		return parola;
	}

	public boolean isCorretta() {
		return corretta;
	}

}
