package com.wilkers1.sqlconnection.view;

import com.wilkers1.sqlconnection.model.Pessoa;

public class Exec {

	public static void main(String[] args) {
		try {
			Pessoa aln = new Pessoa("Sal Mão", "sal@mao.com");
			
			System.out.println("done!");
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

}
