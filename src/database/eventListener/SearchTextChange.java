package database.eventListener;

import java.awt.TextField;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.UI;
import database.DAO.DBDao;

@Service
public class SearchTextChange implements TextListener {
	@Autowired
	private UI ui;
	@Autowired
	private DBDao dbDao;

	@Override
	public void textValueChanged(TextEvent arg0) {
		// TODO Auto-generated method stub
		TextField search = (TextField) arg0.getSource();
		String inputValue = search.getText();
		List list = dbDao.getTables();
		ui.awtList.removeAll();
		if (inputValue.trim().length() == 0) {
			for (int i = 0; i < list.size(); i++) {
				ui.awtList.add((String) list.get(i));
				ui.statusBar.setText("共有"+ui.awtList.getItemCount()+"张表");
			}
		} else {
			for (int i = 0; i < list.size(); i++) {
				if (((String) list.get(i)).indexOf(inputValue) >= 0) {
					ui.awtList.add((String) list.get(i));
				}
			}
		}
	}
}
