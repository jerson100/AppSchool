package interfaces;

import java.util.List;

import exceptions.NotAll;
import models.Aul_Curso;

public interface IAul_Curso extends ICrud<Aul_Curso>{
	List<Aul_Curso> all(int idTipoperfil, int idTipoUsuario, int idSeccion) throws NotAll;
}
