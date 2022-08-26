/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;
import java.io.File;
import javax.swing.JOptionPane;
/**
 *
 * @author kingrishabdugar
 */
public class OpenPdf {
    public static void openById(String id){
        try{
//location:\\ + "Green-Leaf-Gourmet_Bill_" +id(file name of bill)
            if((new File("D:\\kingr\\Projects\\Generated_Bills\\"+"Green-Leaf-Gourmet_Bill_"+id+".pdf")).exists()){
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler D:\\kingr\\Projects\\Generated_Bills\\"+"Green-Leaf-Gourmet_Bill_"+id+".pdf" );
                
            }
            else
                JOptionPane.showMessageDialog(null, "File does not Exist");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
