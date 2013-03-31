/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dandm_marpg_client;

/**
 *
 * @author dajnewin
 */
public abstract class Entity {
    
    private String name;
    
    public Entity(String entityName)
    {
        this.name = entityName;
    }
    
    public void setName(String newName)
    {
        name = newName;
    }
    
    public String getName()
    {
        return name;
    }
    
}
