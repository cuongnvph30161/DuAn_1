/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author ADMIN
 */
public class XImages {
    public  static Image getIconApp(){
        URL url = XImages.class.getResource("/Img/toTo.png");
        return new ImageIcon(url).getImage();
    }           
}
