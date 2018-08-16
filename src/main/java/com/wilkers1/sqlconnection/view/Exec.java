package com.wilkers1.sqlconnection.view;

import com.wilkers1.sqlconnection.model.Model;
import com.wilkers1.sqlconnection.model.Pessoa;

public class Exec {

	public static void main(String[] args) {
		try {
			Pessoa ppl = new Pessoa("Sal Mão", "sal@mao.com");
			Model.insert(ppl);
			System.out.println("done!");
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

}


