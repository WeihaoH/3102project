package TMS.ejb.persistence;

import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-11-30T19:00:08")
@StaticMetamodel(Team.class)
public class Team_ { 

    public static volatile SingularAttribute<Team, String> teamName;
    public static volatile SingularAttribute<Team, List> candidates;
    public static volatile SingularAttribute<Team, Date> formDeadline;
    public static volatile SingularAttribute<Team, String> teamId;
    public static volatile SingularAttribute<Team, String> courseCode;
    public static volatile SingularAttribute<Team, List> papameters;
    public static volatile SingularAttribute<Team, Boolean> isFull;
    public static volatile SingularAttribute<Team, Date> creationDate;
    public static volatile SingularAttribute<Team, List> teamMembers;

}