package com.wilkers1.sqlconnection.controller;

import java.sql.SQLException;

import com.wilkers1.sqlconnection.model.Model;
import com.wilkers1.sqlconnection.model.Pessoa;

class DAOPessoa extends JdbcDAO implements DAOPrepare {
	
	@Override
	public void insert(Model mdl) throws SQLException {
		if(!(mdl instanceof Pessoa)) { throw invalidModelExp; }
		Pessoa ppl = (Pessoa)mdl;
		if(ppl.getId()!=-1) { throw idExp; }
		String sql = pplInsert +String.format("('%s', '%s');",
				ppl.getNome(), ppl.getEmail());
		execute(pplInsert +sql);
	}
	
	@Override
	public void delete(Model mdl) throws SQLException {
		if(!(mdl instanceof Pessoa)) { throw invalidModelExp; }
		Pessoa ppl = (Pessoa)mdl;
		if(ppl.getId()==-1) { throw noIdExp; }
		String sql = String.format("(idPessoa = '%d') AND (pessoaNome = '%s') AND (pessoaEmail = '%s');",
				ppl.getId(), ppl.getNome(), ppl.getEmail());
		execute(pplDelete +sql);
	}
	
}