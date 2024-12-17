package ejercicioServer.sokcetio.config;

public enum Events {

	ON_BUSCAR_TELEFONO ("onBuscarTelefono"),
	ON_ANNIADIR_ALUMNO ("onAnniadirAlumno"),
    ON_BORRAR_ALUMNO ("onBorrarAlumno"),
    ON_MODIFICAR_ALUMNO ("onModificarAlumno"),
	ON_RESPUESTA_SERVIDOR ("onRespuestaServidor"),
	ON_TELEFONO_ENCONTRADO("onTelefonoEncotrado");
	
	public final String value;

	private Events(String value) {
		this.value = value;
	}
}