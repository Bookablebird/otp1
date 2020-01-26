package model;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Class Product.
 */
@Entity
@Table(name="products")
public class Product {

    private static final long serialVersionUID = 2L;

	/** The name. */
	@Transient
    private final StringProperty name;

	/** The category. */
	@Transient
    private final StringProperty category;

	/** The row. */
	@Transient
    private final IntegerProperty row;

	/** The shelf. */
	@Transient
    private final IntegerProperty shelf;

	/** The id. */
	@Transient
    private final IntegerProperty id;

	/** The amount. */
	@Transient
    private final IntegerProperty amount;

    /** The data id. */
    @Id
    @Column(name="product_ID", unique=true)
    private int dataId;

    /** The data name. */
    @Column(name="product_name")
	private String dataName;

	/** The data category. */
	@Column(name="product_categoryID")
    private String dataCategory;

	/** The data row. */
	@Column(name="product_row")
    private int dataRow;

	/** The data shelf. */
	@Column(name="product_shelf")
    private int dataShelf;

	/** The data amount. */
	@Column(name="product_amount")
    private int dataAmount;

    /**
     * Default constructor.
     */
    public Product() {
        this("", "",0,0,0);
    }

    /**
     * Constructor with some initial data.
     *
     * @param name the name
     * @param category the category
     * @param row the row
     * @param shelf the shelf
     * @param amount
     */
    public Product(String name, String category, int row, int shelf, int amount) {
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.id = new SimpleIntegerProperty();
        this.row = new SimpleIntegerProperty(row);
        this.shelf = new SimpleIntegerProperty(shelf);
        this.amount = new SimpleIntegerProperty(amount);


        this.dataName=name;
        this.dataCategory=category;
        this.dataRow=row;
        this.dataShelf=shelf;
        this.dataAmount=amount;
        //this.dataId

    }


    /**
     * Sets the product values.
     */
    public void setProductValues(){
    	this.id.set(dataId);
    	this.name.set(this.dataName);
    	this.category.set(this.dataCategory);
    	this.row.set(this.dataRow);
    	this.shelf.set(this.dataShelf);
    	this.amount.set(this.dataAmount);
    }

    /**
     * PROPERTYJEN GETTERIT.
     *
     * @return the integer property
     */

    public IntegerProperty shelfProperty() {
        return shelf;
    }

    /**
     * Corridor property.
     *
     * @return the integer property
     */
    public IntegerProperty rowProperty() {
        return row;
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
     * Amount property.
     *
     * @return the integer property
     */
    public IntegerProperty amountProperty() {
        return amount;
    }

    /**
     * Category property.
     *
     * @return the integer property
     */
    public StringProperty categoryProperty() {
        return category;
    }


    /**
     * Name property.
     *
     * @return the string property
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Normaalit getterit ja setterit.
     *
     * @return the name
     */

    public String getName() {
        return name.get();
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name.set(name);
        this.dataName=name;
    }

    /**
     * Gets the category.
     *
     * @return the category
     */
    public String getCategory() {
        return category.get();
    }

    /**
     * Sets the category.
     *
     * @param category the new category
     */
    public void setCategory(String category) {
        this.category.set(category);
        this.dataCategory=category;
    }


    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getID() {
        return id.get();
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setID(int id) {
        this.id.set(id);
        this.dataId=id;
    }


    /**
     * Gets the row.
     *
     * @return the row
     */
    public int getRow() {
        return row.get();
    }

    /**
     * Sets the row.
     *
     * @param row the new row
     */
    public void setRow(int row) {
        this.row.set(row);
        this.dataRow=row;
    }

    /**
     * Gets the shelf.
     *
     * @return the shelf
     */
    public int getShelf() {
        return shelf.get();
    }

    /**
     * Sets the shelf.
     *
     * @param shelf the new shelf
     */
    public void setShelf(int shelf) {
        this.shelf.set(shelf);
        this.dataShelf=shelf;
    }

    /**
     * Gets the amount.
     *
     * @return the amount
     */
    public int getAmount() {
        return amount.get();
    }

    /**
     * Sets the amount.
     *
     * @param amount the new amount
     */
    public void setAmount(int amount) {
        this.amount.set(amount);
        this.dataAmount=amount;
    }

    /**
     * To string.
     *
     * @return the string
     */
    // To string
    @Override
    public String toString()
    {
        return("Name: "+ this.getName()+
                ".\nCorridor : " +
                this.getRow()+" " +
                "\nShelf:"+ this.getShelf()+
                "\namount"+this.getAmount()+
            "\ncategory"+this.getCategory());
            //"\n"+this.getID());
    }
}
