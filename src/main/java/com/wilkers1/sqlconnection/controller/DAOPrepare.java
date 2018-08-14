package com.wilkers1.sqlconnection.controller;

interface DAOPrepare {
	static String pplInsert = "INSERT INTO tbpessoa(pessoaNome, pessoaEmail) VALUES ";
	static String pplDelete = "DELETE FROM tbpessoa WHERE ";
	static String tarInsert = "INSERT INTO tbtarefa (`idTarefa`, `tarefaTitulo`, `tarefaDesc`, `tarefaPrazo`, `tarefaPrazoInicio`, `tarefaPrazoTermino`, `idMetodo`) VALUES ";
	static String tarDelete = "DELETE FROM tbtarefa WHERE ";
}
