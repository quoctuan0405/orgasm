/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

/**
 *
 * @author Admin
 */
public class PasswordAuthenticationTest {
    
    public PasswordAuthenticationTest() {
    }

    @Test
    public void testHashReturnedTheHashPasswordInsteadOfPlainPassword() {
        String password1 = "password";
        String password2 = "1234";
        String password3 = "@@@@@@";
        String password4 = "password1234";
        String password5 = "password1234@@@@";
        
        PasswordAuthentication passwordAuthentication = new PasswordAuthentication();
        
        String hashedPassword1 = passwordAuthentication.hash(password1.toCharArray());
        String hashedPassword2 = passwordAuthentication.hash(password2.toCharArray());
        String hashedPassword3 = passwordAuthentication.hash(password3.toCharArray());
        String hashedPassword4 = passwordAuthentication.hash(password4.toCharArray());
        String hashedPassword5 = passwordAuthentication.hash(password5.toCharArray());

        assertThat(password1).isNotEqualTo(hashedPassword1);
        assertThat(password2).isNotEqualTo(hashedPassword2);
        assertThat(password3).isNotEqualTo(hashedPassword3);
        assertThat(password4).isNotEqualTo(hashedPassword4);
        assertThat(password5).isNotEqualTo(hashedPassword5);
    }

    @Test
    public void testAuthenticateReturnTrueIfProvidedTheRightPassword() {
        String password1 = "password";
        String password2 = "1234";
        String password3 = "@@@@@@";
        String password4 = "password1234";
        String password5 = "password1234@@@@";
        
        PasswordAuthentication passwordAuthentication = new PasswordAuthentication();
        
        String hashedPassword1 = passwordAuthentication.hash(password1.toCharArray());
        String hashedPassword2 = passwordAuthentication.hash(password2.toCharArray());
        String hashedPassword3 = passwordAuthentication.hash(password3.toCharArray());
        String hashedPassword4 = passwordAuthentication.hash(password4.toCharArray());
        String hashedPassword5 = passwordAuthentication.hash(password5.toCharArray());

        assertThat(passwordAuthentication.authenticate(password1.toCharArray(), hashedPassword1)).isTrue();
        assertThat(passwordAuthentication.authenticate(password2.toCharArray(), hashedPassword2)).isTrue();
        assertThat(passwordAuthentication.authenticate(password3.toCharArray(), hashedPassword3)).isTrue();
        assertThat(passwordAuthentication.authenticate(password4.toCharArray(), hashedPassword4)).isTrue();
        assertThat(passwordAuthentication.authenticate(password5.toCharArray(), hashedPassword5)).isTrue();
    }
    
    @Test
    public void testAuthenticateReturnFalseIfProvidedTheWrongPassword() {
        String password1 = "password";
        String password2 = "1234";
        String password3 = "@@@@@@";
        String password4 = "password1234";
        String password5 = "password1234@@@@";
        
        String wrongPassword = "nasdifjhaosid";
        
        PasswordAuthentication passwordAuthentication = new PasswordAuthentication();
        String hashedPassword1 = passwordAuthentication.hash(password1.toCharArray());
        String hashedPassword2 = passwordAuthentication.hash(password2.toCharArray());
        String hashedPassword3 = passwordAuthentication.hash(password3.toCharArray());
        String hashedPassword4 = passwordAuthentication.hash(password4.toCharArray());
        String hashedPassword5 = passwordAuthentication.hash(password5.toCharArray());
        
        assertThat(passwordAuthentication.authenticate(wrongPassword.toCharArray(), hashedPassword1)).isFalse();
        assertThat(passwordAuthentication.authenticate(wrongPassword.toCharArray(), hashedPassword2)).isFalse();
        assertThat(passwordAuthentication.authenticate(wrongPassword.toCharArray(), hashedPassword3)).isFalse();
        assertThat(passwordAuthentication.authenticate(wrongPassword.toCharArray(), hashedPassword4)).isFalse();
        assertThat(passwordAuthentication.authenticate(wrongPassword.toCharArray(), hashedPassword5)).isFalse();

    }
}
