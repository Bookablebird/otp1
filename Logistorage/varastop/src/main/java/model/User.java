package model;


import javax.persistence.*;


//For the database


import database.EnDeCoder;
import database.UserDao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class User.
 */
@Entity
@Table(name="users")
//@Access(AccessType.PROPERTY)
public class User {

    /** Database info. */
	private static final long serialVersionUID = 2L;
	
	/** The id. */
	@Transient
    private final IntegerProperty id;
    
    /** The first name. */
    @Transient
	private final StringProperty firstName;
	
	/** The last name. */
	@Transient
    private final StringProperty lastName;
    
    /** The username. */
    @Transient
    private final StringProperty username;
    
    /** The type. */
    @Transient
    private final StringProperty type;
    
    /** The password. */
    @Transient
    private final StringProperty password;
    
    /** The email. */
    @Transient
    private final StringProperty email;
    
    /** The dataid. */
    @Id	
    @Column(name="user_ID", unique=true)
    private int dataid;   
    
    /** The data first name. */
    @Column(name="user_firstname")
	private String dataFirstName;
	
	/** The data last name. */
	@Column(name="user_lastname")
    private String dataLastName;	
	
	/** The data username. */
	@Column(name="user_username",unique = true)
    private String dataUsername;
	
	/** The data type. */
	@Column(name="user_type")
    private String dataType;
	
	/** The data password. */
	@Column(name="user_password")
    @Convert(converter = EnDeCoder.class)
    private String dataPassword;
	
	/** The dataemail. */
	@Column(name="user_email") 
    private String dataemail;
	
	
	
    /**
     * Gets the data first name.
     *
     * @return the data first name
     */
    public String getDataFirstName() {
		return dataFirstName;
	}

	/**
	 * Sets the data first name.
	 *
	 * @param dataFirstName the new data first name
	 */
	public void setDataFirstName(String dataFirstName) {
		this.dataFirstName = dataFirstName;
	}

	/**
     * Default constructor.
     */
    public User() {
        this("", "", "", "", "");
    }

    /**
     * Constructor with some initial data.
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param username the username
     * @param type the type
     * @param email the email
     //* @param id the id
     //* @param password the password
     */
    public User(String firstName, String lastName, String username, String type, String email) {
        this.firstName = new SimpleStringProperty(this.dataFirstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.username = new SimpleStringProperty(username);
        this.id = new SimpleIntegerProperty(0);
        this.email = new SimpleStringProperty(email);
        this.type = new SimpleStringProperty(type);
        this.password = new SimpleStringProperty("otp112");
        
        
        //this.dataid = id;
        this.dataFirstName=firstName;
        this.dataLastName=lastName;
        this.dataUsername=username;
        this.dataemail=email;
        this.dataType=type;
        this.dataPassword="PruutPrööt.";
        //this.dataPassword=password;
        

    }
    
    /**
     * Sets the user values.
     */
    public void setUserValues(){
    	this.id.set(dataid);
    	this.firstName.set(this.dataFirstName);
    	this.lastName.set(this.dataLastName);
    	this.username.set(this.dataUsername);
    	this.email.set(this.dataemail);
    	this.type.set(this.dataType);
    	//this.password.set(this.dataPassword);
    	
    }	
    
	
    /**
     * PROPERTYJEN GETTERIT*.
     *
     * @return the string property
     */
    public StringProperty firstNameProperty() {
        return firstName;
    }
    
    /**
     * Last name property.
     *
     * @return the string property
     */
    public StringProperty lastNameProperty() {
        return lastName;
    }

    /**
     * Username property.
     *
     * @return the string property
     */
    public StringProperty usernameProperty() {
        return username;
    }
    
    /**
     * Id property.
     *
     * @return the integer property
     */
    public IntegerProperty idProperty() {
        return id;
    }


    /**
     * Email property.
     *
     * @return the string property
     */
    public StringProperty emailProperty() {
        return email;
    }


    /**
     * Type property.
     *
     * @return the string property
     */
    public StringProperty typeProperty() {
        return type;
    }
    
    //-------------------------------------------------------------------------

    /**
     * Normaalit getterit ja setterit*.
     *
     * @return the id
     */
    public Integer getID() {
        return id.get();
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setID(Integer id) {
        this.id.set(id);
        this.dataid=id;
    }
    

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
        this.dataFirstName=firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
        this.dataLastName=lastName;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username.get();
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username.set(username);
        this.dataUsername=username;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type.get();
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(String type) {
        this.type.set(type);
        this.dataType=type;
    }

  
    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email.set(email);
        this.dataemail=email;
    }
    

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
    	return password.get();
    }
    
    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
    	this.password.set(password);
    	this.dataPassword=password;
    }
   
    //---------------------------------------------------------


    // To string

    public String toString()
    {
        return("Username: "+ this.getUsername()+
                ".\nname: " + this.getFirstName()+" "+this.getLastName()+"\npas: "+this.getPassword()
                );

    }

}
