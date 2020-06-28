package interfaces;

import models.Glo_TipoArchivo;

public interface IGlo_TipoArchivo extends ICrud<Glo_TipoArchivo>{

	String validarExtension(Glo_TipoArchivo tipoArchivo);
	
}
