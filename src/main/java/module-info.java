module org.fsb.act {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	requires opencsv;
	requires com.oracle.database.jdbc;
	

    opens org.fsb.act to javafx.fxml;
    opens org.fsb.act.entities to javafx.base, opencsv;
    opens org.fsb.act.controllers to javafx.fxml;
    
    exports org.fsb.act;
    exports org.fsb.act.controllers;
}
