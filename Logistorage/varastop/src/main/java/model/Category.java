package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

// Class Declaration

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

//For the database
import java.io.Serializable;

/**
 * The Class Category.
 */
@Entity
@Table(name="categories")
public class Category
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /** The name. */
    // Instance Variables
	@Column(name="category_Name")
    private String name;
    
    /** The id. */
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="category_ID", unique=true)
    private int id;

    /**
     * Instantiates a new category.
     *
     * @param id the id
     * @param name the name
     */
    // Constructor Declaration of Class
    public Category(int id, String name)
    {
        this.id = id ;
        this.name = name;

    }

    /**
     * Instantiates a new category.
     */
    public Category() {
    }

    //Getters

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }


    //Setters

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }
}
