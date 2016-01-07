package database.eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import database.UI;
import database.DAO.DBDao;
import database.DAO.DBTableServiceDAO;

@Service
public class ButtonClickHandler implements ActionListener {
	@Autowired
	private UI ui;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DBDao dbDao;
	@Autowired
	private DBTableServiceDAO dBTableServiceDAO;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String cmd = (String) arg0.getActionCommand();
		String tableName = ui.awtList.getSelectedItem();
		switch (cmd) {
		case "执行":
			String SQLCmd = ui.tfSqlCommand.getText();
			jdbcTemplate.execute(SQLCmd);
			ui.statusBar.setText("成功执行命令：" + SQLCmd);
			ui.table.setModel(dbDao.getTableData(tableName));
			break;
		case "收集所有数据":
			List tables = dbDao.getTables();
			StringBuffer sbAllData = new StringBuffer();
			for(int i=0;i<tables.size();i++){
				Vector tmp = dBTableServiceDAO.getTableData((String)tables.get(i));
				//sbAllData.append(tmp.toString()+"\n");
			//	System.out.println(tmp.toString());
			}
			//JOptionPane.showMessageDialog(null, sbAllData);
			break;
		case "删除本表数据":
			dbDao.deleteTableData(tableName);
			ui.statusBar.setText("成功删除" + tableName + "的数据");
			ui.table.setModel(dbDao.getTableData(tableName));
			break;
		case "关于":
			JOptionPane.showMessageDialog(null, "版本：v1.1\n作者：bkd\n联系方式：bkdwei@bkdwei.com\n更新日期：2015-08-30\n", "关于	",
					1, null);
			break;
		default:
			ui.statusBar.setText("无任何操作可执行");
		}
	}
}
