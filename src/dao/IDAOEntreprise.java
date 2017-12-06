package dao;

import domain.Entreprise;

public interface IDAOEntreprise extends IDAO<Entreprise> {

	long addId(Entreprise c);

	int getNumSiretEntreprise(long id);

}
