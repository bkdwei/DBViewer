package database.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBTableServiceDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Vector getTableData(String tableName) {
		Connection conn =null;
//		DefaultTableModel tableMode = null;
		Vector rows = new Vector();

		if (tableName != null) {
			try {
				conn = jdbcTemplate.getDataSource().getConnection();
				Statement stmt = conn.createStatement();

				String sql = "select * from " + tableName;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql.toLowerCase());
				ResultSetMetaData rsd = rs.getMetaData();
				int number = rsd.getColumnCount();
				System.out.println("columnConut:" + number);

				Vector columnNames = new Vector();

				for (int num = 0; num < number; num++) {
					String columnName = rsd.getColumnLabel(num + 1);
					columnNames.addElement(columnName);
					
				}
				rows.addElement(columnNames);
				System.out.println(columnNames.toString());

				int checkList = 0;

				while (rs.next()) {
					Vector newRow = new Vector();

					for (int i = 0; i < number; i++) {

						newRow.addElement(rs.getObject(i + 1));
						rows.addElement(newRow);
						
					}
					System.out.println(newRow.toString());
				}
			//	tableMode = new DefaultTableModel(rows, columnNames);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try{
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rows;
	}

}
