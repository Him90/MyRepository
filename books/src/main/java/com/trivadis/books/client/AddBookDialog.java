package com.trivadis.books.client;

import java.awt.print.Book;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBox;
import com.google.gwt.user.client.ui.Widget;

import net.bytebuddy.implementation.attribute.AnnotationAppender.Target.OnType;

public class AddBookDialog extends DialogBox {

	private static AddBookDialogUiBinder uiBinder = GWT.create(AddBookDialogUiBinder.class);

	interface AddBookDialogUiBinder extends UiBinder<Widget, AddBookDialog> {
	}

	//private BookDTO book;

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

	public AddBookDialog(Consumer<BookDTO> callback,ServiceAsync service) {
		this.callback = callback;
		setWidget(uiBinder.createAndBindUi(this));
		this.center();
		genreSet.clear();
		loadAvailableGenres(service);	
		handleUserInput();
	}


	private void initAvailableGenres() {
		for (Genre genre : genreSet) {
			entryBookGenre.addItem(genre.getGenreTitle());
		}
		
		GWT.log(Integer.toString(preselectedGenre));
		entryBookGenre.setSelectedIndex(preselectedGenre);
		
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



	@UiHandler("buttonAdd")
	void onClick(ClickEvent e) {
		
		if (checkInputNotEmpty()){
		callback.accept(setUserInput());
		entryBookTitle.setValue("");
		preselectedGenre = entryBookGenre.getSelectedIndex();
		GWT.log(Integer.toString(preselectedGenre));

		}
		
	}
	
	private BookDTO setUserInput() {
		BookDTO book = new BookDTO();
		book.getBookGenre().setGenreTitle(entryBookGenre.getSelectedValue());
		book.setBookTitle(entryBookTitle.getValue());
		return book;
	}


	@UiHandler("buttonClose")
	void onClose(ClickEvent e) {
		hide();
	}
	

	public Boolean checkInputNotEmpty() {
		if (!entryBookTitle.getValue().equals("") ){
			userMessage.getElement().getStyle().setColor("green");
			userMessage.setText("weiterer Titel?");
			return true;
		} else {
			userMessage.setText("Eingabe fehlt");
			userMessage.getElement().getStyle().setColor("red");
			return false;
		}
	}
	
	private void handleUserInput() {
		entryBookTitle.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				int userInputLength = entryBookTitle.getText().length();

				if (userInputLength >= 70) {
					userMessage.setText("Zu viel Text! Es bleiben nur noch "+((Integer)(80-userInputLength)).toString() + " Zeichen!");
					userMessage.getElement().getStyle().setColor("orange");

				}
				if (userInputLength == 80) {
					userMessage.setText("Maximale Anzahl an Zeichen erreicht!");
					userMessage.getElement().getStyle().setColor("red");
					entryBookTitle.setText(entryBookTitle.getText().substring(0, entryBookTitle.getText().length() - 1));
					entryBookTitle.setCursorPos(80);
				}

			}

		});
	}

}
