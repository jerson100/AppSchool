package interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import exceptions.NotAll;
import exceptions.NotCreated;
import models.Aul_RegistroNotas;

public interface IAul_RegistrarNotas extends ICrud<Aul_RegistroNotas>{

	Map<String, List<Aul_RegistroNotas>> all(int idCiclo, int SecCurPro) throws NotAll, SQLException;

	List<Aul_RegistroNotas> allList(int idCiclo, int SecCurPro) throws NotAll, SQLException;

	void create(Aul_RegistroNotas[] notas, int idUsuario) throws NotCreated, SQLException;

}
