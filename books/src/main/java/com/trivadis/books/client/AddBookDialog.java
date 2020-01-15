package com.trivadis.books.client;

import java.util.function.Consumer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.protobuf.DescriptorProtos.FieldDescriptorProto.Label;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
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

	public AddBookDialog(Consumer<BookDTO> callback) {
		this.callback = callback;
		setWidget(uiBinder.createAndBindUi(this));
		this.center();

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
