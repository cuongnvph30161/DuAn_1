package utilities;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class DBackUpAndRestore {

    private static void createFolder() {
        File dirBackup = new File("C:\\ToTo\\Backup");
        if (!dirBackup.exists()) {
            dirBackup.mkdirs();
        }
    }

    public static void createBackup() {
        createFolder();
        File src = new File("C:\\ToTo\\Backup");
        if (!src.isDirectory() || !src.exists()) {
            return;
        }
        try {
            Connection conn = JdbcHelper.getConnection();
            Statement st = conn.createStatement();
            String querry = ""
                    + "Begin try "
                    + "DECLARE @FileName varchar(100) "
                    + "Set @FileName=CONCAT('C:\\ToTo\\Backup\\',CONCAT_WS('_',YEAR(GETDATE()),Month(GETDATE()),DAY(GETDATE()),"
                    + "'__',DATEPART(HOUR,GETDATE()),DATEPART(MINUTE,GETDATE()),DATEPART(SECOND,GETDATE())),'.bak') "
                    + "BACKUP DATABASE ToTo "
                    + "TO DISK = @FileName "
                    + "SELECT @FileName "
                    + "END TRY "
                    + "BEGIN CATCH "
                    + "SELECT ''"
                    + "END CATCH";
            ResultSet rs = st.executeQuery(querry);
            rs.next();
            String mss = rs.getString(1);
            JOptionPane.showMessageDialog(null, mss.equals("") ? "Backup thất bại" : "<html>Backup thành công<hr>File được lưu tại:<br>" + mss + "</html>");
            conn.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static List<String> getListFileBackUp() {
        List<String> lstFileName = new ArrayList<>();
        File dir = new File("C:\\ToTo\\Backup");
        for (File file : dir.listFiles()) {
            lstFileName.add(file.getName());
        }
        return lstFileName;
    }

    public static void restoreDatabase(String fileName) {
        try {

            Connection conn = JdbcHelper.getConnection("master");
            Statement st = conn.createStatement();
            var querry = ""
                    + "DECLARE @kill varchar(8000) = ''; "
                    + "SELECT @kill = @kill + 'kill ' + CONVERT(varchar(5), session_id) + ';' "
                    + "FROM sys.dm_exec_sessions "
                    + "WHERE database_id  = db_id('ToTo') "
                    + "print @kill "
                    + "exec(@kill) "
                    + "BEGIN TRY "
                    + "Restore database ToTo "
                    + "From disk= 'C:\\ToTo\\Backup\\" + fileName + "' "
                    + "with replace "
                    + "SELECT 1 "
                    + "END TRY "
                    + "BEGIN CATCH "
                    + "SELECT 0 "
                    + "END CATCH";
            ResultSet rs = st.executeQuery(querry);
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getInt(1) == 1 ? "Backup thành công ! Vui lòng khởi động lại trương trình" : "Backup thất bại");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void main(String[] args) {
		restoreDatabase("2023_7_18____15_53_42.bak");
    }

}
