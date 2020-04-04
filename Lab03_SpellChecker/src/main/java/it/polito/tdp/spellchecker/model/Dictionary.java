package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Contiene un dizionario di una lingua specifica
 * 
 * @author Rizzi
 *
 */
public class Dictionary {

	List<RichWord> paroleInserite = new ArrayList<>();
	List<String> dizionario = new ArrayList<>();
//	List<RichWord> paroleInserite = new LinkedList<>();
//	List<String> dizionario = new LinkedList<>();
	/**
	 * Aggiunge in {@code dizionario} una serie di parole prese da un file di testo
	 * 
	 * @param language indica la lingua del {@link Dictionary}
	 */
	public void loadDictionary(String language) {
		dizionario.clear();
		try {
			FileReader fr = new FileReader("src/main/resources/" + language + ".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				dizionario.add(word.toLowerCase());
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		}
	}

	/**
	 * Esegue il controllo ortografico delle parole
	 * 
	 * @param inputTextList {@link List} contenente le parole inserite di cui si
	 *                      vuol fare controllo ortografico
	 * @return Una {@link} List} di {@link RichWord} contenente le parole inserite e
	 *         l'indicazione della loro correttezza o meno
	 */
/*	public List<RichWord> spellCheckText(List<String> inputTextList) {
		paroleInserite.clear();
		for (String s : inputTextList) {
			if (dizionario.contains(s)) {
				paroleInserite.add(new RichWord(s, true));
			} else
				paroleInserite.add(new RichWord(s, false));
		}
		return paroleInserite;
	} */
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList) {
		paroleInserite.clear();
		for (String s : inputTextList) {
			if (dizionario.contains(s)) {
				paroleInserite.add(new RichWord(s, true));
			} else
				paroleInserite.add(new RichWord(s, false));
		}
		return paroleInserite;
	}
	
	public void spellCheckTextDichotomic(List<String> inputTextList) {
		paroleInserite.clear();
		for (String s : inputTextList) {
			if (this.ricercaDicotomica(s))
				paroleInserite.add(new RichWord(s, true));
			else
				paroleInserite.add(new RichWord(s, false));
		}
		/*	for (String s : inputTextList) {
			if (dizionario.contains(s)) {
				paroleInserite.add(new RichWord(s, true));
			} else
				paroleInserite.add(new RichWord(s, false));
		} */
	}

	public void setParoleInserite(List<RichWord> paroleInserite) {
		this.paroleInserite = paroleInserite;
	}
	
	private boolean ricercaDicotomica(String s) {
		int primo = 0;
		int ultimo = dizionario.size() - 1;
		boolean trovato = false;
		
		while (primo <= ultimo && !trovato) {
			Integer medio = (int) (primo + ultimo)/2;
			if (dizionario.get(medio).equals(s))
				trovato = true;
			else if (dizionario.get(medio).compareTo(s) > 0)
				ultimo = medio - 1;
			else
				primo = medio + 1;
		}
		return trovato;
	}
	
	public int numeroParoleSbagliate() {
		int conteggio = 0;
		for (RichWord rw : paroleInserite) {
			if (!rw.isCorretta()) {
				conteggio ++ ;
			}
		}
		return conteggio;
	}

	@Override
	public String toString() {
		String s = "";
		for (RichWord rw : paroleInserite) {
			if (!rw.isCorretta()) {
				if (s.equals(""))
					s += rw.getParola();
				else
					s += "\n" + rw.getParola();
			}
		}
		return s;
	}
	
	

}
