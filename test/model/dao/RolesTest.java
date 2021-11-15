/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import model.DefectModel;
import model.TestModel;
import model.TestModelConfig;
import model.entity.Role;
import static org.assertj.core.api.Assertions.assertThat;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mssql.InsertIdentityOperation;
import org.dbunit.ext.mssql.MsSqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Admin
 */
public class RolesTest extends TestCase {
    protected IDataSet getDataSet() throws Exception {
        String projectDirectory = System.getProperty("user.dir");

        return new FlatXmlDataSetBuilder().build(new File(projectDirectory + "\\test\\model\\" + "data.xml"));
    }
    
    protected IDatabaseConnection getDatabaseConnection() throws SQLException, DatabaseUnitException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            String url = "jdbc:sqlserver://" + TestModelConfig.serverName + ":" + TestModelConfig.portNumber + ";databaseName=" + TestModelConfig.dbName;
            Connection conn = DriverManager.getConnection(url, TestModelConfig.user, TestModelConfig.password);
            
            IDatabaseConnection connection = new DatabaseConnection(conn);
            DatabaseConfig config = connection.getConfig();
            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MsSqlDataTypeFactory());
            
            return connection;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RolesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        Roles.setModel(new TestModel());
        
        IDatabaseConnection connection = this.getDatabaseConnection();
        
        IDataSet dataSet = this.getDataSet();
        
        try {
            InsertIdentityOperation.CLEAN_INSERT.execute(connection, dataSet);
            
            System.out.println("Setup complete!!!");
        } catch(Exception e) {
            e.printStackTrace();
            connection.close();
        }
    }
    
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        
        IDataSet dataSet = this.getDataSet();
        
        IDatabaseConnection connection = this.getDatabaseConnection();
        
        try {
            DatabaseOperation.NONE.execute(connection, dataSet);
            System.out.println("Teardown complete!!!");
        } catch(Exception e) {
            System.out.println(e.getMessage());
            connection.close();
        }
    }
    
    /**
     * Test of all method, of class Roles.
     */
    @Test
    public void testAllMethodReturnAllValuesFromRolesTable() throws Exception {
        List<Role> actualValues = Roles.all();
        
        List<Role> expectedValues = new ArrayList<Role>();
        expectedValues.add(new Role(1, "user"));
        expectedValues.add(new Role(2, "admin"));
        
        assertThat(expectedValues).hasSameElementsAs(actualValues);
    }
    
    /**
     * Test of all method, of class Roles.
     */
    @Test
    public void testAllMethodReturnEmptyListWhenRoleTableIsNull() throws Exception {
        IDatabaseConnection connection = this.getDatabaseConnection();
        IDataSet dataSet = this.getDataSet();
        InsertIdentityOperation.DELETE_ALL.execute(connection, dataSet);

        List<Role> actualValues = Roles.all();
        
        List<Role> expectedValues = new ArrayList<Role>();
        
        assertThat(expectedValues).hasSameElementsAs(actualValues);
    }
    
    /**
     * Test of all method, of class Roles.
     */
    @Test
    public void testThrowExceptionWhenCantConnectToDatabase() throws Exception {
        try {
            Roles.setModel(new DefectModel());
            
            Roles.all();
            
        } catch (SQLException ex) {
            assertThat(ex.getMessage()).isEqualTo("Can't connect to database");
            return;
        }
        
        assertTrue(false);
    }
}
