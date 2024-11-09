package FinalProject.Controller;


import FinalProject.Model.Client;
import FinalProject.Model.Data.Material;
import FinalProject.View.ClientView;

import java.util.List;
import java.util.Scanner;

public class ClientController {
    private Client client;
    private ClientView view;

    public ClientController(Client client, ClientView view) {
        this.client = client;
        this.view = view;
    }

    public void run(Scanner in) {
        Boolean isRunning = true;
        while (isRunning) {
            view.greet(client.getUsername());
            char choice = in.next().charAt(0);
            in.nextLine();
            isRunning = validateChoice(choice, in);
        }
    }

    // Private methods -------
    private Boolean validateChoice(char choice, Scanner in) {
        switch (choice) {
            case '1':
                findMat(in);
                break;
            case '2':
                break ;
            case 'x':
                return false;
            default:
                view.incorrectInput();
                break;
        }
        return true;
    }

    private void findMat(Scanner in) {
        view.askForTitle();
        String title = in.nextLine();
        List<Material> materials = client.findMaterials(title);
        view.showFoundMats(materials);
    }

}
