package database;

import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

import database.DAO.DBDao;
import database.eventListener.ButtonClickHandler;
import database.eventListener.CleanDefaulText;
import database.eventListener.ItemStatusChange;
import database.eventListener.SearchTextChange;

@Service
public class DBViewer {

	@Autowired
	private DBDao dbDao;
	@Autowired
	public UI ui;
	@Autowired
	private ItemStatusChange itemStatusChange;
	@Autowired
	private SearchTextChange searchTextChange;
	@Autowired
	private CleanDefaulText cleanDefaulText;
	@Autowired
	private ButtonClickHandler buttonClickHandler;

	public void testConnection() {
		int testResult = dbDao.testConnection();
		if (testResult == 0) {
			JOptionPane.showMessageDialog(null, "数据库连接失败，请重新配置数据库的连接信息！", "数据库连接测试", 0, null);
			ui.statusBar.setText("数据库连接失败");
		} else if (testResult == 1) {
			JOptionPane.showMessageDialog(null, "数据库连接成功！", "数据库连接测试", 1, null);
		}
	}

	public void init() {

		ui.awtList.addItemListener(itemStatusChange);
		ui.search.addTextListener(searchTextChange);
		ui.search.addFocusListener(cleanDefaulText);
		ui.tfSqlCommand.addFocusListener(cleanDefaulText);
		ui.btDeleteTableData.addActionListener(buttonClickHandler);
		ui.btExecuteSql.addActionListener(buttonClickHandler);
		ui.btQueryAllData.addActionListener(buttonClickHandler);
		ui.btAbout.addActionListener(buttonClickHandler);
		testConnection();
		List l = dbDao.getTables();
		for (int i = 0; i < l.size(); i++) {
			ui.awtList.add((String) l.get(i));
		}
		ui.statusBar.setText("数据库连接成功。共有" + ui.awtList.getItemCount() + "张表");
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new FileSystemXmlApplicationContext("beans-config.xml");
		DBViewer dbViewer = (DBViewer) ctx.getBean("dbViewer");
		dbViewer.dbDao.testConnection();
		dbViewer.ui.init();
		dbViewer.ui.compose();

		dbViewer.init();
		

	}

}

