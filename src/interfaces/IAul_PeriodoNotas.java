package interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import exceptions.NotAll;
import models.Aul_PeriodoNotas;

public interface IAul_PeriodoNotas extends ICrud<Aul_PeriodoNotas>{

	Map<String, List<Aul_PeriodoNotas>> all(int idCiclo, int idSecCur) throws NotAll, SQLException;

}
