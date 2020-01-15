package com.trivadis.books.client;

import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.protobuf.DescriptorProtos.FieldDescriptorProto.Label;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBox;
import com.google.gwt.user.client.ui.Widget;

public class AddBookDialog extends DialogBox {

	private static AddBookDialogUiBinder uiBinder = GWT.create(AddBookDialogUiBinder.class);

	interface AddBookDialogUiBinder extends UiBinder<Widget, AddBookDialog> {
	}

	private BookDTO book;

	@UiField
	Button buttonAdd;
	@UiField
	Button buttonClose;
	@UiField
	TextBox entryBookTitle;
	@UiField
	ListBox entryBookGenre;
	
	private final Consumer<BookDTO> callback;
	private HashSet<Genre> genreSet = new HashSet<Genre>();

	public AddBookDialog(Consumer<BookDTO> callback,ServiceAsync service) {
		this.callback = callback;
		setWidget(uiBinder.createAndBindUi(this));
		this.center();
		genreSet.clear();
		loadAvailableGenres(service);		
	}


	private void initAvailableGenres() {
		for (Genre genre : genreSet) {
			entryBookGenre.addItem(genre.getGenreTitle());
		}
		
	}


	private void loadAvailableGenres(ServiceAsync service) {
		service.getGenres(new AsyncCallback<List<Genre>>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("until here");
				Window.alert(caught.getMessage());

			}

			@Override
			public void onSuccess(List<Genre> result) {
				genreSet.clear();
				genreSet.addAll(result);
				initAvailableGenres();
			}

		});
	}


	public String getBookTitle() {
		return entryBookTitle.getValue();
	}
	
	public String getBookGenre() {
		return entryBookGenre.getSelectedValue();
	}

	@UiHandler("buttonAdd")
	void onClick(ClickEvent e) {
		
		if (checkUserInput()){
		callback.accept(setUserInput());
		}
		
	}
	
	private BookDTO setUserInput() {
		book.setBookGenre(entryBookGenre.getSelectedValue());
		book.setBookTitle(entryBookTitle.getValue());
		return book;
	}

	private boolean checkUserInput() {
		return true;
		// TODO Auto-generated method stub
		
	}

	@UiHandler("buttonClose")
	void onClose(ClickEvent e) {
		hide();
	}
	
	
	

}
