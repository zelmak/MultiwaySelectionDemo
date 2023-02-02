
package multiwayselect;

import javafx.beans.property.*;

public class Part {

    private ObjectProperty<Integer> id;
    private StringProperty name;

    public Part(int newId, String newName) {
        this.id = new SimpleObjectProperty<>();
        this.name = new SimpleStringProperty();
        setId(newId);
        setName(newName);
    }

    public final ObjectProperty<Integer> idProperty() {
        return this.id;
    }

    public final void setId(int newValue) {
        idProperty().set(newValue);
    }

    public final int getId() {
        return idProperty().get();
    }

    public final StringProperty nameProperty() {
        return this.name;
    }

    public final void setName(String newValue) {
        nameProperty().set(newValue);
    }

    public final String getName() {
        return nameProperty().get();
    }

}
