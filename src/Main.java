/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import View.NonUserHome_V;
import View.Player_V;
import javax.swing.SwingUtilities;
/**
 *
 * @author Dell
 */
public class Main {
    public static void main(String[] ar)
    {
        SwingUtilities.invokeLater(() -> {
            //new Player_V();
             new NonUserHome_V();
        });
    }
}
