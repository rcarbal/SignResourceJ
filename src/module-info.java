module SignResourceJ {

    requires javafx.fxml;
    requires javafx.controls;
    requires java.datatransfer;
//    requires poi.ooxml;
    requires poi;
    requires poi.ooxml;

    opens discrepancies;
    opens poAverages;
}