package persistence;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-02T01:03:59")
@StaticMetamodel(UserAccount.class)
public class UserAccount_ { 

    public static volatile SingularAttribute<UserAccount, String> firstname;
    public static volatile SingularAttribute<UserAccount, byte[]> password;
    public static volatile SingularAttribute<UserAccount, String> role;
    public static volatile SingularAttribute<UserAccount, byte[]> salt;
    public static volatile SingularAttribute<UserAccount, String> userId;
    public static volatile SingularAttribute<UserAccount, String> email;
    public static volatile SingularAttribute<UserAccount, String> lastname;

}