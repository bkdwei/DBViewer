package database.eventListener;

import java.awt.TextField;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import org.springframework.stereotype.Service;
@Service
public class CleanDefaulText implements FocusListener {

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		TextField search = (TextField) arg0.getSource();
		String inputValue = search.getText();
		if (inputValue.trim().equals("请输入您要搜索的表名")||inputValue.trim().equals("请输入您要执行的命令")) {
			search.setText("");
		} 
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

}
