-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 10-Ago-2018 às 01:48
-- Versão do servidor: 5.7.17
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dtbsql`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbmetodologias`
--

CREATE TABLE `tbmetodologias` (
  `idMetodo` int(11) NOT NULL,
  `metodoNome` varchar(16) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbmetodologias`
--

INSERT INTO `tbmetodologias` (`idMetodo`, `metodoNome`) VALUES
(1, 'comodoro'),
(2, 'scram'),
(3, 'someOtherMethod'),
(4, 'idkWhatAreThose'),
(5, 'eeeeeeee');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbpessoa`
--

CREATE TABLE `tbpessoa` (
  `idPessoa` int(11) NOT NULL,
  `pessoaNome` varchar(32) NOT NULL,
  `pessoaEmail` varchar(48) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbpessoa`
--

INSERT INTO `tbpessoa` (`idPessoa`, `pessoaNome`, `pessoaEmail`) VALUES
(1, 'Frisk', 'person@somewhere'),
(2, 'Fool', 'aaa@at'),
(3, 'Bar', 'noOne@nowhere');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbrefparticipante`
--

CREATE TABLE `tbrefparticipante` (
  `idParticipante` int(11) NOT NULL,
  `idTarefa` int(11) NOT NULL,
  `idPessoa` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbrefparticipante`
--

INSERT INTO `tbrefparticipante` (`idParticipante`, `idTarefa`, `idPessoa`) VALUES
(1, 0, 0),
(2, 1, 1),
(3, 1, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbtarefa`
--

CREATE TABLE `tbtarefa` (
  `idTarefa` int(11) NOT NULL,
  `tarefaTitulo` varchar(32) NOT NULL,
  `tarefaDesc` varchar(256) DEFAULT NULL,
  `tarefaPrazo` date NOT NULL,
  `tarefaPrazoInicio` date DEFAULT NULL,
  `tarefaPrazoTermino` date DEFAULT NULL,
  `idMetodo` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tbtarefa`
--

INSERT INTO `tbtarefa` (`idTarefa`, `tarefaTitulo`, `tarefaDesc`, `tarefaPrazo`, `tarefaPrazoInicio`, `tarefaPrazoTermino`, `idMetodo`) VALUES
(1, 'Tarefa sobre \'tal\'', 'Esta tarefa se refere a \'tal\' coisa pra ser feita assim.', '2018-08-07', NULL, NULL, NULL),
(2, 'Esta outra coisa', 'Isso aqui é isso.', '2018-03-31', NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbmetodologias`
--
ALTER TABLE `tbmetodologias`
  ADD PRIMARY KEY (`idMetodo`);

--
-- Indexes for table `tbpessoa`
--
ALTER TABLE `tbpessoa`
  ADD PRIMARY KEY (`idPessoa`),
  ADD UNIQUE KEY `pessoaEmail` (`pessoaEmail`),
  ADD KEY `pessoaNome` (`pessoaNome`);

--
-- Indexes for table `tbrefparticipante`
--
ALTER TABLE `tbrefparticipante`
  ADD PRIMARY KEY (`idParticipante`),
  ADD KEY `idTarefa` (`idTarefa`),
  ADD KEY `idPessoa` (`idPessoa`);

--
-- Indexes for table `tbtarefa`
--
ALTER TABLE `tbtarefa`
  ADD PRIMARY KEY (`idTarefa`),
  ADD UNIQUE KEY `tarefaTitulo` (`tarefaTitulo`),
  ADD KEY `idMetodo` (`idMetodo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbmetodologias`
--
ALTER TABLE `tbmetodologias`
  MODIFY `idMetodo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `tbpessoa`
--
ALTER TABLE `tbpessoa`
  MODIFY `idPessoa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `tbrefparticipante`
--
ALTER TABLE `tbrefparticipante`
  MODIFY `idParticipante` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `tbtarefa`
--
ALTER TABLE `tbtarefa`
  MODIFY `idTarefa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
