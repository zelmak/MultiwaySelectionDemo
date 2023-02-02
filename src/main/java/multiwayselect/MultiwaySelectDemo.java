package multiwayselect;

import javafx.application.*;
import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class MultiwaySelectDemo extends Application {

    private final ObservableList<Assembly> assemblies = FXCollections.observableArrayList();
    private final ObservableList<Part> parts = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        buildModel();
        stage.setTitle("multi-way selection demo");
        final Region root = buildView();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void buildModel() {
        Part cpu = new Part(1, "CPU Ryzen 5");
        Part ram8 = new Part(2, "RAM 8GB DDR4 (1x8GB)");
        Part mobo1 = new Part(3, "MOBO ATX B550");
        Part chassis = new Part(4, "CASE Standard ATX Case");
        Part ram16 = new Part(5, "RAM 16GB DDR4 (2x8GB)");
        Part mobo2 = new Part(7, "MOBO ATX X570 RGB");
        Part chassis1 = new Part(8, "CASE Gamer ATX Case w/RGB");
        Assembly basicBox = new Assembly(1, "Basic AMD Box", cpu, ram8, mobo1, chassis);
        Assembly gamerBox = new Assembly(2, "Gamer AMD Box", cpu, ram16, mobo2, chassis1);
        assemblies.addAll(basicBox, gamerBox);
        for (Assembly a : assemblies) {
            for (Part p : a.partsProperty()) {
                if (!parts.contains(p)) {
                    parts.add(p);
                }
            }
        }
    }

//    private boolean selecting = false;
    private Region buildView() {
        TableView<Part> partsTable = setupPartsTableView();
        TableView<Assembly> assembliesTable = setupAssembliesTableView();
        MultiwaySelectionHandler selectionHandler = new MultiwaySelectionHandler();
        selectionHandler.setupMultiSelection(assembliesTable, partsTable,
                (assembly, part) -> assembly.partsProperty().contains(part));

//        assembliesTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Assembly>() {
//            @Override
//            public void onChanged(Change<? extends Assembly> c) {
//                if (!selecting) {
//                    selecting = true;
//
//                    TableViewSelectionModel<Part> sm = partsTable.getSelectionModel();
//                    sm.clearSelection();
//                    for (Assembly a : c.getList()) {
//                        for (Part p : a.partsProperty()) {
//                            sm.select(p);
//                        }
//                    }
//                    selecting = false;
//                }
//
//            }
//        });
//
//        partsTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Part>() {
//            @Override
//            public void onChanged(Change<? extends Part> c) {
//                if (!selecting) {
//                    selecting = true;
//                    TableViewSelectionModel<Assembly> sm = assembliesTable.getSelectionModel();
//                    sm.clearSelection();
//                    for (Part p : c.getList()) {
//                        for (Assembly a : assemblies) {
//                            if (a.partsProperty().contains(p)) {
//                                sm.select(a);
//                            }
//                        }
//                    }
//                    selecting = false;
//                }
//            }
//        });
        return new SplitPane(assembliesTable, partsTable);
    }


    private TableView setupAssembliesTableView() {
        final TableView tableView = new TableView(assemblies);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        final TableColumn<Assembly, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(cell -> cell.getValue().idProperty());

        final TableColumn<Assembly, String> nameColumn = new TableColumn<>("name");
        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());

        tableView.getColumns().addAll(idColumn, nameColumn);
        return tableView;
    }

    private TableView setupPartsTableView() {
        final TableView tableView = new TableView(parts);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        final TableColumn<Part, Integer> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(cell -> cell.getValue().idProperty());

        final TableColumn<Part, String> nameColumn = new TableColumn<>("name");
        nameColumn.setCellValueFactory(cell -> cell.getValue().nameProperty());

        tableView.getColumns().addAll(idColumn, nameColumn);

        return tableView;
    }



}
