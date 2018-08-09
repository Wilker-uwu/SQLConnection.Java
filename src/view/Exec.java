package view;

import model.Pessoa;

public class Exec {

	public static void main(String[] args) {
		try {
			Pessoa aln = new Pessoa("Sal Mão", "sal@mao.com");
			aln.getId();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

}
