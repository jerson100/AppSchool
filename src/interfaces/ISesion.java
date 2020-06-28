package interfaces;

import exceptions.NotFound;
import models.Sesion;

public interface ISesion extends ICrud<Sesion> {

	Sesion acceder(String usuario, String password) throws NotFound;
	
}
