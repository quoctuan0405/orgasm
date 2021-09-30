/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.UUID;

/**
 *
 * @author Admin
 */
public class Token {
    static public String generateToken() {
        StringBuilder sb = new StringBuilder();
        sb = sb.append(UUID.randomUUID());
        sb = sb.append(UUID.randomUUID());
        
        System.out.println(sb.toString());
        return sb.toString();
    }
}
