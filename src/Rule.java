/**
 * The job of this class is to hold on the name and description of a rule.
 * A rule is a special rule you can add from event.
 * The player who trigger the event will be available to set a name and
 * description of the vent if they want to.
 *
 * @author TrymV
 * @version 0.1
 */
public class Rule {
    private String name;
    private String description;

    /**
     * Constructor for objects of the class Rule.
     * @param name name of the rule.
     * @param description a small description of the rule to remember it easier.
     * @throws IllegalArgumentException if name or description is set to null.
     */
    public Rule(String name, String description) {
        if(name != null || description != null) {
            this.name = name;
            this.description = description;
        }
        else {
            throw new IllegalArgumentException("name or description in Rule was set to null!");
        }
    }

    /**
     * Return name of the rule.
     * @return name of the rule.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return description of the rule.
     * @return description of the rule.
     */
    public String getDescription() {
        return this.description;
    }
}
