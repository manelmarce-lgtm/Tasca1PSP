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
     * Validació i Codi de Sortida en el Fill:
     * Si FiltreLog rep un text buit o nul per System.in,
     * ha d'emetre un missatge d'error per System.err ("Error: Text buit")
     * i finalitzar immediatament amb System.exit(1). Si tot és correcte, finalitza
     * amb System.exit(0).
     * 
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String linia;
        String cercar = "ERROR";
        int contadorTotal = 0;
        boolean teContingut = false;

        while ((linia = reader.readLine()) != null) {
            teContingut = true;
            if (!linia.isEmpty()) {
                // Comptem les aparicions a la línia actual i les sumem al total
                int coincidenciesLinia = linia.split("(?i)" + cercar, -1).length - 1;
                contadorTotal += coincidenciesLinia;
            }
        }

        if (!teContingut) {
            System.err.println("Error: Text buit");
            System.exit(1);
        }

        // Escriure el resultat final a la sortida estàndard
        System.out.println(contadorTotal);
        System.exit(0);
    }
}
