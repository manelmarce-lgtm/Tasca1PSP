import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FiltreLog {

    public static void main(String[] args) {
        String cercar = "ERROR";

        // Si es passa un argument per línia de comandes, s'utilitza com a paraula clau
        if (args.length > 0) {
            cercar = args[0];
        }

        int contador = 0;
        boolean teContingut = false;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String linia;

            while ((linia = reader.readLine()) != null) {
                teContingut = true;

                // Comptar quantes vegades apareix la paraula clau exacta a la línia
                int index = 0;
                while ((index = linia.indexOf(cercar, index)) != -1) {
                    contador++;
                    index += cercar.length();
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }

        // Si System.in no ha passat cap línia (text buit o nul)
        if (!teContingut) {
            System.err.println("Error: Text buit");
            System.exit(1);
        }

        // Mostrar el resultat final i sortir amb codi 0
        System.out.println(contador);
        System.exit(0);
    }
}