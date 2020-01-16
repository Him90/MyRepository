package com.trivadis.books.client;

import java.util.HashSet;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
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
	private HashSet<Genre> genreSet = new HashSet<Genre>();
	private ListBox listGenreFiltersBox;
	private TextBox filterByBookTitle;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		DockLayoutPanel panel = new DockLayoutPanel(Unit.PX);

		// Create Header
		Label header = new Label("Book-Store");
		header.setStyleName("title");
		panel.addNorth(header, 100);

		FlowPanel buttons = new FlowPanel();
		Button buttonAdd = new Button("Add book");
		Button buttonUpdate = new Button("Update Table");

		FlowPanel filters = new FlowPanel();
		InlineLabel filterGenreInfo = new InlineLabel("Select to filter by genre");
		InlineLabel filterTextInfo = new InlineLabel("Select to filter by booktitle");

		listGenreFiltersBox = new ListBox();
		filterByBookTitle = new TextBox();

		buttons.setStyleName("buttonbar");
		filters.setStyleName("filters");
		filterGenreInfo.setStyleName("label");
		filterTextInfo.setStyleName("label");
		buttonAdd.setStyleName("button");
		buttonUpdate.setStyleName("button");
		listGenreFiltersBox.setStyleName("button");
		filterByBookTitle.setStyleName("textInput");

		loadAvailableGenres();

		filters.add(filterGenreInfo);
		filters.add(listGenreFiltersBox);
		filters.add(filterTextInfo);
		filters.add(filterByBookTitle);
		buttons.add(buttonAdd);
		buttons.add(buttonUpdate);

		panel.addNorth(buttons, 60);
		panel.addSouth(filters, 60);

		// Create ContentArea with scrollable CellTable and DataProvider
		DockLayoutPanel content = new DockLayoutPanel(Unit.PX);
		ScrollPanel scrollTable = new ScrollPanel();

		bookDataProvider = new ListDataProvider<BookDTO>();
		bookCellTable = new BookCellTable(bookDataProvider, service);
		bookDataProvider.addDataDisplay(bookCellTable);

		bookCellTable.updateFromServer(bookDataProvider, service);

		bookCellTable.setStyleName("table");
		content.setStyleName("content");
		scrollTable.setStyleName("scrollTable");
		scrollTable.add(bookCellTable);
		content.addNorth(scrollTable, 400);

		panel.add(content);
		RootLayoutPanel.get().add(panel);

		buttonAdd.addClickHandler(e -> {

			AddBookDialog dialogUIBinder = new AddBookDialog(book -> {
				bookCellTable.setPageSize(bookDataProvider.getList().size()+1);

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

			}, service);


			listGenreFiltersBox.setSelectedIndex(0);

			dialogUIBinder.center();
			dialogUIBinder.show();

		});

		buttonUpdate.addClickHandler(e -> {

			bookCellTable.updateFromServer(bookDataProvider, service);
			listGenreFiltersBox.setSelectedIndex(0);

		});

		listGenreFiltersBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent arg0) {
				showFilteredResult();

			}
		});

		filterByBookTitle.addKeyDownHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					showFilteredResult();
				}

			}
		});

	}

	private void loadAvailableGenres() {
		service.getGenres(new AsyncCallback<List<Genre>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());

			}

			@Override
			public void onSuccess(List<Genre> result) {
				genreSet.clear();
				genreSet.addAll(result);
				initAvailableGenres();
				GWT.log("load works");
			}

		});
	}

	private void initAvailableGenres() {
		listGenreFiltersBox.addItem("none");

		for (Genre genre : genreSet) {
			listGenreFiltersBox.addItem(genre.getGenreTitle());
		}

	}

	public void showFilteredResult() {
		service.getBooks(new AsyncCallback<List<BookDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());

			}

			@Override
			public void onSuccess(List<BookDTO> result) {
				bookDataProvider.getList().clear();

				for (BookDTO book : result) {
					if ((book.getBookGenre().getGenreTitle() == listGenreFiltersBox.getSelectedValue())
							&& (book.getBookTitle().contains(filterByBookTitle.getValue()))) {

						bookDataProvider.getList().add(book);

					}
					if (listGenreFiltersBox.getSelectedValue() == "none"
							&& (book.getBookTitle().contains(filterByBookTitle.getValue()))) {
						// bookDataProvider.getList().clear();
						bookDataProvider.getList().add(book);

						// bookDataProvider.getList().addAll(result);

					}

				}
			}

		});

		ColumnSortEvent.fire(bookCellTable, bookCellTable.getColumnSortList());

	}

}
