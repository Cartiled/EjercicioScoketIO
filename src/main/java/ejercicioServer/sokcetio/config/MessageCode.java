package ejercicioServer.sokcetio.config;

public enum MessageCode {

	ON_TELEFONO_NO_ENCONTRADO ("100"),
    ON_ALUMNO_EXISTE ("300"),
    ON_ALUMNO_NO_EXISTE ("400"),
	ERROR_DE_EXECPTION("500");
	
	public final String value;

	private MessageCode(String value) {
		this.value = value;
	}
}
