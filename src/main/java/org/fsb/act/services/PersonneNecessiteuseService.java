package org.fsb.act.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fsb.act.dao.DaoMembre;
import org.fsb.act.dao.PersonneNecessiteuseDao;
import org.fsb.act.entities.Membre;
import org.fsb.act.entities.PersonneNecessiteuse;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonneNecessiteuseService {

	public static List<PersonneNecessiteuse> getData(){
		return PersonneNecessiteuseDao.getData();
	}
	
	public static PersonneNecessiteuse getById(long id){
		return PersonneNecessiteuseDao.getById(id);
	}
	
	public static int ajouterPersonneN(PersonneNecessiteuse personne) {
		
		return PersonneNecessiteuseDao.add(personne);
	}

	public static int supprimerPersonneN(long id) {
		
		return PersonneNecessiteuseDao.delete(id);
	}

	public static int modifierPersonneN(PersonneNecessiteuse personne) {
		
		return PersonneNecessiteuseDao.update(personne);
	}
	
	public static ObservableList<PersonneNecessiteuse> searchPersonneN(ObservableList<PersonneNecessiteuse> data, String text) {
		ObservableList<PersonneNecessiteuse>  localData= FXCollections.observableArrayList();
		for(PersonneNecessiteuse personne: data) {
			if(personne.getPrenom().matches(text+".*") ||
		       personne.getNom().matches(text+".*") ||
		       (personne.getPrenom()+" "+personne.getNom()).matches(text+".*")||
		       personne.getPrenomPere().matches(text+".*") ||
		       personne.getPrenomGPere().matches(text+".*") ||
		       personne.getNomMere().matches(text+".*") ||
		       personne.getPrenomMere().matches(text+".*") ||
		       (personne.getPrenomMere()+" "+personne.getNomMere()).matches(text+".*")||		       
		       personne.getDateN().matches(".*"+text+".*")||
		       personne.getProfession().matches(text+".*") ||
		       personne.getEmail().matches(text+".*") ||
		       personne.getTelephone().matches(text+".*") ||		       
		       (""+personne.getIdentite()).matches(text+".*") ||
		       (""+personne.getCodePostal()).matches(text+".*") ||
		       (""+personne.getId()).matches(text+".*") ||
		       personne.getEtatCivil().matches(text+".*") ||
		       personne.getLieuN().matches(text+".*") ||
		       personne.getGouvernorat().matches(text+".*") 		       		       
		      //|| membre.getDirigeant().matches(text+".*")
		       ) {
				
				localData.add(personne);
			}
		}
		return localData;
	}
	
	
	public static int mappingCsvToPersonneN(String absolutePath) {
		
		int nbErreur=0;
		// Hashmap to map CSV data to personneN data
        Map<String, String> mapping= new HashMap<>();
        mapping.put("prenom","prenom");
        mapping.put("nom","nom");
        mapping.put("prenom Pere","prenomPere");
        mapping.put("prenom Grand Pere","prenomGPere");
        mapping.put("prenom Mere","prenomMere");
        mapping.put("nom Mere","nomMere");
        mapping.put("date de naissance","dateN");
        mapping.put("lieu de naissance","lieuN");
        mapping.put("nationalite","nationalite");
        mapping.put("gouvernorat","gouvernorat");
        mapping.put("profession","profession");
        mapping.put("etat civil","etatCivil");        
        mapping.put("type piece identite","identite");
        mapping.put("numero piece identite","typeIdentite");
        mapping.put("adresse email","email"); 
        mapping.put("numero telephone","telephone");
        mapping.put("adresse","adresse");
        mapping.put("code postal","codePostal");
        mapping.put("dirigeant"," dirigeant");
        
        // HeaderColumnNameTranslateMappingStrategy
        // for PersonneNecessiteuse class
        HeaderColumnNameTranslateMappingStrategy<PersonneNecessiteuse> strategy =
             new HeaderColumnNameTranslateMappingStrategy<PersonneNecessiteuse>();
        strategy.setType(PersonneNecessiteuse.class);
        strategy.setColumnMapping(mapping);
        
        // open file csvreader object l
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader
            (absolutePath));
        }
        catch (FileNotFoundException e) {
  
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        CsvToBean csvToBean = new CsvToBean();
        
        // call the parse method of CsvToBean
        // pass strategy, csvReader to parse method
        
		List<PersonneNecessiteuse> list = csvToBean.parse(strategy, csvReader, false);
		
        for(PersonneNecessiteuse personne: list) {
        	System.out.println(personne);
        	
        	long i=PersonneNecessiteuseDao.add(personne);
        	if(i!=1)
        		nbErreur++;
        }
		return nbErreur;
	}

	public static boolean mappingPersonneNToCsv(File absoluteFile, ArrayList<PersonneNecessiteuse> personneList) {
		
  
        try {
  
            // Creating writer class to generate
            // csv file
            FileWriter writer = new 
                       FileWriter(absoluteFile);
            
            // create CSVWriter object filewriter object as parameter
            CSVWriter csvWriter = new CSVWriter(writer);
            // adding header to csv
            String[] header = { "prenom", "nom","prenom Pere","prenom Grand Pere","prenom Mere","nom Mere", "date de naissance","lieu de naissance","nationalite","gouvernorat","profession","etat civil","type piece identite", "numero piece identite", "adresse email",
        			"numero telephone","adresse","code postal", "dirigeant"};
            csvWriter.writeNext(header);
            
            // Create Mapping Strategy to arrange the 
            // column name in order
            ColumnPositionMappingStrategy mappingStrategy=
                        new ColumnPositionMappingStrategy();
            mappingStrategy.setType(PersonneNecessiteuse.class);
  
            // Arrange column name as provided in below array.
            String[] columns = new String[] 
                    { "prenom", "nom","prenomPere","prenomGPere","prenomMere","nomMere", "dateN","lieuN","nationalite","gouvernorat", "profession","etatCivil","identite","typeidentite", "email", "telephone","adresse","codePostal", "dirigeant" };
            mappingStrategy.setColumnMapping(columns);
  
            // Creating StatefulBeanToCsv object
            StatefulBeanToCsvBuilder<PersonneNecessiteuse> builder=
                        new StatefulBeanToCsvBuilder(writer);
            StatefulBeanToCsv beanWriter = 
          builder.withMappingStrategy(mappingStrategy).build();
            
            // Write list to StatefulBeanToCsv object
            beanWriter.write(personneList);
           
  
            // closing the writer object
            writer.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
		
	}

	
}