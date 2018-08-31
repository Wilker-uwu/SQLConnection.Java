package com.wilkers1.sqlconnection.controller;

import java.sql.SQLException;

import com.wilkers1.sqlconnection.model.RefParticipante;

class DAORefPar extends JdbcDAO implements DAOPrepare{
	
	@Override
	public void insert(Model mdl) throws SQLException {
		if(!(mdl instanceof RefParticipante)) { throw invalidModelExp; }
		RefParticipante ref = (RefParticipante)mdl;
		if(ref.getPessoa()<1 || ref.getTarefa()<1) { throw idExp; }
		execute(refInsert +ref.toString());
	}
	
	@Override
	public void delete(Model mdl) throws SQLException, NullPointerException, IllegalAccessException {
		if(!(mdl instanceof RefParticipante)) { throw invalidModelExp; }
		RefParticipante ref = (RefParticipante)mdl;
		if(ref.getPessoa()==-1 || ref.getTarefa()==-1) { throw noIdExp; }
		execute(refDelete +ref.toString(true));
	}
	
}