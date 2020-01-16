package com.trivadis.books.client;

import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;

public class BookCellTable extends CellTable<BookDTO> {

	private ListHandler<BookDTO> listHandler;

	public BookCellTable(ListDataProvider<BookDTO> bookDataProvider, ServiceAsync service) {

		listHandler = new ListHandler<BookDTO>(bookDataProvider.getList());
		addColumnSortHandler(listHandler);

		createTitleColumn();
		createGenreColumn();
		createDeleteColumn(bookDataProvider, service);
	}

	private void createTitleColumn() {
		Column<BookDTO, String> titleColumn = new Column<BookDTO, String>(new TextCell()) {

			@Override
			public String getValue(BookDTO book) {
				return book.getBookTitle();
			}
		};

		titleColumn.setSortable(true);
		addColumn(titleColumn, "Title");

		listHandler.setComparator(titleColumn, (a, b) -> (a.getBookTitle().compareTo(b.getBookTitle())));
	}

	private void createGenreColumn() {
		Column<BookDTO, String> genreColumn = new Column<BookDTO, String>(new TextCell()) {

			@Override
			public String getValue(BookDTO book) {
				return book.getBookGenre().getGenreTitle();
			}
		};

		genreColumn.setSortable(true);
		addColumn(genreColumn, "Genre");

		listHandler.setComparator(genreColumn,
				(a, b) -> (a.getBookGenre().getGenreTitle().compareTo(b.getBookGenre().getGenreTitle())));
	}

	private void createDeleteColumn(ListDataProvider<BookDTO> bookDataProvider, ServiceAsync service) {
		Column<BookDTO, String> deleteColumn = new Column<BookDTO, String>(new ButtonCell()) {

			@Override
			public String getValue(BookDTO object) {
				return "löschen";
			}
		};

		addColumn(deleteColumn);
		deleteColumn.setFieldUpdater(new FieldUpdater<BookDTO, String>() {
			@Override
			public void update(int index, BookDTO book, String value) {

				service.deleteBook(book, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(Void result) {
						// updateFromServer(bookDataProvider, service);
						bookDataProvider.getList().remove(index);

					}
				});

			}
		});
	}

	public void updateFromServer(ListDataProvider<BookDTO> bookDataProvider, ServiceAsync service) {
		service.getBooks(new AsyncCallback<List<BookDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());

			}

			@Override
			public void onSuccess(List<BookDTO> result) {
				bookDataProvider.getList().clear();
				bookDataProvider.getList().addAll(result);
			}

		});
		ColumnSortEvent.fire(BookCellTable.this, getColumnSortList());

	}
}
