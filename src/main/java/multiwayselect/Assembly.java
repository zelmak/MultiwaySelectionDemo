
package multiwayselect;

import javafx.beans.property.*;
import javafx.collections.*;

public class Assembly {

    private ObjectProperty<Integer> id;
    private StringProperty name;
    private ObservableList<Part> parts;

    public Assembly(int newId, String newName, Part... newParts) {
        id = new SimpleObjectProperty<>();
        name = new SimpleStringProperty();
        parts = FXCollections.observableArrayList();
        setId(newId);
        setName(newName);
        parts.setAll(newParts);
    }

    public final ObjectProperty<Integer> idProperty() {
        return id;
    }

    public final void setId(int newId) {
        idProperty().set(newId);
    }

    public final int getId() {
        return idProperty().get();
    }

    public final StringProperty nameProperty() {
        return name;
    }

    public final void setName(String newValue) {
        nameProperty().set(newValue);
    }

    public final String getName() {
        return nameProperty().get();
    }

    public final ObservableList<Part> partsProperty() {
        return parts;
    }

}
