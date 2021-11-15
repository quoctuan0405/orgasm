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
import model.entity.User;
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
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import utility.PasswordAuthentication;

/**
 *
 * @author Admin
 */
public class UsersTest extends TestCase {
    
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
            Logger.getLogger(UsersTest.class.getName()).log(Level.SEVERE, null, ex);
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
    @Override
    public void setUp() throws Exception {
        super.setUp();
        
        Users.setModel(new TestModel());
        
        IDatabaseConnection connection = this.getDatabaseConnection();
        
        IDataSet dataSet = this.getDataSet();
        
        try {
            InsertIdentityOperation.CLEAN_INSERT.execute(connection, dataSet);
            
        } catch(SQLException | DatabaseUnitException e) {
            connection.close();
        }
    }
    
    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        
        IDataSet dataSet = this.getDataSet();
        
        IDatabaseConnection connection = this.getDatabaseConnection();
        
        try {
            DatabaseOperation.NONE.execute(connection, dataSet);
        } catch(SQLException | DatabaseUnitException e) {
            System.out.println(e.getMessage());
            connection.close();
        }
    }
    
    /************** TEST METHOD ALL() ****************/
    
    /**
     * Test of method All(), class Users.
     */
    @Test
    public void testMethodAllThrowExceptionWhenCantConnectToDatabase() {
        try {
            Users.setModel(new DefectModel());
            
            Users.all();
            
        } catch (SQLException ex) {
            assertThat(ex.getMessage()).isEqualTo("Can't connect to database");
            return;
        }
        
        assertTrue(false);
    }
    
    /**
     * Test of method All(), of class Users.
     * @throws java.lang.Exception
     */
    @Test
    public void testMethodAllReturnAllValuesFromUsersTable() throws Exception {
        List<User> actualValues = Users.all();
        
        List<User> expectedValues = new ArrayList();
        expectedValues.add(new User(1, "someone@email.com", "username", "Im proud to the first user in this software!", "admin", "192 Avenue Street", "0933 485 222", "Female", "Married", "$31$16$RV53nD3fRxSFviE0HWVqV0Z4hOMb4QBM3iE3Vvu5Gmc", "https://i.postimg.cc/BZmgq0fT/pexels-dmitriy-ganin-7538060.jpg", "Hi there!", true, null));
        expectedValues.add(new User(2, "username1@email.com", "username1", "Im proud to the second user in this software!", "user", "24 Long Street", "0942 733 834", "Male", "Not married", "$31$16$RV53nD3fRxSFviE0HWVqV0Z4hOMb4QBM3iE3Vvu5Gmc", "https://i.postimg.cc/CxqHHnB7/pexels-mikhail-nilov-7815116.jpg", "Hello Im Josh!", true, null));
        expectedValues.add(new User(3, "username2@email.com", "username2", "Im proud to the third user in this software!", "user", "293 Wall Street", "0922 483 838", "Female", "Undefined", "$31$16$RV53nD3fRxSFviE0HWVqV0Z4hOMb4QBM3iE3Vvu5Gmc", "https://i.postimg.cc/k5Ws9R7G/pexels-rauf-allahverdiyev-1367243.jpg", "Hello Im Jane!", true, null));

        assertThat(actualValues).hasSameElementsAs(expectedValues);
    }
    
    /**
     * Test of method all(), of class Users.
     * @throws java.lang.Exception
     */
    @Test
    public void testMethodAllReturnTheCorrectRoleValueOfReturnUsers() throws Exception {
        List<User> actualValues = Users.all();
        
        assertThat(actualValues.get(0).getRole()).isEqualTo("admin");
        assertThat(actualValues.get(1).getRole()).isEqualTo("user");
        assertThat(actualValues.get(2).getRole()).isEqualTo("user");
    }
    
    /**
     * Test method All(), of class Users.
     * @throws java.lang.Exception
     */
    @Test
    public void testMethodAllReturnEmptyListWhenUsersTableIsNull() throws Exception {
        IDatabaseConnection connection = this.getDatabaseConnection();
        IDataSet dataSet = this.getDataSet();
        InsertIdentityOperation.DELETE_ALL.execute(connection, dataSet);

        List<User> actualValues = Users.all();
        
        List<User> expectedValues = new ArrayList();
        
        assertThat(expectedValues).hasSameElementsAs(actualValues);
    }
    
    /************** TEST METHOD findByUsername() ****************/
    
    /**
     * Test of method findByUsername(), class Users.
     */
    @Test
    public void testMethodFindByUsernameThrowExceptionWhenCantConnectToDatabase() {
        try {
            Users.setModel(new DefectModel());
            
            Users.findByUsername("username");
            
        } catch (SQLException ex) {
            assertThat(ex.getMessage()).isEqualTo("Can't connect to database");
            return;
        }
        
        assertTrue(false);
    }
    
    /**
     * Test of method findByUsername(), of class Users.
     * @throws java.lang.Exception
     */
    @Test
    public void testMethodFindByUsernameReturnCorrectUser() throws Exception {
        User actualUser1 = Users.findByUsername("username");
        User actualUser2 = Users.findByUsername("username1");
        
        User expectedUser1 = new User(1, "someone@email.com", "username", "Im proud to the first user in this software!", "admin", "192 Avenue Street", "0933 485 222", "Female", "Married", "$31$16$RV53nD3fRxSFviE0HWVqV0Z4hOMb4QBM3iE3Vvu5Gmc", "https://i.postimg.cc/BZmgq0fT/pexels-dmitriy-ganin-7538060.jpg", "Hi there!", true, null);
        User expectedUser2 = new User(2, "username1@email.com", "username1", "Im proud to the second user in this software!", "user", "24 Long Street", "0942 733 834", "Male", "Not married", "$31$16$RV53nD3fRxSFviE0HWVqV0Z4hOMb4QBM3iE3Vvu5Gmc", "https://i.postimg.cc/CxqHHnB7/pexels-mikhail-nilov-7815116.jpg", "Hello Im Josh!", true, null);

        assertThat(actualUser1).isEqualTo(expectedUser1);
        assertThat(actualUser2).isEqualTo(expectedUser2);
    }
    
    /**
     * Test of method findByUsername(), of class Users.
     * @throws java.lang.Exception
     */
    @Test
    public void testMethodFindByUsernameReturnTheCorrectRoleValueOfUser() throws Exception {
        User actualUser1 = Users.findByUsername("username");
        User actualUser2 = Users.findByUsername("username1");
        
        assertThat(actualUser1.getRole()).isEqualTo("admin");
        assertThat(actualUser2.getRole()).isEqualTo("user");
    }
    
    /**
     * Test of method findByUsername(), of class Users.
     * @throws java.lang.Exception
     */
    @Test
    public void testMethodFindByUsernameReturnNullWhenANonExistUsernameIsProvided() throws Exception {
        User actualUser = Users.findByUsername("ajksdhflakjsdhf");
        
        assertThat(actualUser).isNull();
    }
    
    /**
     * Test of method findByUsername(), of class Users.
     * @throws java.lang.Exception
     */
    @Test
    public void testMethodFindByUsernameReturnNullWhenNullValueIsProvided() throws Exception {
        User actualUser = Users.findByUsername(null);
        
        assertThat(actualUser).isNull();
    }
    
    /**
     * Test method findByUsername(), of class Users.
     * @throws java.lang.Exception
     */
    @Test
    public void testMethodFindByUsernameReturnNullWhenUsersTableIsNull() throws Exception {
        IDatabaseConnection connection = this.getDatabaseConnection();
        IDataSet dataSet = this.getDataSet();
        InsertIdentityOperation.DELETE_ALL.execute(connection, dataSet);

        User actualUser = Users.findByUsername("username");
        
        assertThat(actualUser).isNull();
    }
    
    /************** TEST METHOD findById() ****************/
    
    /**
     * Test of method findById(), class Users.
     */
    @Test
    public void testMethodFindByIdThrowExceptionWhenCantConnectToDatabase() {
        try {
            Users.setModel(new DefectModel());
            
            Users.findById(1);
            
        } catch (SQLException ex) {
            assertThat(ex.getMessage()).isEqualTo("Can't connect to database");
            return;
        }
        
        assertTrue(false);
    }
    
    /**
     * Test of method findById, of class Users.
     * @throws java.lang.Exception
     */
    @Test
    public void testMethodFindByIdReturnCorrectUser() throws Exception {
        User actualUser1 = Users.findById(1);
        User actualUser2 = Users.findById(2);
        
        User expectedUser1 = new User(1, "someone@email.com", "username", "Im proud to the first user in this software!", "admin", "192 Avenue Street", "0933 485 222", "Female", "Married", "$31$16$RV53nD3fRxSFviE0HWVqV0Z4hOMb4QBM3iE3Vvu5Gmc", "https://i.postimg.cc/BZmgq0fT/pexels-dmitriy-ganin-7538060.jpg", "Hi there!", true, null);
        User expectedUser2 = new User(2, "username1@email.com", "username1", "Im proud to the second user in this software!", "user", "24 Long Street", "0942 733 834", "Male", "Not married", "$31$16$RV53nD3fRxSFviE0HWVqV0Z4hOMb4QBM3iE3Vvu5Gmc", "https://i.postimg.cc/CxqHHnB7/pexels-mikhail-nilov-7815116.jpg", "Hello Im Josh!", true, null);

        assertThat(actualUser1).isEqualTo(expectedUser1);
        assertThat(actualUser2).isEqualTo(expectedUser2);
    }
    
    /**
     * Test of method findById(), of class Users.
     * @throws java.lang.Exception
     */
    @Test
    public void testMethodFindByIdReturnNullWhenANonExistIdIsProvided() throws Exception {
        User actualUser = Users.findById(-1);
        
        assertThat(actualUser).isNull();
    }
    
    /**
     * Test of method findById(), of class Users.
     * @throws java.lang.Exception
     */
    @Test
    public void testMethodFindByIdReturnTheCorrectRoleValueOfUser() throws Exception {
        User actualUser1 = Users.findById(1);
        User actualUser2 = Users.findById(2);
        
        assertThat(actualUser1.getRole()).isEqualTo("admin");
        assertThat(actualUser2.getRole()).isEqualTo("user");
    }
    
    /**
     * Test method findById(), of class Users.
     * @throws java.lang.Exception
     */
    @Test
    public void testMethodFindByIdReturnNullWhenUsersTableIsNull() throws Exception {
        IDatabaseConnection connection = this.getDatabaseConnection();
        IDataSet dataSet = this.getDataSet();
        InsertIdentityOperation.DELETE_ALL.execute(connection, dataSet);

        User actualUser = Users.findById(1);
        
        assertThat(actualUser).isNull();
    }
    
    /************** TEST METHOD verifyPassword() ****************/
    
    /**
     * Test method verify(), of class Users.
     * @throws java.lang.Exception
     */
    @Test
    public void testVerifyReturnTrueWhenProvidedTheRightCombinationOfUsernameAndPassword() throws Exception {
        User user1 = Users.verify("username", "password");
        User user2 = Users.verify("username1", "password");

        User expectedUser1 = new User(1, "someone@email.com", "username", "Im proud to the first user in this software!", "admin", "192 Avenue Street", "0933 485 222", "Female", "Married", "$31$16$RV53nD3fRxSFviE0HWVqV0Z4hOMb4QBM3iE3Vvu5Gmc", "https://i.postimg.cc/BZmgq0fT/pexels-dmitriy-ganin-7538060.jpg", "Hi there!", true, null);
        User expectedUser2 = new User(2, "username1@email.com", "username1", "Im proud to the second user in this software!", "user", "24 Long Street", "0942 733 834", "Male", "Not married", "$31$16$RV53nD3fRxSFviE0HWVqV0Z4hOMb4QBM3iE3Vvu5Gmc", "https://i.postimg.cc/CxqHHnB7/pexels-mikhail-nilov-7815116.jpg", "Hello Im Josh!", true, null);

        assertThat(user1).isEqualTo(expectedUser1);
        assertThat(user2).isEqualTo(expectedUser2);
    }
    
    /**
     * Test method verify(), of class Users.
     * @throws java.lang.Exception
     */
    @Test
    public void testVerifyReturnFalseWhenProvidedTheWrongCombinationOfUsernameAndPassword() throws Exception {
        User user1 = Users.verify("username", "password1234");
        User user2 = Users.verify("username1456", "password");

        assertThat(user1).isNull();
        assertThat(user2).isNull();
    }
    
    /************** TEST METHOD add() ****************/
    
    /**
     * Test of method add(), class Users.
     */
    @Test
    public void testMethodAddUserThrowExceptionWhenCantConnectToDatabase() {
        try {
            Users.setModel(new DefectModel());
            
            Users.add("email@email.com", "username", "password", 1, "absdfasdoifuahosdjf");
            
        } catch (SQLException ex) {
            assertThat(ex.getMessage()).isEqualTo("Can't connect to database");
            return;
        }
        
        assertTrue(false);
    }
    
    /**
     * Test method add(), of class Users.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddNewUserSuccessfully() throws Exception {
        User actualAddedUser1 = Users.add("email@email.com", "username3737", "password", 1, "absdfasdoifuahosdjf");
        User actualAddedUser2 = Users.add("another@email.com", "username81187", "password", 1, "absdfasdoifuahosdjf");
        
        User expectedAddedUser1 = Users.findByUsername("username3737");
        User expectedAddedUser2 = Users.findByUsername("username81187");

        assertThat(actualAddedUser1).isNotNull();
        assertThat(actualAddedUser2).isNotNull();

        assertThat(actualAddedUser1).isEqualTo(expectedAddedUser1);
        assertThat(actualAddedUser2).isEqualTo(expectedAddedUser2);
    }
    
    /**
     * Test method add(), of class Users.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddNewUserThrowExceptionWhenAddNewUserWithDuplicateUsername() throws Exception {
        try {
            Users.setModel(new DefectModel());
            
            Users.add("email@email.com", "username", "password", 1, "absdfasdoifuahosdjf");
            
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("Username already exists. Please choose another username.");
            return;
        }
        
        assertTrue(false);
    }
    
    /**
     * Test method add(), of class Users.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddNewUserThrowExceptionWhenAddNewUserWithDuplicateEmail() throws Exception {
        try {
            Users.setModel(new DefectModel());
            
            Users.add("someone@email.com", "username7234628", "password", 1, "absdfasdoifuahosdjf");
            
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("Email already exists. Please choose another username.");
            return;
        }
        
        assertTrue(false);
    }
    
    /************** TEST METHOD updateProfile() ****************/
    
    /**
     * Test of method updateProfile(), class Users.
     */
    @Test
    public void testMethodupdateProfileThrowExceptionWhenCantConnectToDatabase() throws Exception {
        try {
            Users.setModel(new DefectModel());
            
            Users.updateProfile("0923744839", "Female", "Married", "Hoy hoy!", "User's profile", "https://images.com/image.jpg", "293 Long Street", 1);
            
        } catch (SQLException ex) {
            assertThat(ex.getMessage()).isEqualTo("Can't connect to database");
            return;
        }
        
        assertTrue(false);
    }
    
    @Test
    public void testUpdateProfileMethodUpdateUserCorrectly() throws SQLException, Exception {
        Users.updateProfile("0923744839", "Female", "Married", "Hoy hoy!", "User's profile", "https://images.com/image.jpg", "293 Long Street", 1);

        User expectedUpdatedUser = Users.findById(1);

        assertThat(expectedUpdatedUser.getId()).isEqualTo(1);
        assertThat(expectedUpdatedUser.getGender()).isEqualTo("Female");
        assertThat(expectedUpdatedUser.getPhone()).isEqualTo("0923744839");
        assertThat(expectedUpdatedUser.getShortDescription()).isEqualTo("Hoy hoy!");
    }
    
    @Test
    public void testUpdateProfileMethodThrowExceptionWhenProvideUserIdThatNotExists() throws SQLException, Exception {
        try {
            Users.updateProfile("0923744839", "Female", "Married", "Hoy hoy!", "User's profile", "https://images.com/image.jpg", "293 Long Street", 100);
            
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("Update an user that not exists in the database.");
            return;
        }
        
        assertTrue(false);
    }
    
    @Test
    public void testUpdateProfileMethodThrowExceptionWhenProvideShortDescriptionThatMoreThan20Characters() throws SQLException, Exception {
        try {
            Users.updateProfile("0923744839", "Female", "Married", "Hey I'm a short description!!!", "User's profile", "https://images.com/image.jpg", "293 Long Street", 100);
            
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("Short description is more than 20 characters");
            return;
        }
        
        assertTrue(false);
    }
    
    /************** TEST METHOD delete() ****************/
    
    /**
     * Test of method delete(), class Users.
     */
    @Test
    public void testDeleteUserThrowsExceptionWhenCantConnectToDatabase() throws Exception {
        try {
            Users.setModel(new DefectModel());

            Users.delete(1);
            
        } catch (SQLException ex) {
            assertThat(ex.getMessage()).isEqualTo("Can't connect to database");
            return;
        }
        
        assertTrue(false);
    }
    
    /**
     * Test of method delete(), class Users.
     */
    @Test
    public void testDeleteUserActuallyDeleteUserWhenProvideExistsUserId() throws Exception {
        Users.delete(1);
        
        User user = Users.findById(1);
            
        assertThat(user).isNull();
    }
    
    /**
     * Test of method delete(), class Users.
     */
    @Test
    public void testDeleteThrowExceptionWhenProvideNonExistedUserId() throws Exception {
        try {
            Users.delete(100);
            
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("User ID does not exists!");
            return;
        }
        
        assertTrue(false);
    }
    
    /************** TEST METHOD changePassword() ****************/
    
    /**
     * Test of method changePassword(), class Users.
     */
    @Test
    public void testChangePasswordThrowsExceptionWhenCantConnectToDatabase() throws Exception {
        try {
            Users.setModel(new DefectModel());

            Users.changePassword("username", "password");
            
        } catch (SQLException ex) {
            assertThat(ex.getMessage()).isEqualTo("Can't connect to database");
            return;
        }
        
        assertTrue(false);
    }
    
    /**
     * Test of method changePassword(), class Users.
     */
    @Test
    public void testChangePasswordActuallyChangePassword() throws Exception {
        String password = "password1234@1234";
        
        Users.changePassword("username", password);
        
        assertThat(Users.verify("username", password)).isNotNull();
    }
    
    /**
     * Test of method changePassword(), class Users.
     */
    @Test
    public void testChangePasswordThrowExceptionWhenNonExistingUsernameIsProvided() throws Exception {
        try {
            Users.changePassword("aksjdhfalksdboaisudfh", "password");
            
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("User does not exists.");
            return;
        }
        
        assertTrue(false);
    }
    
    /**
     * Test of method changePassword(), class Users.
     */
    @Test
    public void testChangePasswordStoreTheHashedPassword() throws Exception {
        String password = "password";
                
        Users.changePassword("username", "password");
            
        User user = Users.findByUsername("username");
        
        PasswordAuthentication passwordAuthentication = new PasswordAuthentication();
        
        assertThat(user.getHashedPassword()).isNotEqualTo(password);
        assertThat(passwordAuthentication.authenticate(password.toCharArray(), user.getHashedPassword())).isEqualTo(true);
    }
    
    /************** TEST METHOD setToken() ****************/
    
    /**
     * Test of method setToken(), class Users.
     */
    @Test
    public void testSetTokenThrowsExceptionWhenCantConnectToDatabase() throws Exception {
        try {
            Users.setModel(new DefectModel());

            Users.setToken(1, "myverylonganduniquetoken");
            
        } catch (SQLException ex) {
            assertThat(ex.getMessage()).isEqualTo("Can't connect to database");
            return;
        }
        
        assertTrue(false);
    }
    
    /**
     * Test of method setToken(), class Users.
     */
    @Test
    public void testSetTokenUpdateUserTokenInTheDatabase() throws Exception {
        String token = "myverylonganduniquetoken";
        
        Users.setToken(1, token);
            
        User user = Users.findById(1);
        
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.isEmailVerified()).isFalse();
        assertThat(user.getVerifyToken()).isEqualTo(token);
    }
    
    /**
     * Test of method setToken(), class Users.
     */
    @Test
    public void testSetTokenSetUserTokenToNullInTheDatabaseWhenNullTokenParamenterIsProvided() throws Exception {
        String token = null;
        
        Users.setToken(1, token);
            
        User user = Users.findById(1);
        
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.isEmailVerified()).isFalse();
        assertThat(user.getVerifyToken()).isEqualTo(token);
    }
    
    /**
     * Test of method setToken(), class Users.
     */
    @Test
    public void testSetTokenThrowsExceptionWhenANonExistedUserIdIsProvided() throws Exception {
        try {
            Users.setToken(-1, "myverylonganduniquetoken");
            
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("User does not exists.");
            return;
        }
        
        assertTrue(false);
    }
    
    /************** TEST METHOD verifyEmail() ****************/
    
    /**
     * Test of method verifyEmail(), class Users.
     */
    @Test
    public void testVerifyEmailThrowsExceptionWhenCantConnectToDatabase() throws Exception {
        try {
            Users.setModel(new DefectModel());

            Users.verifyEmail(1, "myverylonganduniquetoken");
            
        } catch (SQLException ex) {
            assertThat(ex.getMessage()).isEqualTo("Can't connect to database");
            return;
        }
        
        assertTrue(false);
    }
    
    /**
     * Test of method verifyEmail(), class Users.
     */
    @Test
    public void testVerifyEmailSetUserEmailVerifiedToTrueAndTokenToNullWhenProvidedWithTheRightToken() throws Exception {
        String token = "myverylonganduniquetoken";
        Users.setToken(1, token);

        Users.verifyEmail(1, token);
        
        User user = Users.findById(1);
        
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getVerifyToken()).isEqualTo(null);
        assertThat(user.isEmailVerified()).isEqualTo(true);
    }
    
    /**
     * Test of method verifyEmail(), class Users.
     */
    @Test
    public void testVerifyEmailReturnNullWhenProvidedWithTheWrongToken() throws Exception {
        String token = "myverylonganduniquetoken";
        Users.setToken(1, token);

        User result = Users.verifyEmail(1, "asdfasdf");
        
        User user = Users.findById(1);
        
        assertThat(result).isNull();
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getVerifyToken()).isNotNull();
        assertThat(user.isEmailVerified()).isEqualTo(false);
    }
    
    /**
     * Test of method verifyEmail(), class Users.
     */
    @Test
    public void testVerifyEmailThrowExceptionWhenProvideWithANonExistUserId() throws Exception {
        try {
            Users.verifyEmail(-1, "myverylonganduniquetoken");
            
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("User is not exists.");
            return;
        }
        
        assertTrue(false);
    }
}
