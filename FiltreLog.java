import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class FiltreLog {
    /*
     * Rep un text línia per línia a través de la seva entrada
     * estàndard (System.in).
     * Per defecte, compta quantes vegades apareix la paraula clau ERROR.
     * Envia el resultat numèric final com una única línia a la seva sortida
     * estàndard (System.out).
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String linia;
        String cercar = "ERROR";
        int contadorTotal = 0; 

        while ((linia = reader.readLine()) != null) {
            // Comptem les aparicions a la línia actual i les sumem al total
            int coincidenciesLinia = linia.split("(?i)" + cercar, -1).length - 1;
            contadorTotal += coincidenciesLinia;
        }

        // Escriure el resultat final a la sortida estàndard
        System.out.println(contadorTotal);
    }
}
