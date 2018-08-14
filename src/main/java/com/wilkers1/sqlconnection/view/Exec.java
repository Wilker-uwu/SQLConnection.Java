package com.wilkers1.sqlconnection.view;

import com.wilkers1.sqlconnection.model.Pessoa;

public class Exec {

	public static void main(String[] args) {
		try {
			Pessoa aln = new Pessoa("Sal M�o", "sal@mao.com");
			aln.getId();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

}
