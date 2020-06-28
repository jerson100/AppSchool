package interfaces;

import java.util.List;

import exceptions.NotAll;
import models.Aul_Trabajo;

public interface IAul_Trabajo extends ICrud<Aul_Trabajo> {
	List<Aul_Trabajo> all(int idSecCur,int idCuenta) throws NotAll;
}
