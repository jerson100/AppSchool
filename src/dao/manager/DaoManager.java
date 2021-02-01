package dao.manager;

import dao.Aul_CicloDao;
import dao.Aul_ClasesDao;
import dao.Aul_ContenidoDao;
import dao.Aul_CursoDao;
import dao.Aul_HorarioDao;
import dao.Aul_PeriodoNotasDao;
import dao.Aul_ProfesorMensajeDao;
import dao.Aul_RegistroNotasDao;
import dao.Aul_SeccionAlumnoDao;
import dao.Aul_SeccionDao;
import dao.Aul_SeccionGradoNivelDao;
import dao.Aul_TrabajoDao;
import dao.Aul_TrabajosAlumnoDao;
import dao.Glo_TipoArchivoDao;
import dao.Glo_TipoSexoDao;
import dao.SesionDao;
import enumerados.EDaoManager;
import interfaces.ICrud;
import models.Glo_TipoArchivo;

public class DaoManager {

	@SuppressWarnings("rawtypes")
	public static ICrud getDaoManager(EDaoManager manager) {
		
		switch (manager) {
		
			case SESIONDAO:
				
				return new SesionDao();
				
			case AULCURSO:
				
				return new Aul_CursoDao();
				
			case AULCONTENIDO:
				
				return new Aul_ContenidoDao();
				
			case AULSECCION:
				
				return new Aul_SeccionDao();
				
			case GLOTIPOSEXO:
				
				return new Glo_TipoSexoDao();
				
			case AULHORARIO:
				
				return new Aul_HorarioDao();
				
			case AULSECCIONGRADONIVEL:
				
				return new Aul_SeccionGradoNivelDao();
				
			case AULCLASES:
				
				return new Aul_ClasesDao();
				
			case AULTRABAJO:
				
				return new Aul_TrabajoDao();
				
			case GLOTIPOARCHIVO:
				
				return new Glo_TipoArchivoDao();
				
			case AULTRABAJOSALUMNO:
				
				return new Aul_TrabajosAlumnoDao();
				
			case AULCICLO:
				
				return new Aul_CicloDao();
				
			case AULREGISTRONOTAS:
				
				return new Aul_RegistroNotasDao();
				
			case AULPERIODONOTAS:
				
				return new Aul_PeriodoNotasDao();
				
			case AULSECCIONALUMNO:
				
				return new Aul_SeccionAlumnoDao();
				
			case AULPROFESORMENSAJE:
				
				return new Aul_ProfesorMensajeDao();
				
			default:
				
		}
		
		return null;
		
	}
	
}
