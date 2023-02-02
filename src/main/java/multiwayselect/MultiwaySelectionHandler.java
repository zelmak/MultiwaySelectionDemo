
package multiwayselect;

import java.util.function.*;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.control.*;

public class MultiwaySelectionHandler {

    private boolean selecting;

    public <S, T> void setupMultiSelection(TableView<S> firstTable, TableView<T> secondTable, BiPredicate<S, T> shouldSelect) {
        firstTable.getSelectionModel().getSelectedItems().addListener((Change<? extends S> c) -> {
            if (selecting) {
                return;
            }
            selecting = true;
            secondTable.getSelectionModel().clearSelection();
            for (T t : secondTable.getItems()) {
                for (S s : firstTable.getSelectionModel().getSelectedItems()) {
                    if (shouldSelect.test(s, t)) {
                        secondTable.getSelectionModel().select(t);
                    }
                }
            }
            selecting = false;
        });
        secondTable.getSelectionModel().getSelectedItems().addListener((Change<? extends T> c) -> {
            if (selecting) {
                return;
            }
            selecting = true;
            firstTable.getSelectionModel().clearSelection();
            for (S s : firstTable.getItems()) {
                for (T t : secondTable.getSelectionModel().getSelectedItems()) {
                    if (shouldSelect.test(s, t)) {
                        firstTable.getSelectionModel().select(s);
                    }
                }
            }
            selecting = false;
        });
    }

}
