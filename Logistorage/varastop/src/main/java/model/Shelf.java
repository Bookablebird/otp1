package model;

// Class Declaration

/**
 * The Class Shelf.
 */
public class Shelf
{

    /** The name. */
    // Instance Variables
    private String name;
    
    /** The category. */
    private String category;
    
    /** The id. */
    private int id;
    
    /** The corridor. */
    private int corridor;
    
    /** The shelf. */
    private int shelf;



    /**
     * Instantiates a new shelf.
     *
     * @param id the id
     * @param name the name
     * @param corridor the corridor
     * @param shelf the shelf
     * @param category the category
     */
    // Constructor Declaration of Class
    public Shelf(int id, String name, int corridor, int shelf, String category)
    {
        this.id = id;
        this.name = name;
        this.corridor = corridor;
        this.shelf = shelf;
        this.category = category;

    }

    /**
     * Instantiates a new shelf.
     */
    public Shelf() {
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
     * Gets the category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the corridor.
     *
     * @return the corridor
     */
    public int getCorridor() {
        return corridor;
    }

    /**
     * Gets the shelf.
     *
     * @return the shelf
     */
    public int getShelf() {
        return shelf;
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
     * Sets the category.
     *
     * @param category the new category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the corridor.
     *
     * @param corridor the new corridor
     */
    public void setCorridor(int corridor) {
        this.corridor = corridor;
    }

    /**
     * Sets the shelf.
     *
     * @param shelf the new shelf
     */
    public void setShelf(int shelf) {
        this.shelf = shelf;
    }



}
