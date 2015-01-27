/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Frederic
 */
public class UserServiceJmsClient implements UserService{
UserService jmsObject;
public UserServiceJmsClient(){
    //jmsObject=;
}
    @Override
    public void createUser(String username, String password, String confirmPassword, String email, String fullname) {
       jmsObject.createUser(username, password, confirmPassword, email, fullname);
    }

    @Override
    public void removeUser(Account u, String password, String confirmPassword) {
       jmsObject.removeUser(u,password,confirmPassword);
    }

    @Override
    public void modifiUser() {
        jmsObject.modifiUser();
    }

    @Override
    public Account getUser() {
       return jmsObject.getUser();
    }

    @Override
    public void addFriend() {
        jmsObject.addFriend();
    }

    @Override
    public void removeFriend() {
      jmsObject.removeFriend();
    }
    @Override
     public void addLikeToPhoto(){
     jmsObject.addLikeToPhoto();
         };
    
}
