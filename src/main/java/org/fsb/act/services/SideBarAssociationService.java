package org.fsb.act.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.fsb.act.dao.SideBarAssociationDao;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.LogoTitre;

public class SideBarAssociationService {
	
	/**
	 * get association et titre asscoiation from dao
	 * update imageView= logo and label= titre 
	 * @param imageView
	 * @param label
	 */
	public static void getLogoTitre(ImageView imageView, Label label) {
		LogoTitre logoTitre= SideBarAssociationDao.getLogoTitre();
		if(logoTitre.getTitre() != null) 
			label.setText(logoTitre.getTitre());
		if(logoTitre.getLogo() != null) {
			try {
				//create file if not exist
				FileOutputStream fos = new FileOutputStream(".//src//main//resources//org//fsb//act//images//logo.jpg");
				//write bytes in the file
				fos.write(logoTitre.getLogo());
				
				//update image view
				Image image = new Image("file:.//src//main//resources//org//fsb//act//images//logo.jpg", imageView.getFitWidth(), imageView.getFitHeight(), true, true);
				imageView.setImage(image);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
