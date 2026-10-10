/*
Instancia el procés fill executant la classe FiltreLog mitjançant ProcessBuilder.
Envia un text de prova de múltiples línies al fill a través del seu OutputStream.
Executa out.flush() i out.close() per indicar la fi de les dades.
Llegeix la resposta del fill pel seu InputStream, la mostra per pantalla i espera la finalització amb p.waitFor().
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class AnalitzadorPrincipal {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("java", "FiltreLog");
        Process p = pb.start();

        OutputStream out = p.getOutputStream();
        String textProva = " Error, hi ha hagut un error a la linea 54 ";
        out.write(textProva.getBytes());
        out.flush();
        out.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String resultat = in.readLine();
        in.close();
        
        System.out.println("El fill ha comptat: " + resultat + " errors.");
        p.waitFor();

    }

}
