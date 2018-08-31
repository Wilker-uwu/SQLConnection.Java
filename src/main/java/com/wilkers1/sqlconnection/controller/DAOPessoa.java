package com.wilkers1.sqlconnection.controller;

import java.sql.SQLException;

import com.wilkers1.sqlconnection.model.Pessoa;

class DAOPessoa extends JdbcDAO implements DAOPrepare {
	
	@Override
	public void insert(Model mdl) throws SQLException {
		if(!(mdl instanceof Pessoa)) { throw invalidModelExp; }
		Pessoa ppl = (Pessoa)mdl;
		if(ppl.getId()!=-1) { throw idExp; }
		execute(pplInsert +ppl.toString());
	}
	
	@Override
	public void delete(Model mdl) throws SQLException, NullPointerException, IllegalAccessException {
		if(!(mdl instanceof Pessoa)) { throw invalidModelExp; }
		Pessoa ppl = (Pessoa)mdl;
		if(ppl.getId()==-1) { throw noIdExp; }
		execute(pplDelete +ppl.toString(true));
	}
	
}