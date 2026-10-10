/*
Instancia el procés fill executant la classe FiltreLog mitjançant ProcessBuilder.
Envia un text de prova de múltiples línies al fill a través del seu OutputStream.
Executa out.flush() i out.close() per indicar la fi de les dades.
Llegeix la resposta del fill pel seu InputStream, la mostra per pantalla 
i espera la finalització amb p.waitFor().
Configuració del Pare:
Redirigeix la sortida d'errors del fill (stderr) cap a un fitxer físic anomenat errors_filtre.log utilitzant pb.redirectError(File).
Captura el codi de finalització del fill amb p.exitValue().
Imprimeix per pantalla l'estat final de l'execució segons el codi retornat.

*/

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class AnalitzadorPrincipal {
    public static void main(String[] args) throws IOException {
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "FiltreLog");
            File fitxerErrors = new File("errors_filtre.log");
            pb.redirectError(fitxerErrors);
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

            int codiSortida = p.exitValue();
            if (codiSortida == 0) {
                System.out.println("El procés fill ha finalitzat correctament");
            } else{
                System.err.print("El proces fill ha finalitzat amb errors: " + codiSortida
                        + "Mira el fitxer errors_filtre.log pe més informacio");
            }
        } catch (IOException e) {
            System.err.println("Error d' in/out: " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
