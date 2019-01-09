
package entity;

import java.util.Date;

/**
 *
 * @author andre
 */
public class PetDTO
{

    private Integer id;
    private String name;
    private Date birth;
    private String species;
    private Date death;
    private Owner owner;
    private String ownerFirstName;
    private String ownerLastName;
    

    public PetDTO()
    {
    }

    public PetDTO(Integer id, String name, Date birth, String species, Date death, String ownerFirstName, String ownerLastName)
    {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.species = species;
        this.death = death;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
    }


    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Date getBirth()
    {
        return birth;
    }

    public void setBirth(Date birth)
    {
        this.birth = birth;
    }

    public String getSpecies()
    {
        return species;
    }

    public void setSpecies(String species)
    {
        this.species = species;
    }

    public Date getDeath()
    {
        return death;
    }

    public void setDeath(Date death)
    {
        this.death = death;
    }

    public String getOwnerFirstName()
    {
        return owner.getFirstName();
    }

    public void setOwnerFirstName(String ownerFirstName)
    {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName()
    {
        return owner.getLastName();
    }

    public void setOwnerLastName(String ownerLastName)
    {
        this.ownerLastName = ownerLastName;
    }

    public Owner getOwner()
    {
        return owner;
    }

    public void setOwner(Owner owner)
    {
        this.owner = owner;
    }

    @Override
    public String toString()
    {
        return "PetDTO{" + "id=" + id + ", name=" + name + ", birth=" + birth + ", species=" + species + ", death=" + death + ", ownerFirstName=" + ownerFirstName + ", ownerLastName=" + ownerLastName + '}';
    }


    
    
}
