package com.trivadis.books.client;

import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;

public class BookCellTable extends CellTable<BookDTO>{

	public BookCellTable(ListDataProvider<BookDTO> bookDataProvider, ServiceAsync service) {
		// TODO Auto-generated constructor stub
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

