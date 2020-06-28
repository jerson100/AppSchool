package utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class JeVlidate {

	public static void main(String[] args) {
		System.out.println(validarLetras(" "));
		System.out.println(validarUrl("HTTPS:\\google.com.pe")); 
		System.out.println(obtenerExtension("peligro."));
		for (int i = 0; i < 50; i++) {
			System.out.println(generarNumeroAleatorio(2, 5));
		}
		String path = "2020/PROFESORES/4b2d8eae9fe469fcd92b14b348ec86b9.pdf";
		int index = path.indexOf("/");
		String container = path.substring(0, index);
		System.out.println(container);
		System.out.println(path.substring(index + 1));
		
		System.out.println("2020/PROFESORES/4b2d8eae9fe469fcd92b14b348ec86b9.pdf".replaceAll("2020/", ""));
		
	}
	
	public static boolean validarNumeroPositivo(String number) {
		return number.matches("\\d+");
	}
	
	public static boolean validarLetras(String txt) {
		Pattern patern = Pattern.compile("^[a-z]+$",Pattern.CASE_INSENSITIVE);
		Matcher m = patern.matcher(txt);
		return m.matches();
	}
	
	public static boolean validarUrl(String url) {
		Pattern patern = Pattern.compile("^(?:https|http|ftp):(?:\\\\|//).+$",Pattern.CASE_INSENSITIVE);
		Matcher m = patern.matcher(url);
		return m.matches();
	}
	
	public static String obtenerExtension(String fileName) {
		int index = fileName.lastIndexOf(".");
		return index == -1 ? "" : fileName.substring(index);
	}
	
	public static int generarNumeroAleatorio(int inicio, int fin) {
		return (int)(Math.random()*(fin - inicio + 1)) + inicio;
	}

	
}
