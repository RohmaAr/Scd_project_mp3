/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Dell
 */
public class Mode_M {
    static boolean  mode=true;
    private Mode_M(){}
    public static boolean getMode()
    {
        return mode;
    }
    public static void modeChange()
    {
        mode=!mode;
    }
}
