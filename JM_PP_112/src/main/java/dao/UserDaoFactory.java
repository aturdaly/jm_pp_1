package dao;

import util.DBHelper;
import util.PropertiesReader;

public class UserDaoFactory {
    private static final String CONFIG_FILE = "config.properties";
    private static final String DAOTYPE = "daotype";

    public static UserDao getUserDaoImpl() {
        switch (PropertiesReader.getPropertiesValue(CONFIG_FILE, DAOTYPE)) {
            case "jdbc":
                return UserDaoJDBC.getInstance(DBHelper.getInstance().getConnection());
            case "hibernate":
                return UserDaoHibernate.getInstance(DBHelper.getInstance().getSessionFactory());
            default:
                throw new IllegalStateException("Unknown daoType!");
        }
    }
}
