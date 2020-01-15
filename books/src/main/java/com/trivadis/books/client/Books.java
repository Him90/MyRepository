package com.trivadis.books.client;

import com.trivadis.books.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Books implements EntryPoint {
	/**
	 * Create a remote service proxy to talk to the server-side service.
	 */
	private final ServiceAsync service = GWT.create(Service.class);
	
	private ListDataProvider<BookDTO> bookDataProvider;
	private BookCellTable bookCellTable;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		DockLayoutPanel panel = new DockLayoutPanel(Unit.PX);

		// Create Header
		Label header = new Label("Book-Store");
		header.setStyleName("title");
		panel.addNorth(header, 100);

		// Create ButtonArea
		FlowPanel buttons = new FlowPanel();
		Button buttonAdd = new Button("Add book");
		Button buttonUpdate = new Button("Update Table");

		buttons.setStyleName("buttonbar");
		buttonAdd.setStyleName("buttonAdd");
		buttonUpdate.setStyleName("buttonUpdate");

		buttons.add(buttonAdd);
		buttons.add(buttonUpdate);

		panel.addNorth(buttons, 30);


		// Create ContentArea with scrollable CellTable and DataProvider
		DockLayoutPanel content = new DockLayoutPanel(Unit.PX);
		ScrollPanel scrollTable = new ScrollPanel();

		bookDataProvider = new ListDataProvider<BookDTO>();
		bookCellTable = new BookCellTable(bookDataProvider, service);
		bookDataProvider.addDataDisplay(bookCellTable);

		content.setStyleName("content");
		scrollTable.setStyleName("scrollTable");
		scrollTable.add(bookCellTable);
		content.addNorth(scrollTable, 400);
		
		buttonAdd.setFocus(true);
		
		panel.add(content);
		RootLayoutPanel.get().add(panel);
		
		buttonAdd.addClickHandler(e -> {

				AddBookDialog dialogUIBinder = new AddBookDialog(book -> {

					service.addBook(book, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
						}

						@Override
						public void onSuccess(Void result) {
							bookCellTable.updateFromServer(bookDataProvider, service);
							ColumnSortEvent.fire(bookCellTable, bookCellTable.getColumnSortList());

						}
					});

				},service);
				dialogUIBinder.center();
				dialogUIBinder.show();


		});


}
}
