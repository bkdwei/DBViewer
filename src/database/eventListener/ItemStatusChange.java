package database.eventListener;

import java.awt.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.UI;
import database.DAO.DBDao;

@Service
public class ItemStatusChange implements ItemListener {

	@Autowired
	private UI ui;
	@Autowired
	private DBDao dbDao;

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		List l = (List) arg0.getItemSelectable();
		String tableName = l.getSelectedItem();
		ui.statusBar.setText(tableName);
		DefaultTableModel tm = dbDao.getTableData(tableName);
		if (tm != null) {
			ui.table.setModel(tm);
			ui.statusBar.setText(tableName+"：共"+(tm.getRowCount()-1)+"条数据");
		}

	}

}
