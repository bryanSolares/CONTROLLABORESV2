package TestConexion;

import com.Controlador.GestorConexion;
import com.DAO.DAOException;
import com.DAO.Managers.DAOManager;
import com.Graficos.Principal.MenuPrincipal;
import com.Modelos.Cliente;
import com.Modelos.Tarea;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String arg[]) throws SQLException, DAOException {
        GestorConexion conexion = new GestorConexion("sa", "CmSql2kXVI", "SOPORTE2\\sql2012", "SISCONTROLLAB");
        DAOManager manager = conexion.iniciarConexion(GestorConexion.SQLSERVER);
//        Map<ResultSetMetaData, List<Cliente>> datos = manager.getDAOClientes().buscarTodos();
//        List<String> encabezados = new ArrayList<>();
//
//        for (int i = 0; i < datos.keySet().iterator().next().getColumnCount(); i++) {
//            //System.out.println(datos.keySet().iterator().next().getCatalogName(i+1));
//            //System.out.println(datos.keySet().iterator().next().getColumnClassName(i+1));
//            //System.out.println(datos.keySet().iterator().next().getColumnLabel(i+1));
//            //System.out.println(datos.keySet().iterator().next().getColumnName(i+1));
//            encabezados.add(datos.keySet().iterator().next().getColumnName(i + 1));
//            //System.out.println(datos.keySet().iterator().next().getColumnType(i+1));
//        }
//
//        datos.values().iterator().next().stream().forEach(System.out::println);
//        encabezados.forEach(System.out::println);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        new MenuPrincipal(manager).setVisible(true);
    }
}
