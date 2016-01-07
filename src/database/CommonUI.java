package database;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.springframework.stereotype.Component;

import database.eventListener.ExitWnd;

@Component
public class CommonUI {
	public Frame frame;

	// 主窗口包含工具栏、数据区、状态栏
	public Panel body;
	public Panel toolBar;
	public Panel dataArea;
	public Label statusBar;

	// 工具栏元素
	public Button btDeleteTableData;
	public TextField tfSqlCommand;

	// 数据区左侧是搜索框和数据库的表，右侧是表的数据展示区
	public Panel westPanel;
	public TextField search;
	public List awtList;
	// 给table添加滚动动条
	public JScrollPane scrollPane;
	public JTable table;

	// 创建UI元素
	public void init() {
		frame = new Frame("数据库查看器");
		body = new Panel();
		toolBar = new Panel();
		dataArea = new Panel();
		statusBar = new Label("status");

		btDeleteTableData = new Button("删除本表数据");
		tfSqlCommand = new TextField("请输入您要执行的命令",50);

		westPanel = new Panel();
		search = new TextField("请输入您要搜索的表名", 25);
		awtList = new List();
		scrollPane = new JScrollPane();
		table = new JTable();
	}

	// 设置UI元素的属性
	public void compose() {
		frame.setSize(860, 640);
		frame.setLocation(200, 50);
		frame.setLayout(new BorderLayout());
		frame.addWindowListener(new ExitWnd());
		body.setLayout(new BorderLayout());

		// 工具栏
		toolBar.add(btDeleteTableData);
		toolBar.add(tfSqlCommand);
		body.add("North", toolBar);

		// 数据管理层
		westPanel.setLayout(new BorderLayout());
		westPanel.add("North",search);
		westPanel.add("Center",awtList);
		dataArea.add("West", westPanel);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setShowGrid(true);
		table.setAutoscrolls(true);
		table.setDragEnabled(true);
		table.setSelectionBackground(new Color(233, 223, 233));
		scrollPane.setViewportView(table);
		dataArea.add("Center", scrollPane);

		body.add("Center", dataArea);
		body.add("South", statusBar);

		frame.add(body);
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		CommonUI ui = new CommonUI();
		ui.init();
		ui.compose();
	
	}

}
