package FinalProject.View;

import FinalProject.Model.Data.Material;

import java.util.List;

public class ClientView {
    public void greet(String username) {
        String menu = """
                [1] Find Material
                [2] Add Material
                [3] Show Material
                [x] Exit
                """;
        System.out.println("Welcome to StudyVault user "+username);
        System.out.println(menu);
    }

    public void incorrectInput() {
        System.out.println("Enter a valid choice");
    }

    public void askForTitle() {
        System.out.println("""
                Find your needed material by giving the exact name of the material
                example:
                    Intro to Computer Programming
                """);
        System.out.println("Enter title");
    }

    public void showFoundMats(List<Material> materials) {
        for (int i = 1; i <= materials.size(); i++) {
            System.out.println(materials.get(i-1).getClass());
            String item = String.format("[%d] %s\n",i,materials.get(i-1));
            System.out.println(item);
        }
    }

    public void addMatStart() {
        System.out.println("To add new materials you just simply have to fill out the \nnecessary information of the materials you wish to upload");
    }

    public void matType() {
        String types = """
                [1] Book
                [2] Paper
                [3] Video
                [x] cancel""";
        System.out.println(types);
    }

    public void Label(String s) {
        System.out.print(s);
    }
}
