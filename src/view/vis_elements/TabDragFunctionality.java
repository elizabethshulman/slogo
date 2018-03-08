package view.vis_elements;


import java.util.concurrent.atomic.AtomicLong;

import javafx.collections.ListChangeListener.Change;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

/**
 * Add tab reordering functionality to a TabPane tab.
 * Code from <a href="https://stackoverflow.com/questions/41473987/how-to-drag-and-drop-tabs-of-the-same-tabpane">this StackOverflow post</a>
 * @author James_D, with minor modifications for clarity by @author elizabethshulman
 *
 */
public class TabDragFunctionality {
	
	    private Tab currentDraggingTab ;
	
	    private static final AtomicLong idGenerator = new AtomicLong();
	
	    private final String draggingID = "DraggingTabPaneSupport-"+idGenerator.incrementAndGet() ;
	
	    public void addSupport(TabPane tabPane) {
	        tabPane.getTabs().forEach(this::addDragHandlers);
	        tabPane.getTabs().addListener((Change<? extends Tab> c) -> {
	            while (c.next()) {
	                if (c.wasAdded()) {
	                    c.getAddedSubList().forEach(this::addDragHandlers);
	                }
	                if (c.wasRemoved()) {
	                    c.getRemoved().forEach(this::removeDragHandlers);
	                }
	            }
	        });
	
	        enableMovementToEnd(tabPane);
	    }
	
	    private void enableMovementToEnd(TabPane tabPane) {
	        tabPane.setOnDragOver(e -> {
	            if (draggingID.equals(e.getDragboard().getString()) && 
	                    currentDraggingTab != null &&
	                    currentDraggingTab.getTabPane() != tabPane) {
	                e.acceptTransferModes(TransferMode.MOVE);
	            }
	        });
	        tabPane.setOnDragDropped(e -> {
	            if (draggingID.equals(e.getDragboard().getString()) && 
	                    currentDraggingTab != null &&
	                    currentDraggingTab.getTabPane() != tabPane) {
	
	                currentDraggingTab.getTabPane().getTabs().remove(currentDraggingTab);
	                tabPane.getTabs().add(currentDraggingTab);
	                currentDraggingTab.getTabPane().getSelectionModel().select(currentDraggingTab);
	            }
	        });
	    }
	    
	    private void addDragHandlers(Tab tab) {
	
	        // move text to label graphic:
	        if (tab.getText() != null && ! tab.getText().isEmpty()) {
	            Label label = new Label(tab.getText(), tab.getGraphic());
	            tab.setText(null);
	            tab.setGraphic(label);
	        }
	
	        Node graphic = tab.getGraphic();
	        graphic.setOnDragDetected(e -> {
	            Dragboard dragboard = graphic.startDragAndDrop(TransferMode.MOVE);
	            ClipboardContent content = new ClipboardContent();
	            content.putString(draggingID);
	            dragboard.setContent(content);
	            dragboard.setDragView(graphic.snapshot(null, null));
	            currentDraggingTab = tab ;
	        });
	        
	        graphic.setOnDragOver(e -> {
	            if (draggingID.equals(e.getDragboard().getString()) && 
	                    currentDraggingTab != null &&
	                    currentDraggingTab.getGraphic() != graphic) {
	                e.acceptTransferModes(TransferMode.MOVE);
	            }
	        });
	        
	        graphic.setOnDragDropped(e -> {
	            if (draggingID.equals(e.getDragboard().getString()) && 
	                    currentDraggingTab != null &&
	                    currentDraggingTab.getGraphic() != graphic) {
	
	                int index = tab.getTabPane().getTabs().indexOf(tab) ;
	                currentDraggingTab.getTabPane().getTabs().remove(currentDraggingTab);
	                tab.getTabPane().getTabs().add(index, currentDraggingTab);
	                currentDraggingTab.getTabPane().getSelectionModel().select(currentDraggingTab);
	            }
	        });
	        
	        graphic.setOnDragDone(e -> currentDraggingTab = null);
	    }
	
	    private void removeDragHandlers(Tab tab) {
	        tab.getGraphic().setOnDragDetected(null);
	        tab.getGraphic().setOnDragOver(null);
	        tab.getGraphic().setOnDragDropped(null);
	        tab.getGraphic().setOnDragDone(null);
	    }
}