package com.trivadis.books.client;

import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AddBookDialog extends DialogBox {

	private static AddBookDialogUiBinder uiBinder = GWT.create(AddBookDialogUiBinder.class);

	interface AddBookDialogUiBinder extends UiBinder<Widget, AddBookDialog> {
	}

	@UiField
	Button buttonAdd;
	@UiField
	Button buttonClose;
	@UiField
	TextBox entryBookTitle;
	@UiField
	ListBox entryBookGenre;
	@UiField
	Label userMessage;

	private final Consumer<BookDTO> callback;
	private HashSet<Genre> genreSet = new HashSet<Genre>();
	private static int preselectedGenre = 0;

	public AddBookDialog(Consumer<BookDTO> callback, ServiceAsync service) {
		this.callback = callback;
		setWidget(uiBinder.createAndBindUi(this));
		this.center();
		loadAvailableGenres(service);
		entryBookTitle.setFocus(true);
	}

	private void loadAvailableGenres(ServiceAsync service) {
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
			}

		});
	}

	private void initAvailableGenres() {
		for (Genre genre : genreSet) {
			entryBookGenre.addItem(genre.getGenreTitle());
		}

		entryBookGenre.setSelectedIndex(preselectedGenre);

	}

	@UiHandler("entryBookGenre")
	void onChange(ChangeEvent e) {
		preselectedGenre = entryBookGenre.getSelectedIndex();

	}

	@UiHandler("entryBookTitle")
	void onInputLength(KeyPressEvent e) {
		int userInputLength = entryBookTitle.getText().length();

		if (userInputLength < 70) {
			userMessage.setText("Enter title here");
			userMessage.getElement().getStyle().setColor("green");

		}
		if (userInputLength >= 70) {
			userMessage.setText("To much text! You have " + ((Integer) (80 - userInputLength - 1)).toString()
					+ " characters left!");
			userMessage.getElement().getStyle().setColor("orange");
			if (userInputLength == 79) {
				userMessage.getElement().getStyle().setColor("red");

			}
		}
		if (userInputLength == 80) {
			userMessage.setText("Maximal allowed input length! Keep it short!");
			userMessage.getElement().getStyle().setColor("red");
			entryBookTitle.setText(entryBookTitle.getText().substring(0, entryBookTitle.getText().length() - 1));
			entryBookTitle.setCursorPos(80);
		}
	}

	@UiHandler("buttonAdd")
	void onClick(ClickEvent e) {

		if (checkInputNotEmpty()) {
			callback.accept(userInput());
			entryBookTitle.setValue("");
			entryBookTitle.setFocus(true);

		}

	}

	@UiHandler("buttonClose")
	void onClose(ClickEvent e) {
		hide();
	}

	private BookDTO userInput() {
		BookDTO book = new BookDTO();
		book.getBookGenre().setGenreTitle(entryBookGenre.getSelectedValue());
		book.setBookTitle(entryBookTitle.getValue());
		return book;
	}

	public Boolean checkInputNotEmpty() {
		if (!entryBookTitle.getValue().equals("")) {
			userMessage.getElement().getStyle().setColor("green");
			userMessage.setText("another title?");
			return true;
		} else {
			userMessage.setText("Input is missing");
			userMessage.getElement().getStyle().setColor("red");
			return false;
		}
	}

}
