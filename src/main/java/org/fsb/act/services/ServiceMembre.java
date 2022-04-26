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
import org.fsb.act.entities.Membre;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import com.opencsv.CSVReader;
public class ServiceMembre {

	public static List<Membre> getAll(){
		return DaoMembre.getAll();
	}

	public static int mappingCsvToMembre(String absolutePath) {
		
		int nbErreur=0;
		// Hashmap to map CSV data to membre data
        Map<String, String> mapping= new HashMap<>();
        mapping.put("prenom","prenom");
        mapping.put("nom","nom");
        mapping.put("date de naissance","dateNaissance");
        mapping.put("profession","profession");
        mapping.put("adresse email","email");
        mapping.put("numero telephone","telephone");
        mapping.put("type piece identite","typePiece");
        mapping.put("numero piece identite","numeroIdentite");
        mapping.put("dirigeant","dirigeant");
        
        // HeaderColumnNameTranslateMappingStrategy
        // for Membre class
        HeaderColumnNameTranslateMappingStrategy<Membre> strategy =
             new HeaderColumnNameTranslateMappingStrategy<Membre>();
        strategy.setType(Membre.class);
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
        
		List<Membre> list = csvToBean.parse(strategy, csvReader, false);
		
        for(Membre membre: list) {
        	System.out.println(membre);
        	
        	long i=DaoMembre.addMembreInDb(membre);
        	if(i!=1)
        		nbErreur++;
        }
		return nbErreur;
	}

	public static boolean mappingMembreToCsv(File absoluteFile, ArrayList<Membre> membreList) {
		
  
        try {
  
            // Creating writer class to generate
            // csv file
            FileWriter writer = new 
                       FileWriter(absoluteFile);
            
            // create CSVWriter object filewriter object as parameter
            CSVWriter csvWriter = new CSVWriter(writer);
            // adding header to csv
            String[] header = { "prenom", "nom", "date de naissance","profession", "adresse email",
        			"numero telephone", "type piece identite", "numero piece identite", "dirigeant"};
            csvWriter.writeNext(header);
            
            // Create Mapping Strategy to arrange the 
            // column name in order
            ColumnPositionMappingStrategy mappingStrategy=
                        new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Membre.class);
  
            // Arrange column name as provided in below array.
            String[] columns = new String[] 
                    { "prenom", "nom", "dateNaissance", "profession", "email", "telephone","numeroIdentite", "typePiece", "dirigeant" };
            mappingStrategy.setColumnMapping(columns);
  
            // Creating StatefulBeanToCsv object
            StatefulBeanToCsvBuilder<Membre> builder=
                        new StatefulBeanToCsvBuilder(writer);
            StatefulBeanToCsv beanWriter = 
          builder.withMappingStrategy(mappingStrategy).build();
            
            // Write list to StatefulBeanToCsv object
            beanWriter.write(membreList);
           
  
            // closing the writer object
            writer.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
		
	}

	public static ObservableList<Membre> searchMembres(ObservableList<Membre> data, String text) {
		ObservableList<Membre>  localData= FXCollections.observableArrayList();
		for(Membre membre: data) {
			if(membre.getPrenom().matches(text+".*") ||
		       membre.getNom().matches(text+".*") ||
		       (membre.getPrenom()+" "+membre.getNom()).matches(text+".*")||
		       membre.getDateNaissance().matches(".*"+text+".*")||
		       membre.getProfession().matches(text+".*") ||
		       membre.getEmail().matches(text+".*") ||
		       membre.getTelephone().matches(text+".*") ||
		       membre.getTypePiece().matches(text+".*") ||
		       (""+membre.getNumeroIdentite()).matches(text+".*") ||
		       membre.getDirigeant().matches(text+".*")) {
				
				localData.add(membre);
			}
		}
		return localData;
	}

	public static long ajouterMembre(Membre membre) {
		
		return DaoMembre.addMembreInDb(membre);
	}

	public static int supprimerMembre(long id) {
		
		return DaoMembre.deleteMembreFromDb(id);
	}

	public static int modifierMembre(Membre membre) {
		
		return DaoMembre.modifierMembreFromDb(membre);
	}
	public static Membre getOneById(long id) {
		return DaoMembre.getOneById(id);
	}
}
