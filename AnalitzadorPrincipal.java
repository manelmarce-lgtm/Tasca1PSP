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
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AnalitzadorPrincipal {
    public static void main(String[] args) throws IOException {

        File fitxerErrors = new File("errors_filtre.log");
        ProcessBuilder pb = new ProcessBuilder("java", "FiltreLog", "ERROR");
        ProcessBuilder pb2 = new ProcessBuilder("java", "FiltreLog", "WARNING");

        pb.redirectError(fitxerErrors);
        pb2.redirectError(fitxerErrors);

        try {
            Process p = pb.start();
            Process p2 = pb2.start();
            String textProva = " [ERROR], hi ha hagut un [ERROR] a la linea 54\n"
                    + "[WARNING] S'esta realentizant el procés.";
            try (BufferedWriter outProces1 = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {
                outProces1.write(textProva);
                outProces1.flush();
            }

            try (BufferedWriter outProces2 = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {
                outProces2.write(textProva);
                outProces2.flush();
            }

            String resultatp1 = null;
            try (BufferedReader inpProces1 = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                resultatp1 = inpProces1.readLine();
            }
            String resultatp2 = null;
            try (BufferedReader inpProces2 = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                resultatp2 = inpProces2.readLine();
            }
            int sortidaP1 = p.waitFor();
            int sortidaP2 = p2.waitFor();

            int codiSortida = (sortidaP1 == 0 && sortidaP2 == 0) ? 0 : 1;
            int contadorErrors = (resultatp1 != null && !resultatp1.isEmpty()) ? Integer.parseInt(resultatp1.trim())
                    : 0;
            int contadorWarnings = (resultatp2 != null && !resultatp2.isEmpty()) ? Integer.parseInt(resultatp2.trim())
                    : 0;
            System.out.println("RESULTAT: " + "ERRORS=" + contadorErrors + " | WARNINGS=" + contadorWarnings
                    + " | EXIT_CODE=" + codiSortida);
        } catch (IOException e) {
            System.err.println("Error E/S: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Procés interromput: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
